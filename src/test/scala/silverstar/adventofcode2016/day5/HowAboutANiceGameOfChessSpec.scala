package silverstar.adventofcode2016.day5

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class HowAboutANiceGameOfChessSpec extends AnyFunSpec with Matchers {

  import HowAboutANiceGameOfChess._

  describe("md5") {

    val testData: Seq[(String, String)] = Seq(
      ("00000155F8105DFF7F56EE10FA9B9ABD".toLowerCase, "abc3231929"),
      ("000008F82C5B3924A1ECBEBF60344E00".toLowerCase, "abc5017308"),
      ("00000F9A2C309875E05C5A5D09F1B8C4".toLowerCase, "abc5278568")
    )

    for (t <- testData) {
      it(s"should return a md5 hash of ${t._1} for ${t._2}") {
        md5(t._2) shouldBe t._1
      }
    }
  }

  describe("isValidHash") {
    it("should return 'true' for hash '00000155F8105DFF7F56EE10FA9B9ABD'") {
      isValidHash("00000155F8105DFF7F56EE10FA9B9ABD") shouldBe true
    }
  }

  describe("char") {
    it("should return '1' for hash '00000155F8105DFF7F56EE10FA9B9ABD'") {
      char("00000155F8105DFF7F56EE10FA9B9ABD") shouldBe '1'
    }
  }

  describe("charAndPosition") {
    it("should return (1, '5') for hash '00000155F8105DFF7F56EE10FA9B9ABD'") {
      charAndPosition("00000155F8105DFF7F56EE10FA9B9ABD") shouldBe (1, '5')
    }
  }

  describe("password") {

    val testData: Seq[(String, String)] = Seq(
      ("18f47a30", "abc"),
      ("801b56a7", "abbhdwsy")
    )

    for (t <- testData) {
      ignore(s"should return password '${t._1}' for doorId '${t._2}'") {
        password(t._2) shouldBe t._1
      }
    }
  }

  describe("passwordImproved") {

    val testData: Seq[(String, String)] = Seq(
      ("05ace8e3", "abc"),
      ("424a0197", "abbhdwsy")
    )

    for (t <- testData) {
      ignore(s"should return password '${t._1}' for doorId '${t._2}'") {
        passwordImproved(t._2) shouldBe t._1
      }
    }
  }
}
