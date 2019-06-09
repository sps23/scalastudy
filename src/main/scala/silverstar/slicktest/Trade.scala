package silverstar.slicktest

import java.sql.Date

import slick.jdbc.H2Profile.api._

case class Trade(id: String, dataDate: Date)

class Trades(tag: Tag) extends Table[Trade](tag, "trades") {
  def id       = column[String]("id", O.PrimaryKey)
  def dataDate = column[Date]("data_date")
  def *        = (id, dataDate) <> (Trade.tupled, Trade.unapply)
}
