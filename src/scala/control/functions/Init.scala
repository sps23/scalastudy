package scala.control.functions

/**
 * @author sylwesterstocki
 */
object Init {
  
  import scala.io.Source
  
  // evaluated as soon as defined
  val wordsVal = Source.fromFile("/usr/share/dict/words").mkString
  
  // evaluated for the first time it is used
  lazy val wordsLazyCorrect = Source.fromFile("/usr/share/dict/words").mkString
  lazy val wordsLazyWrong = Source.fromFile("/usr/share/dict/words123").mkString
  
  // evaluated every time it is used
  def wordsDef = Source.fromFile("/usr/share/dict/words").mkString
}