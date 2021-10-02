package silverstar.codewars.poker

import org.scalatest.wordspec.AnyWordSpec

class PokerHandUnitTest extends AnyWordSpec {

  "build" should {
    "create only valid PokerHand" in {
      // Arrange
      val expected: Seq[PokerHand] = Seq[Option[PokerHand]](
        PokerHand.build("KS AS TS QS JS"),
        PokerHand.build("2H 3H 4H 5H 6H"),
        PokerHand.build("AS AD AC AH JD"),
        PokerHand.build("JS JD JC JH 3D"),
        PokerHand.build("2S AH 2H AS AC"),
        PokerHand.build("AS 3S 4S 8S 2S"),
        PokerHand.build("2H 3H 5H 6H 7H"),
        PokerHand.build("2S 3H 4H 5S 6C"),
        PokerHand.build("2D AC 3H 4H 5S"),
        PokerHand.build("AH AC 5H 6H AS"),
        PokerHand.build("2S 2H 4H 5S 4C"),
        PokerHand.build("AH AC 5H 6H 7S"),
        PokerHand.build("AH AC 4H 6H 7S"),
        PokerHand.build("2S AH 4H 5S KC"),
        PokerHand.build("2S 3H 6H 7S 9C")
      ).flatten

      print(expected.map(_.print()).mkString("\n"))

      assert(expected.length === 15)
    }
  }
}
