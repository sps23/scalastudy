package silverstar.adventofcode2016.day6

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import scala.io.Source

class SignalsAndNoiseSpec extends AnyFunSpec with Matchers {

  import SignalsAndNoise._

  val inputMessage: String =
    """eedadn
      |drvtee
      |eandsr
      |raavrd
      |atevrs
      |tsrnev
      |sdttsa
      |rasrtv
      |nssdts
      |ntnada
      |svetve
      |tesnvt
      |vntsnd
      |vrdear
      |dvrsen
      |enarar
    """.stripMargin

  describe("rowsToColumns") {
    it("should convert rows to columns") {
      val testData = Array("abcd", "efgh")
      val expected = Array("ae", "bf", "cg", "dh")
      rowsToColumns(testData) shouldBe expected
    }
  }

  describe("mostCommonChar") {
    val testData = Seq(
      ("aaabbcc", 'a'),
      ("aabbcc", 'a'),
      ("abc", 'a'),
      ("abbccac", 'c')
    )

    for (t <- testData) {
      it(s"should return the most common character '${t._2}' for ${t._1}") {
        mostCommonChar(t._1) shouldBe t._2
      }
    }
  }

  describe("leastCommonChar") {
    val testData = Seq(
      ("aaabbc", 'c'),
      ("aabbcc", 'a'),
      ("abbccc", 'a'),
      ("abcacc", 'b')
    )

    for (t <- testData) {
      it(s"should return the least common character '${t._2}' for ${t._1}") {
        leastCommonChar(t._1) shouldBe t._2
      }
    }
  }

  describe("decodeMessage") {
    it("should decode the example message to 'easter'") {
      decodeMessage(inputMessage) shouldBe "easter"
    }

    it("should return result for example data") {
      val input = getClass.getResourceAsStream("SignalsAndNoise.txt")
      val data  = Source.fromInputStream(input, "UTF8").mkString
      decodeMessage(data) shouldBe "tzstqsua"
    }
  }

  describe("decodeMessageMod") {
    it("should decode the example message to 'advent'") {
      decodeMessageMod(inputMessage) shouldBe "advent"
    }

    it("should return result for example data") {
      val input = getClass.getResourceAsStream("SignalsAndNoise.txt")
      val data  = Source.fromInputStream(input, "UTF8").mkString
      decodeMessageMod(data) shouldBe "myregdnr"
    }
  }
}
