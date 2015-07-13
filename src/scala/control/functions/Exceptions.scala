package scala.control.functions

/**
 * @author sylwesterstocki
 */
object Exceptions {
  
  
  def sqrt(i : Double) : Double = {
    if(i >= 0) {
      math.sqrt(i)
    } else {
      throw new IllegalArgumentException("i should be greater than 0")
    }
  }
}