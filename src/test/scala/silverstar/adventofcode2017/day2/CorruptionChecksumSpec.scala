package silverstar.adventofcode2017.day2

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import scala.io.Source

class CorruptionChecksumSpec extends AnyFunSpec with Matchers {

  import CorruptionChecksum._

  describe("calculateChecksum") {

    it("should return '18' for the test spreadsheet") {
      val spreadsheet =
        """5 1 9 5
          |7 5 3
          |2 4 6 8""".stripMargin
      calculateChecksum(spreadsheet) shouldBe 18
    }

    it("should return result for example data") {
      val input = getClass.getResourceAsStream("CorruptionChecksum.txt")
      val data  = Source.fromInputStream(input, "UTF8").mkString
      calculateChecksum(data) shouldBe 36174
    }
  }

  describe("calculateNewChecksum1") {

    it("should return '9' for the test spreadsheet") {
      val spreadsheet =
        """5 9 2 8
          |9 4 7 3
          |3 8 6 5""".stripMargin
      calculateNewChecksum1(spreadsheet) shouldBe 9
    }

    it("should return result for example data") {
      val input = getClass.getResourceAsStream("CorruptionChecksum.txt")
      val data  = Source.fromInputStream(input, "UTF8").mkString
      calculateNewChecksum1(data) shouldBe 244
    }
  }

  describe("calculateNewChecksum2") {

    it("should return '9' for the test spreadsheet") {
      val spreadsheet =
        """5 9 2 8
          |9 4 7 3
          |3 8 6 5""".stripMargin
      calculateNewChecksum2(spreadsheet) shouldBe 9
    }

    it("should return result for example data") {
      val input = getClass.getResourceAsStream("CorruptionChecksum.txt")
      val data  = Source.fromInputStream(input, "UTF8").mkString
      calculateNewChecksum2(data) shouldBe 244
    }
  }
}
