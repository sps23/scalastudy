package silverstar.slicktest

import java.sql.Date
import java.time.LocalDate

import slick.jdbc
import slick.jdbc.H2Profile
import slick.jdbc.H2Profile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

object SlickApp extends App {

  val db: H2Profile.backend.Database          = Database.forConfig("h2mem1")
  val session: jdbc.H2Profile.backend.Session = db.createSession()

  val localDateNow = LocalDate.now
  val dateNow      = Date.valueOf(localDateNow)
  val idA: String  = "AAA"
  val idB: String  = "BBB"

  val setup = DBIO.seq(
    (trades.schema ++ stringDataPoints.schema ++ dateDataPoints.schema ++ doubleDataPoints.schema).createIfNotExists,
    // insert trades
    trades += Trade(idA, dateNow),
    trades += Trade(idB, dateNow),
    // insert data points for idA
    stringDataPoints += StringDataPoint(None, idA, "Product Type", Option("Bond"), None),
    stringDataPoints += StringDataPoint(None, idA, "Description", Option("Test Trade A"), None),
    stringDataPoints += StringDataPoint(None, idA, "Counterparty Name", None, Option("Missing")),
    dateDataPoints += DateDataPoint(None, idA, "Maturity Date", None, None),
    dateDataPoints += DateDataPoint(None, idA, "Settlement Date", Option(dateNow), None),
    doubleDataPoints += DoubleDataPoint(None, idA, "Dollar Value", Option(122.56), None),
    doubleDataPoints += DoubleDataPoint(None, idA, "Fx Rate", None, Option("Unknown currency")),
    // insert data points for idB
    stringDataPoints += StringDataPoint(None, idB, "Product Type", Option("SWAP"), None),
    stringDataPoints += StringDataPoint(None, idB, "Description", Option("Test Trade B"), None),
    stringDataPoints += StringDataPoint(None, idB, "Counterparty Name", Option("CP Name"), None),
    dateDataPoints += DateDataPoint(None, idB, "Maturity Date", None, None),
    dateDataPoints += DateDataPoint(None, idB, "Settlement Date", Option(dateNow), None),
    doubleDataPoints += DoubleDataPoint(None, idB, "Dollar Value", Option(45.88), None),
    doubleDataPoints += DoubleDataPoint(None, idB, "Fx Rate", Option(1.32), None)
  )

  val result: Future[Unit] = db.run(setup)
  result onComplete {
    case Success(_) => println("OK")
    case Failure(t) => println(s"Error: ${t.getLocalizedMessage}")
  }

  val tradesResult: Future[Seq[Trade]] = db.run(trades.result)
  tradesResult.foreach(t => println(t.mkString("\n")))

  val stringDataPointsResult: Future[Seq[StringDataPoint]] = db.run(stringDataPoints.result)
  stringDataPointsResult.foreach(t => println(t.mkString("\n")))

  val dateDataPointsResult: Future[Seq[DateDataPoint]] = db.run(dateDataPoints.result)
  dateDataPointsResult.foreach(t => println(t.mkString("\n")))

  val joinStr                                           = (trades join stringDataPoints on (_.id === _.rowId)).result
  val joinDate                                          = (trades join dateDataPoints on (_.id === _.rowId)).result
  val joinDouble                                        = (trades join doubleDataPoints on (_.id === _.rowId)).result
  val joinStrResult: Future[Seq[(Trade, DataPoint)]]    = db.run(joinStr)
  val joinDateResult: Future[Seq[(Trade, DataPoint)]]   = db.run(joinDate)
  val joinDoubleResult: Future[Seq[(Trade, DataPoint)]] = db.run(joinDouble)

  val joinResult: Future[Seq[(Trade, DataPoint)]] =
    for {
      str    <- joinStrResult
      date   <- joinDateResult
      double <- joinDoubleResult
    } yield {
      val result: Seq[(Trade, DataPoint)] = str ++ date ++ double
      result
    }

  joinResult.foreach(t => println(t.mkString("\n")))

  val mapByTrade: Future[Map[Trade, Seq[(Trade, DataPoint)]]] = joinResult.map(_.groupBy(_._1))
  mapByTrade.foreach(t => println(t.mkString("\n")))

  val dataRows: Future[Seq[DataRow]] = for {
    m <- mapByTrade
  } yield DataRow.dataRowsFrom(m)
  dataRows.foreach(t => println(t.mkString("\n")))
}
