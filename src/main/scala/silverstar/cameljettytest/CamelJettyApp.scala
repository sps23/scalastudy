package silverstar.cameljettytest

import org.apache.camel.main.Main

object CamelJettyApp extends App {

  private val defaultPort = "9820"

  val camelMain = new Main
  camelMain.addRouteBuilder(new CamelJettyRoute(defaultPort, "test1"))
  camelMain.addRouteBuilder(new CamelJettyRoute(defaultPort, "test2"))
  camelMain.run()
}
