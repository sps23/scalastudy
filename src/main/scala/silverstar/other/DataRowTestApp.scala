package silverstar.other

import java.time.LocalDate

import org.scalactic.{Bad, Good, Or}

object DataRowTestApp extends App {

  trait Named {
    def name: String
  }

  trait Error[Value] {
    val message: String
    val rejectedValue: Option[Value]
  }

  case class Missing[Value](message: String) extends Error[Value] {
    override val rejectedValue: Option[Value] = None
  }

  case class Invalid[Value](message: String, rejectedVal: Value) extends Error[Value] {
    override val rejectedValue: Option[Value] = Option(rejectedVal)
  }

  trait DataPoint extends Named {
    type Value
    val value: Option[Value]
    val error: Option[Error[Value]]
  }

  object DataPoint {
    def fromOr[V](or: Or[V, String], aName: String): DataPoint = new DataPoint {
      override type Value = V
      override val value: Option[Value] = or.toOption
      override val error: Option[Error[Value]] = or.fold(_ => None, bf => Option(Missing[Value](bf)))

      override val name: String = aName
    }

    def from[V](or: Or[V, String], aName: String): DataPoint = or match {
      case Good(g) => g match {
        case s: String => new StringDataPoint(Option(s), aName, None)
        case ld: LocalDate => new LocalDateDataPoint {
          override val value: Option[LocalDate] = Option(ld)
          override val error: Option[Error[LocalDate]] = None

          override def name: String = aName
        }
        case other => new DataPoint {
          override type Value = V
          override val value: Option[Value] = Option(other)
          override val error: Option[Error[Value]] = None

          override val name: String = aName
        }
      }
      case Bad(b) => new DataPoint {
        override type Value = V
        override val value: Option[Value] = None
        override val error: Option[Error[Value]] = Option(Missing[Value](b))

        override val name: String = aName
      }
    }
  }

  case class DataRow(dataPoints: Seq[DataPoint]) {
    val dataPointsByNameMap: Map[String, DataPoint] = dataPoints.map(dp => dp.name -> dp).toMap

    def getByName(name: String): Option[DataPoint] = dataPointsByNameMap.get(name)
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

  case class Description(override val value: Option[String], override val name: String, override val error: Option[Error[String]]) extends StringDataPoint(value, name ,error)
  case class CreatedAt(value: Option[LocalDate], name: String, error: Option[Error[LocalDate]]) extends LocalDateDataPoint
  case class Label(override val value: Option[String], override val name: String, override val error: Option[Error[String]]) extends StringDataPoint(value, name, error)

  val dp1 = new StringDataPoint(Option("AAA"),"A",None)

  val dp2 = new LocalDateDataPoint {
    override val value: Option[LocalDate] = Option(LocalDate.now)

    override def name: String = "BBB"

    override val error: Option[Error[LocalDate]] = None
  }

  val dp3 = Description(Option("desc"), "Description", None)
  val dp4 = CreatedAt(Option(LocalDate.now), "Created At", None)
  val dp5 = Label(None, "Main Label", Option(Missing[String]("missing label")))
  val dp6 = Label(None, "Secondary Label", Option(Invalid[String]("invalid label, lowercase letters only", "CapitalLetters")))
  val dp7 = DataPoint.from(Good("definition"), "Definition A")
  val dp8 = DataPoint.fromOr(Bad("missing definition"), "Definition B")

  val dataRow1: DataRow = DataRow(Seq(dp1, dp2, dp3, dp4, dp5, dp6, dp7, dp8))

  println(dataRow1.dataPoints.map(_.value).mkString(";"))
  println(dataRow1.dataPoints.map(_.error).mkString(";"))
  dataRow1.dataPoints.map(dp => dp.value.map(_.getClass.getCanonicalName)).foreach(println)

  println(dataRow1.dataPointsByNameMap.mkString("\n"))

  val dpTest1: Option[DataPoint] = dataRow1.dataPointsByNameMap.get("Description")
  val dpTest2: Option[DataPoint] = dataRow1.getByName("Description")

  val classTest = dpTest1.map(_.isInstanceOf[StringDataPoint])
  dpTest1 foreach  {
    case _: Description => println("D")
    case _ => println("Other")
  }

  val valueClassTestS = dpTest1.map(_.asInstanceOf[StringDataPoint].value)
  val valueClassTestD = dpTest1.map(_.asInstanceOf[Description].value)
  val valueTest1: Option[DataPoint#Value] = dpTest1.flatMap(_.value)
}