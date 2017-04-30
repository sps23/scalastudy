package silverstar.scalafortheimpatient.control.functions

object Factorial {

  def facIter(n: Int) = {
    var r : BigDecimal = 1
    for (i <- 1 to n) r = r * i
    r
  }

  def facRec(n: Int): BigDecimal = if (n <= 0) 1 else n * facRec(n - 1)
}