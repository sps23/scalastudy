package silverstar.cameljettytest

import org.apache.camel.main.Main

object CamelJettyApp extends App {

  val camelMain = new Main
  camelMain.addRouteBuilder(new CamelJettyRoute)
  camelMain.run()
}
