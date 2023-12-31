package silverstar.adventofcode2017.day1

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import scala.io.Source

class InverseCaptchaSpec extends AnyFunSpec with Matchers {

  import silverstar.adventofcode2017.day1.InverseCaptcha._

  describe("solveCaptcha") {
    val testData: Seq[(String, Long)] = Seq(
      ("1122", 3),
      ("1111", 4),
      ("1234", 0),
      ("91212129", 9)
    )

    describe("solveCaptcha1") {

      testData.foreach(t => {
        it(s"should return '${t._2}' for '${t._1}'") {
          solveCaptcha1(t._1) shouldBe t._2
        }
      })

      it("should return result for example data") {
        val input = getClass.getResourceAsStream("InverseCaptcha.txt")
        val data  = Source.fromInputStream(input, "UTF8").mkString
        solveCaptcha1(data) shouldBe 1341
      }
    }

    describe("solveCaptcha2") {

      testData.foreach(t => {
        it(s"should return '${t._2}' for '${t._1}'") {
          solveCaptcha2(t._1) shouldBe t._2
        }
      })

      it("should return result for example data") {
        val input = getClass.getResourceAsStream("InverseCaptcha.txt")
        val data  = Source.fromInputStream(input, "UTF8").mkString
        solveCaptcha2(data) shouldBe 1341
      }
    }

    describe("solveCaptcha3") {

      testData.foreach(t => {
        it(s"should return '${t._2}' for '${t._1}'") {
          solveCaptcha3(t._1) shouldBe t._2
        }
      })

      it("should return result for example data") {
        val input = getClass.getResourceAsStream("InverseCaptcha.txt")
        val data  = Source.fromInputStream(input, "UTF8").mkString
        solveCaptcha3(data) shouldBe 1341
      }
    }
  }

  describe("solveNewCaptcha") {
    val testData: Seq[(String, Long)] = Seq(
      ("1212", 6),
      ("1221", 0),
      ("123425", 4),
      ("123123", 12),
      ("12131415", 4)
    )

    testData.foreach(t => {
      it(s"should return '${t._2}' for '${t._1}'") {
        solveNewCaptcha(t._1) shouldBe t._2
      }
    })

    it("should return result for example data") {
      val input = getClass.getResourceAsStream("InverseCaptcha.txt")
      val data  = Source.fromInputStream(input, "UTF8").mkString
      solveNewCaptcha(data) shouldBe 1348
    }
  }
}
