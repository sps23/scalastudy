package silverstar.modeling

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

