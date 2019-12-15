package silverstar.pureconfig

import com.typesafe.config.{ConfigFactory, ConfigRenderOptions, ConfigValue}
import pureconfig.{ConfigSource, ConfigWriter}
import pureconfig.error.ConfigReaderFailures
import pureconfig.syntax._
import pureconfig.generic.auto._

case class KeyModel(key: String)

case class ValueModel(value: String)

case class KeyValuesModel(key: KeyModel, values: List[ValueModel])

case class PureConfigModel2(map: Map[String, List[ValueModel]])

case class PureconfigModel1(list: List[String],
                            map: Map[String, String],
                            mapList: Map[String, List[String]],
                            mapListValue: Map[String, List[ValueModel]],
                            keyValues: List[KeyValuesModel])

object PureconfigApp extends App {

  val configString: String =
    s"""
       |list = ["L1", "L2"]
       |map {
       |  k1 = "value"
       |}
       |map-list {
       |  k2 = ["value1", "value2"]
       |}
       |map-list-value {
       |  k3 = [
       |    { value = "v1" },
       |    { value = "v2" }
       |  ]
       |}
       |key-values = [
       |  {
       |    key = { key = "k4" },
       |    values = [
       |      { value = "v3" },
       |      { value = "v4" }
       |    ]
       |  }
       |]
     """.stripMargin

  val config                                            = ConfigFactory.parseString(configString)
  val p: Either[ConfigReaderFailures, PureconfigModel1] = ConfigSource.fromConfig(config).load[PureconfigModel1]
  p match {
    case Left(f) => println(f)
    case Right(pm) =>
      val m: Map[KeyModel, List[ValueModel]] = pm.keyValues.map(kv => (kv.key, kv.values)).toMap
      println(m)
  }
  println(p)

  val pcm2 = PureConfigModel2(
    map = Map("k1" -> List(ValueModel("v1"), ValueModel("v2")))
  )

//  implicit val keyModelConfigWriter   = ConfigWriter.toString[KeyModel](km => km.key)
//  implicit val valueModelConfigWriter = ConfigWriter.toString[ValueModel](vm => vm.value)

  val s: ConfigValue = pcm2.toConfig

  println(s.render(ConfigRenderOptions.concise().setFormatted(true)))
}
