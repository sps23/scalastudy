package silverstar.cameljettytest

import org.apache.camel.{Exchange, Processor}

class HttpProcessor extends Processor {
  override def process(exchange: Exchange): Unit = {

    val env = exchange.getIn.getHeader("env")

    env match {
      case "test" => exchange.getOut().setBody("<html><body>TEST env</body></html>")
      case "dev"  => exchange.getOut().setBody("<html><body>DEV env</body></html>")
      case _      => exchange.getOut().setBody("<html><body>UNKNOWN env</body></html>")
    }
  }
}
