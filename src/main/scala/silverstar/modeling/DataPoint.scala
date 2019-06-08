package silverstar.modeling

import java.time.LocalDate

import org.scalactic.{Bad, Good, Or}

trait DataPoint extends Named {
  type Value
  val value: Option[Value]
  val error: Option[Error[Value]]
}

object DataPoint {
  def fromOr[V](or: Or[V, String], aName: String): DataPoint = new DataPoint {
    override type Value = V
    override val value: Option[Value]        = or.toOption
    override val error: Option[Error[Value]] = or.fold(_ => None, bf => Option(Missing[Value](bf)))

    override val name: String = aName
  }

  def from[V](or: Or[V, String], aName: String): DataPoint = or match {
    case Good(g) =>
      g match {
        case s: String => new StringDataPoint(Option(s), aName, None)
        case ld: LocalDate =>
          new LocalDateDataPoint {
            override val value: Option[LocalDate]        = Option(ld)
            override val error: Option[Error[LocalDate]] = None

            override def name: String = aName
          }
        case other =>
          new DataPoint {
            override type Value = V
            override val value: Option[Value]        = Option(other)
            override val error: Option[Error[Value]] = None

            override val name: String = aName
          }
      }
    case Bad(b) =>
      new DataPoint {
        override type Value = V
        override val value: Option[Value]        = None
        override val error: Option[Error[Value]] = Option(Missing[Value](b))

        override val name: String = aName
      }
  }
}

class StringDataPoint(val value: Option[String], val name: String, val error: Option[Error[String]]) extends DataPoint {
  override type Value = String
}

trait LocalDateDataPoint extends DataPoint {
  override type Value = LocalDate
}

case class LongDataPoint(value: Option[Long], name: String, error: Option[Error[Long]]) extends DataPoint {
  override type Value = Long
}

case class Description(override val value: Option[String],
                       override val name: String,
                       override val error: Option[Error[String]])
    extends StringDataPoint(value, name, error)
case class CreatedAt(value: Option[LocalDate], name: String, error: Option[Error[LocalDate]]) extends LocalDateDataPoint
case class Label(override val value: Option[String],
                 override val name: String,
                 override val error: Option[Error[String]])
    extends StringDataPoint(value, name, error)
