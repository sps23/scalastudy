package silverstar.scalafortheimpatient.control.functions

object Factorial {

  @SuppressWarnings(Array("org.wartremover.warts.Var"))
  def facIter(n: Int): BigDecimal = {
    var r: BigDecimal = 1
    for (i <- 1 to n) r = r * i
    r
  }

  def facRec(n: Int): BigDecimal = if (n <= 0) 1 else n * facRec(n - 1)
}
