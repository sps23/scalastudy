package silverstar.adventofcode2017.day1

import org.scalatest.{FunSpec, Matchers}

import scala.io.Source

class InverseCaptchaSpec extends FunSpec with Matchers {

  import silverstar.adventofcode2017.day1.InverseCaptcha._

  val testData: Seq[(String, Long)] = Seq(
    ("1122", 3),
    ("1111", 4),
    ("1234", 0),
    ("91212129", 9)
  )

  describe("solveCaptcha") {

    testData.foreach(t => {
      it(s"should return '${t._2}' for '${t._1}'") {
        solveCaptcha(t._1) shouldBe t._2
      }
    })

    it("should return result for example data") {
      val input = getClass.getResourceAsStream("InverseCaptcha.txt")
      val data = Source.fromInputStream(input, "UTF8").mkString
      solveCaptcha(data) shouldBe 1341
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
      val data = Source.fromInputStream(input, "UTF8").mkString
      solveCaptcha2(data) shouldBe 1341
    }
  }
}
