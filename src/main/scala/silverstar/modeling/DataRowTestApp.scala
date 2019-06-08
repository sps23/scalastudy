package silverstar.modeling

import java.time.LocalDate

import org.scalactic.{Bad, Good}

object DataRowTestApp extends App {

  val dp1 = new StringDataPoint(Option("AAA"), "A", None)

  val dp2 = new LocalDateDataPoint {
    override val value: Option[LocalDate] = Option(LocalDate.now)

    override def name: String = "BBB"

    override val error: Option[Error[LocalDate]] = None
  }

  val dp3 = Description(Option("desc"), "Description", None)
  val dp4 = CreatedAt(Option(LocalDate.now), "Created At", None)
  val dp5 = Label(None, "Main Label", Option(Missing[String]("missing label")))
  val dp6 =
    Label(None, "Secondary Label", Option(Invalid[String]("invalid label, lowercase letters only", "CapitalLetters")))
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
  dpTest1 foreach {
    case _: Description => println("D")
    case _              => println("Other")
  }

  val valueClassTestS                     = dpTest1.map(_.asInstanceOf[StringDataPoint].value)
  val valueClassTestD                     = dpTest1.map(_.asInstanceOf[Description].value)
  val valueTest1: Option[DataPoint#Value] = dpTest1.flatMap(_.value)
}
