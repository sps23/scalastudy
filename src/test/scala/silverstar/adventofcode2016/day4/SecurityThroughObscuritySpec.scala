package silverstar.adventofcode2016.day4

import org.scalatest.{FunSpec, Matchers}

import scala.io.Source

class SecurityThroughObscuritySpec extends FunSpec with Matchers {

  import SecurityThroughObscurity._

  describe("Day4SecurityThroughObscurity") {

    describe("helper methods") {
      val testData: Seq[(String, (RoomEncryption, Boolean))] = Seq(
        ("aaaaa-bbb-z-y-x-123[abxyz]", (RoomEncryption("aaaaabbbzyx", 123L, "abxyz"), true)),
        ("a-b-c-d-e-f-g-h-987[abcde]", (RoomEncryption("abcdefgh", 987L, "abcde"), true)),
        ("not-a-real-room-404[oarel]", (RoomEncryption("notarealroom", 404L, "oarel"), true)),
        ("totally-real-room-200[decoy]", (RoomEncryption("totallyrealroom", 200L, "decoy"), false))
      )

      describe("parseRoom") {
        for (t <- testData) {
          it(s"should parse ${t._1} to ${t._2._1}") {
            parseRoom(t._1) shouldBe t._2._1
          }
        }
      }

      describe("isRealRoom") {
        for (t <- testData) {
          it(s"should say that ${t._2._1} isRealRoom = ${t._2._2}") {
            t._2._1.isRealRoom shouldBe t._2._2
          }
        }
      }
    }

    describe("sumOfTheSectorIdsForTheRealRooms") {
      it("should return result for example data") {
        val input = getClass.getResourceAsStream("SecurityThroughObscurity.txt")
        val data = Source.fromInputStream(input, "UTF8").mkString
        sumOfTheSectorIdsForTheRealRooms(data) shouldBe 278221
      }
    }
  }
}
