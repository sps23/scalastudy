package silverstar.scalafortheimpatient.control.functions

object Exercises {

  def signum(x: Int): Int = {
    if (x == 0) {
      0
    } else if (x > 0) {
      1
    } else {
      -1
    }
  }

  def countdown(n: Int): Unit = {
    for (i <- n to 0 by -1) {
      println(i)
    }
  }

  @SuppressWarnings(Array("org.wartremover.warts.Var"))
  def productUnicodeIter(s: String): BigDecimal = {
    var product: BigDecimal = 1
    for (ch <- s) product *= ch
    product
  }

  def productUnicodeRec(s: String): BigDecimal = {
    if (s.length == 0) 1
    else s.head * productUnicodeRec(s.tail)
  }

  def power(x: Int, n: Int): Double = {
    if (n == 0) 1
    else if (n > 0) {
      if (n % 2 == 0) {
        power(x, n / 2) * power(x, n / 2)
      } else {
        x * power(x, n - 1)
      }
    } else {
      1 / power(x, -1 * n)
    }
  }
}
