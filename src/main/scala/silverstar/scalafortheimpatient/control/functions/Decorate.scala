package silverstar.scalafortheimpatient.control.functions

object Decorate {

  private val defaultLeft  = "["
  private val defaultRight = "["

  def decorate(str: String)                              = defaultLeft + str + defaultRight
  def decorate(str: String, left: String, right: String) = left + str + right
}
