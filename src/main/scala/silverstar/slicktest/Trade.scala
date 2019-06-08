package silverstar.slicktest

import java.sql.Date

import slick.jdbc.H2Profile.api._

case class Trade(tradeId: String, dataDate: Date)

class Trades(tag: Tag) extends Table[Trade](tag, "trades") {
  def tradeId  = column[String]("trade_id")
  def dataDate = column[Date]("data_date")
  def pk       = primaryKey("pk", (tradeId, dataDate))
  def *        = (tradeId, dataDate) <> (Trade.tupled, Trade.unapply)
}
