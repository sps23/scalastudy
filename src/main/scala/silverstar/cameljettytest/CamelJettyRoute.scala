package silverstar.cameljettytest

import org.apache.camel.builder.RouteBuilder

class CamelJettyRoute extends RouteBuilder {

  override def configure(): Unit = {
    from("jetty:http://localhost:9080/testservice").process(new HttpProcessor)
  }
}
