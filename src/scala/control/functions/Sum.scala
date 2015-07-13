package scala.control.functions

/**
 * @author sylwesterstocki
 */
object Sum {
  
  def sumIter(args: Int*) = {
    var result = 0
    for(arg <- args) result += arg
    result
  }
  
  def sumRec(args: Int*) : Int = {
    if(args.length == 0) 0
    else args.head + sumRec(args.tail : _*)
  }
}