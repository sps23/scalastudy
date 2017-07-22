package silverstar.adventofcode.day1

import org.scalatest.{FunSpec, Matchers}
import silverstar.adventofcode.day1.NoTimeForATaxicab._

class NoTimeForATaxicabSpec extends FunSpec with Matchers {

  describe("NoTimeForATaxicabSpec") {

    describe("parseInput") {
      it("for 'R5, L5, R5, R3'") {
        val actual = parseInput("R5, L5, R5, R3")
        val expected = List(R(5), L(5), R(5), R(3))
        actual shouldBe expected
      }

      it("for 'R11, L222, R3333'") {
        val actual = parseInput("R11, L222, R3333")
        val expected = List(R(11), L(222), R(3333))
        actual shouldBe expected
      }
    }

    val testInput = "L4, R2, R4, L5, L3, L1, R4, R5, R1, R3, L3, L2, L2, R5, R1, L1, L2, R2, R2, L5, R5, R5, L2, R1, " +
      "R2, L2, L4, L1, R5, R2, R1, R1, L2, L3, R2, L5, L186, L5, L3, R3, L5, R4, R2, L5, R1, R4, L1, L3, R3, R1, " +
      "L1, R4, R2, L1, L4, R5, L1, R50, L4, R3, R78, R4, R2, L4, R3, L4, R4, L1, R5, L4, R1, L2, R3, L2, R5, R5, " +
      "L4, L1, L2, R185, L5, R2, R1, L3, R4, L5, R2, R4, L3, R4, L2, L5, R1, R2, L2, L1, L2, R2, L2, R1, L5, L3, " +
      "L4, L3, L4, L2, L5, L5, R2, L3, L4, R4, R4, R5, L4, L2, R4, L5, R3, R1, L1, R3, L2, R2, R1, R5, L4, R5, " +
      "L3, R2, R3, R1, R4, L4, R1, R3, L5, L1, L3, R2, R1, R4, L4, R3, L3, R3, R2, L3, L3, R4, L2, R4, L3, L4, " +
      "R5, R1, L1, R5, R3, R1, R3, R4, L1, R4, R3, R1, L5, L5, L4, R4, R3, L2, R1, R5, L3, R4, R5, L4, L5, R2"

    describe("howManyBlocksAway") {
      it("should return 2 for 'R2, L3'") {
        val actual = howManyBlocksAway("R2, L3")
        val expected = 5
        actual shouldBe expected
      }

      it("should return 2 for 'R2, R2, R2'") {
        val actual = howManyBlocksAway("R2, R2, R2")
        val expected = 2
        actual shouldBe expected
      }

      it("should return 5 for 'L2, L2, L5'") {
        val actual = howManyBlocksAway("R2, R2, R2")
        val expected = 2
        actual shouldBe expected
      }

      it("should return 12 for 'R5, L5, R5, R3'") {
        val actual = howManyBlocksAway("R5, L5, R5, R3")
        val expected = 12
        actual shouldBe expected
      }

      it("should return correct result for test input") {
        val actual = howManyBlocksAway(testInput)
        val expected = 353
        actual shouldBe expected
      }
    }

    describe("howManyBlocksAwayFirstVisitedTwice") {
      it("should return 4 for 'R8, R4, R4, R8'") {
        val actual = howManyBlocksAwayFirstVisitedTwice("R8, R4, R4, R8")
        val expected = 4
        actual shouldBe expected
      }

      it("should return correct result for test input") {
        val actual = howManyBlocksAwayFirstVisitedTwice(testInput)
        val expected = 152
        actual shouldBe expected
      }
    }
  }
}
