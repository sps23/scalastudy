package silverstar.slicktest

import java.sql.Date
import java.time.LocalDate

import slick.jdbc.H2Profile.api._

import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object SlickApp extends App {

  val db = Database.forConfig("h2mem1")

  db.createSession()

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
  Await.result(tradesResult, 5 seconds)
  tradesResult.map(println)

  val stringDataPointsResult: Future[Seq[StringDataPoint]] = db.run(stringDataPoints.result)
  Await.result(stringDataPointsResult, 5 seconds)
  stringDataPointsResult.map(println)

  val dateDataPointsResult: Future[Seq[DateDataPoint]] = db.run(dateDataPoints.result)
  Await.result(dateDataPointsResult, 5 seconds)
  dateDataPointsResult.map(println)

  val joinStr                                                 = (trades join stringDataPoints on (_.id === _.rowId)).result
  val joinDate                                                = (trades join dateDataPoints on (_.id === _.rowId)).result
  val joinDouble                                              = (trades join doubleDataPoints on (_.id === _.rowId)).result
  val joinStrResult: Future[Seq[(Trade, StringDataPoint)]]    = db.run(joinStr)
  val joinDateResult: Future[Seq[(Trade, DateDataPoint)]]     = db.run(joinDate)
  val joinDoubleResult: Future[Seq[(Trade, DoubleDataPoint)]] = db.run(joinDouble)

  val joinResult: Future[Seq[(Trade, DataPoint)]] =
    for {
      str    <- joinStrResult
      date   <- joinDateResult
      double <- joinDoubleResult
    } yield {
      str ++ date ++ double
    }

  Await.result(joinResult, 5 seconds)
  joinResult.map(println)

  val mapByTrade: Future[Map[Trade, Seq[(Trade, DataPoint)]]] = joinResult.map(_.groupBy(_._1))
  mapByTrade.map(println)

  val dataRows: Future[Seq[DataRow]] = for {
    m <- mapByTrade
  } yield DataRow.dataRowsFrom(m)
  Await.result(dataRows, 5 seconds)
  dataRows.map(println)
}
