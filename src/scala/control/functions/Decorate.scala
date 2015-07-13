package scala.control.functions

/**
 * @author sylwesterstocki
 */
object Decorate {
  
  def decorate(str: String, left: String = "[", right: String = "]") = left + str + right
}