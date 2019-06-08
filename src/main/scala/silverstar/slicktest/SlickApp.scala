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

  val setup = DBIO.seq(
    (trades.schema ++ stringDataPoints.schema).createIfNotExists,
    // insert trades
    trades += Trade("AAA", dateNow),
    stringDataPoints += StringDataPoint(None, "AAA", dateNow, "Product Type", Option("Bond"), None),
    stringDataPoints += StringDataPoint(None, "AAA", dateNow, "Description", Option("Test Trade 1"), None)
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

  val join = (trades join stringDataPoints on ((trade, str) => {
    trade.tradeId === str.tradeId && trade.dataDate === str.dataDate
  })).result
  val joinResult: Future[Seq[(Trade, StringDataPoint)]] = db.run(join)
  Await.result(joinResult, 5 seconds)
  joinResult.map(println)
  joinResult.map(_.groupBy(_._1)).map(println)
  joinResult.map(_.groupBy(_._1).mapValues(_.map(_._2))).map(println)
}
