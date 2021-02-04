package silverstar.slicktest

import java.sql.Date

import slick.jdbc.H2Profile.api._

sealed trait DataPoint {
  type Value
  val id: Option[Long]
  val rowId: String
  val name: String
  val value: Option[Value]
  val error: Option[String]
}

case class StringDataPoint(id: Option[Long], rowId: String, name: String, value: Option[String], error: Option[String])
    extends DataPoint {
  override type Value = String
}

class StringDataPoints(tag: Tag) extends Table[StringDataPoint](tag, None, "stringDataPoints") {
  def id    = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def rowId = column[String]("row_id")
  def name  = column[String]("name")
  def value = column[Option[String]]("value")
  def error = column[Option[String]]("error")
  def *     = (id.?, rowId, name, value, error) <> (StringDataPoint.tupled, StringDataPoint.unapply)
}

case class DateDataPoint(id: Option[Long], rowId: String, name: String, value: Option[Date], error: Option[String])
    extends DataPoint {
  override type Value = Date
}

class DateDataPoints(tag: Tag) extends Table[DateDataPoint](tag, None, "dateDataPoints") {
  def id    = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def rowId = column[String]("row_id")
  def name  = column[String]("name")
  def value = column[Option[Date]]("value")
  def error = column[Option[String]]("error")
  def *     = (id.?, rowId, name, value, error) <> (DateDataPoint.tupled, DateDataPoint.unapply)
}

case class DoubleDataPoint(id: Option[Long], rowId: String, name: String, value: Option[Double], error: Option[String])
    extends DataPoint {
  override type Value = Double
}

class DoubleDataPoints(tag: Tag) extends Table[DoubleDataPoint](tag, None, "doubleDataPoints") {
  def id    = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def rowId = column[String]("row_id")
  def name  = column[String]("name")
  def value = column[Option[Double]]("value")
  def error = column[Option[String]]("error")
  def *     = (id.?, rowId, name, value, error) <> (DoubleDataPoint.tupled, DoubleDataPoint.unapply)
}
