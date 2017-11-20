package silverstar.challenge

import java.io.ByteArrayInputStream

import org.scalatest.{FunSpec, Matchers}
import PaintShop._

class PaintShopSpec extends FunSpec with Matchers {

  implicit class StringImprovements(string: String) {
    def toInputStream = new ByteArrayInputStream(string.getBytes)
  }

  val input1: String =
    """5
      |1 M 3 G 5 G
      |2 G 3 M 4 G
      |5 M""".stripMargin

  val order1 = Order(
    numberOfColors = 5,
    customerPreferences = Seq(
      CustomerPreference(colors = Seq(Paint(1, Matt), Paint(3, Gloss), Paint(5, Gloss))),
      CustomerPreference(colors = Seq(Paint(2, Gloss), Paint(3, Matt), Paint(4, Gloss))),
      CustomerPreference(colors = Seq(Paint(5, Matt)))
    )
  )

  val result1 = Seq(Gloss, Gloss, Gloss, Gloss, Matt)

  val input2: String =
    """1
      |1 G
      |1 M""".stripMargin

  val order2 = Order(
    numberOfColors = 1,
    customerPreferences = Seq(
      CustomerPreference(colors = Seq(Paint(1, Gloss))),
      CustomerPreference(colors = Seq(Paint(1, Matt)))
    )
  )

  val input3: String =
    """5
      |2 M
      |5 G
      |1 G
      |5 G 1 G 4 M
      |3 G
      |5 G
      |3 G 5 G 1 G
      |3 G
      |2 M
      |5 G 1 G
      |2 M
      |5 G
      |4 M
      |5 G 4 M""".stripMargin

  val result3 = Seq(Gloss, Matt, Gloss, Matt, Gloss)

  val input4: String =
    """2
      |1 G 2 M
      |1 M
      |""".stripMargin

  val result4 = Seq(Matt, Matt)

  describe("parseInputFile") {
    it("should parse input1 to order1") {
      parseInput(input1.toInputStream) shouldBe order1
    }

    it("should parse input2 to order2") {
      parseInput(input2.toInputStream) shouldBe order2
    }
  }

  describe("satisfyCustomers") {

    it("should return result1 for order1") {
      satisfyOrder(order1) shouldBe result1
    }

    it("should return NO result for order2") {
      satisfyOrder(order2) shouldBe Seq()
    }

    it("should return result3 for order3") {
      satisfyOrder(parseInput(input3.toInputStream)) shouldBe result3
    }

    it("should return result4 for order4") {
      satisfyOrder(parseInput(input4.toInputStream)) shouldBe result4
    }
  }
}
