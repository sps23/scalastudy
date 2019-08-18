package silverstar.cameljettytest

import org.apache.camel.builder.RouteBuilder

class CamelJettyRoute(port: String, name: String) extends RouteBuilder {

  override def configure(): Unit = {
    from(s"jetty:http://localhost:$port/$name").process(new HttpProcessor)
  }
}
