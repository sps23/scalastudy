package silverstar.scalafortheimpatient.control.functions

object Exceptions {

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  def sqrt(i: Double): Double = {
    if (i >= 0) {
      math.sqrt(i)
    } else {
      throw new IllegalArgumentException("i should be greater than 0")
    }
  }
}
