package silverstar.adventofcode2016.day3

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import scala.io.Source

class SquaresWithThreeSidesSpec extends AnyFunSpec with Matchers {

  import SquaresWithThreeSides._

  describe("isTriangle") {
    it("should return true for valid triangle") {
      isTriangle((2, 3, 4)) shouldBe true
    }

    it("should return false for invalid triangle") {
      isTriangle((10, 15, 25)) shouldBe false
    }
  }

  describe("howManyTriangles") {
    it("should return result for example data") {
      val input = getClass.getResourceAsStream("SquaresWithThreeSides.txt")
      val data  = Source.fromInputStream(input, "UTF8").mkString
      howManyTriangles(data) shouldBe 983
    }
  }

  describe("howManyTrianglesVertical") {
    it("should return result for example data") {
      val input = getClass.getResourceAsStream("SquaresWithThreeSides.txt")
      val data  = Source.fromInputStream(input, "UTF8").mkString
      howManyTrianglesVertical(data) shouldBe 1836
    }
  }
}
