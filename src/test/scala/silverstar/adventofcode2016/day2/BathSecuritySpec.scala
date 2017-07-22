package silverstar.adventofcode2016.day2

import org.scalatest.Matchers
import org.scalatest.FunSpec

import scala.io.Source

class BathSecuritySpec extends FunSpec with Matchers {

  import BathSecurity._

  private val input = getClass.getResourceAsStream("BathSecurity.txt")
  private val example = Source.fromInputStream(input, "UTF8").mkString

  describe("1to9Pad") {
    describe("makeMove1to9Pad") {
      val testDataMakeMove1to9Pad: Seq[((Int, Char), Int)] = Seq(
        ((5, 'U'), 2),
        ((5, 'D'), 8),
        ((5, 'L'), 4),
        ((5, 'R'), 6),
        ((1, 'L'), 1),
        ((1, 'U'), 1),
        ((2, 'U'), 2),
        ((3, 'U'), 3),
        ((3, 'R'), 3),
        ((4, 'L'), 4),
        ((6, 'R'), 6),
        ((7, 'L'), 7),
        ((7, 'D'), 7),
        ((8, 'D'), 8),
        ((9, 'D'), 9),
        ((9, 'R'), 9)
      )

      for (t <- testDataMakeMove1to9Pad) {
        it(s"should return [${t._2}] for (${t._1})") {
          makeMove1to9Pad(t._1._1, t._1._2) shouldBe t._2
        }
      }
    }

    describe("figureOutBathCode1to9Pad") {
      val testData1to9Pad: Seq[(String, String)] = Seq(
        ("ULL\nRRDDD\nLURDL\nUUUUD", "1985"),
        (example, "76792")
      )

      for (t <- testData1to9Pad) {
        it(s"should return [${t._2}] for (${t._1.mkString(";")})") {
          figureOutBathCode1to9Pad(t._1) shouldBe t._2
        }
      }
    }
  }

  describe("1toDPad") {
    describe("makeMove1toDPad") {
      val testDataMakeMove1to9Pad: Seq[((Long, Char), Long)] = Seq(
        ((1L, 'D'), 3L),
        ((5L, 'U'), 5L),
        ((5L, 'D'), 5L),
        ((5L, 'L'), 5L),
        ((5L, 'R'), 6L),
        ((13L, 'U'), 11L)
      )
      for (t <- testDataMakeMove1to9Pad) {
        it(s"should return [${t._2}] for (${t._1})") {
          makeMove1toDPad(t._1._1, t._1._2) shouldBe t._2
        }
      }
    }

    describe("figureOutBathCode1to9Pad") {
      val testData1toDPad: Seq[(String, String)] = Seq(
        (example, "A7AC3")
      )
      for (t <- testData1toDPad) {
        it(s"should return [${t._2}] for (${t._1.mkString(";")})") {
          figureOutBathCode1toDPad(t._1) shouldBe t._2
        }
      }
    }
  }
}
