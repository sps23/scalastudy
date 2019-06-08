package silverstar.slicktest

import java.sql.Date

import slick.jdbc.H2Profile.api._

sealed trait DataPoint {
  type Value
  val id: Option[Long]
  val tradeId: String
  val dataDate: Date
  val name: String
  val value: Option[Value]
  val error: Option[String]
}

case class StringDataPoint(id: Option[Long],
                           tradeId: String,
                           dataDate: Date,
                           name: String,
                           value: Option[String],
                           error: Option[String])
    extends DataPoint {
  override type Value = String
}

class StringDataPoints(tag: Tag) extends Table[StringDataPoint](tag, "stringDataPoints") {
  def id       = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def tradeId  = column[String]("trade_id")
  def dataDate = column[Date]("data_date")

  def name  = column[String]("name")
  def value = column[Option[String]]("value")
  def error = column[Option[String]]("error")
  def *     = (id.?, tradeId, dataDate, name, value, error) <> (StringDataPoint.tupled, StringDataPoint.unapply)
}

//case class DateDataPoint(id: Option[Long], name: String, value: Option[Date], error: Option[String]) extends DataPoint {
//  override type Value = Date
//}
//
//class DateDataPoints(tag: Tag) extends Table[DateDataPoint](tag, "dateDataPoints") {
//  def id    = column[Long]("id", O.PrimaryKey, O.AutoInc)
//  def name  = column[String]("name")
//  def value = column[Option[Date]]("value")
//  def error = column[Option[String]]("error")
//  def *     = (id.?, name, value, error) <> (DateDataPoint.tupled, DateDataPoint.unapply)
//}
//
//case class DoubleDataPoint(id: Option[Long], name: String, value: Option[Double], error: Option[String])
//    extends DataPoint {
//  override type Value = Double
//}
//
//class DoubleDataPoints(tag: Tag) extends Table[DoubleDataPoint](tag, "dateDataPoints") {
//  def id    = column[Long]("id", O.PrimaryKey, O.AutoInc)
//  def name  = column[String]("name")
//  def value = column[Option[Double]]("value")
//  def error = column[Option[String]]("error")
//  def *     = (id.?, name, value, error) <> (DoubleDataPoint.tupled, DoubleDataPoint.unapply)
//}
