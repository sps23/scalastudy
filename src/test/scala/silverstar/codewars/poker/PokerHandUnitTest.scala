package silverstar.codewars.poker

import org.scalatest.wordspec.AnyWordSpec

import scala.util.Random

class PokerHandUnitTest extends AnyWordSpec {

  // Arrange
  private val pokerHandsSorted: Seq[PokerHand] = Seq[Option[PokerHand]](
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

  private def printPokerHands(s: Seq[PokerHand]): Unit = println(s.map(_.print()).mkString("\n"))

  printPokerHands(pokerHandsSorted)

  "PokerHand.build" should {
    "create only valid PokerHand" in {
      assert(pokerHandsSorted.length === 15)
    }
  }

  "PokerHand.sort" should {
    "sort example PokerHand" in {
      val input: Seq[PokerHand] = Random.shuffle(pokerHandsSorted)
      printPokerHands(input)

      val actual: Seq[PokerHand] = input.sorted(PokerHand.ordering)
      printPokerHands(actual)

      assert(actual === pokerHandsSorted)
    }
    "sort StraightFlush" in {

    }
  }

  "PokerHand.isRoyalFlush" should {
    "return true" in {
      assert(PokerHand.build("KS AS TS QS JS").map(_.isRoyalFlush) === Option(true))
      assert(PokerHand.build("KD TD AD QD JD").map(_.isRoyalFlush) === Option(true))
    }
    "return false" in {
      assert(PokerHand.build("KS AS TS QS JH").map(_.isRoyalFlush) === Option(false))
      assert(PokerHand.build("KS AS TS QS 2S").map(_.isRoyalFlush) === Option(false))
    }
  }

  "PokerHand.isStraightFlush" should {
    "return true" in {
      assert(PokerHand.build("2H 3H 4H 5H 6H").map(_.isStraightFlush) === Option(true))
      assert(PokerHand.build("2D AD 3D 4D 5D").map(_.isStraightFlush) === Option(true))
      assert(PokerHand.build("7S 6S 3S 4S 5S").map(_.isStraightFlush) === Option(true))
      assert(PokerHand.build("KC AC TC QC JC").map(_.isStraightFlush) === Option(true))
    }
    "return false" in {
      assert(PokerHand.build("TS 3S 4S 5S 6S").map(_.isStraightFlush) === Option(false))
      assert(PokerHand.build("KS AS TD QS 2H").map(_.isStraightFlush) === Option(false))
      assert(PokerHand.build("2S 3H 4H 5S 7C").map(_.isStraightFlush) === Option(false))
    }
  }

  "PokerHand.isFourOfAKind" should {
    "return true" in {
      assert(PokerHand.build("AS AD AC AH JD").map(_.isFourOfAKind) === Option(true))
      assert(PokerHand.build("JS JD JC JH 3D").map(_.isFourOfAKind) === Option(true))
    }
    "return false" in {
      assert(PokerHand.build("9S 9C 9H 4S 2H").map(_.isFourOfAKind) === Option(false))
      assert(PokerHand.build("2S 3H 4H 5S 6C").map(_.isFourOfAKind) === Option(false))
    }
  }

  "PokerHand.isFullHouse" should {
    "return true" in {
      assert(PokerHand.build("2S AH 2H AS AC").map(_.isFullHouse) === Option(true))
      assert(PokerHand.build("3S 3C JC JH 3D").map(_.isFullHouse) === Option(true))
    }
    "return false" in {
      assert(PokerHand.build("9S 9C 9H 4S 2H").map(_.isFullHouse) === Option(false))
      assert(PokerHand.build("2S 3H 4H 5S 6C").map(_.isFullHouse) === Option(false))
    }
  }

  "PokerHand.isFlush" should {
    "return true" in {
      assert(PokerHand.build("TS 3S 4S 5S 6S").map(_.isFlush) === Option(true))
      assert(PokerHand.build("2D AD 3D QD 5D").map(_.isFlush) === Option(true))
    }
    "return false" in {
      assert(PokerHand.build("9S AS 3S 4S 2H").map(_.isFlush) === Option(false))
      assert(PokerHand.build("2S 3H 4H 5S 6C").map(_.isFlush) === Option(false))
    }
  }

  "PokerHand.isStraight" should {
    "return true" in {
      assert(PokerHand.build("2S 3H 4H 5S 6C").map(_.isStraight) === Option(true))
      assert(PokerHand.build("2D AC 3H 4H 5S").map(_.isStraight) === Option(true))
      assert(PokerHand.build("7D 6C 3H 4H 5S").map(_.isStraight) === Option(true))
      assert(PokerHand.build("KS AS TS QS JH").map(_.isStraight) === Option(true))
    }
    "return false" in {
      assert(PokerHand.build("KS AS 3S 4S 2H").map(_.isStraight) === Option(false))
      assert(PokerHand.build("KS AS TD QS 2H").map(_.isStraight) === Option(false))
    }
  }

  "PokerHand.isThreeOfAKind" should {
    "return true" in {
      assert(PokerHand.build("AH AC 5H 6H AS").map(_.isThreeOfAKind) === Option(true))
      assert(PokerHand.build("2D AD 2C QD 2H").map(_.isThreeOfAKind) === Option(true))
      assert(PokerHand.build("2D TD TC QD TH").map(_.isThreeOfAKind) === Option(true))
    }
    "return false" in {
      assert(PokerHand.build("9S AS 3S 4S AH").map(_.isThreeOfAKind) === Option(false))
      assert(PokerHand.build("2S 3H 4H 4S 6C").map(_.isThreeOfAKind) === Option(false))
    }
  }

  "PokerHand.isTwoPairs" should {
    "return true" in {
      assert(PokerHand.build("AH AC 5H QH QS").map(_.isTwoPairs) === Option(true))
      assert(PokerHand.build("2S 2H 4H 5S 4C").map(_.isTwoPairs) === Option(true))
      assert(PokerHand.build("2D TD TC 2C 8H").map(_.isTwoPairs) === Option(true))
    }
    "return false" in {
      assert(PokerHand.build("9S AS 3S 4S AH").map(_.isTwoPairs) === Option(false))
      assert(PokerHand.build("2S 3H 4H 4S 6C").map(_.isTwoPairs) === Option(false))
    }
  }

  "PokerHand.isPair" should {
    "return true" in {
      assert(PokerHand.build("AH AC 5H TH QS").map(_.isPair) === Option(true))
      assert(PokerHand.build("2S 2H 8H 5S 4C").map(_.isPair) === Option(true))
      assert(PokerHand.build("2D TD TC 3C QH").map(_.isPair) === Option(true))
      assert(PokerHand.build("8D TD KC 3C 8H").map(_.isPair) === Option(true))
    }
    "return false" in {
      assert(PokerHand.build("9S AS 3S 4S TH").map(_.isPair) === Option(false))
      assert(PokerHand.build("2S 3H QH 4S 6C").map(_.isPair) === Option(false))
    }
  }

  "Card.sort" should {
    "sort cards by CardValue.rank" in {
      val input: Seq[PokerHand] = Seq[Option[PokerHand]](
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

      val expected: Seq[PokerHand] = Seq[Option[PokerHand]](
        PokerHand.build("AS KS QS JS TS"),
        PokerHand.build("6H 5H 4H 3H 2H"),
        PokerHand.build("AS AD AC AH JD"),
        PokerHand.build("JS JD JC JH 3D"),
        PokerHand.build("AH AS AC 2S 2H"),
        PokerHand.build("AS 8S 4S 3S 2S"),
        PokerHand.build("7H 6H 5H 3H 2H"),
        PokerHand.build("6C 5S 4H 3H 2S"),
        PokerHand.build("AC 5S 4H 3H 2D"),
        PokerHand.build("AH AC AS 6H 5H"),
        PokerHand.build("5S 4H 4C 2S 2H"),
        PokerHand.build("AH AC 7S 6H 5H"),
        PokerHand.build("AH AC 7S 6H 4H"),
        PokerHand.build("AH KC 5S 4H 2S"),
        PokerHand.build("9C 7S 6H 3H 2S")
      ).flatten

      val actual = input.map(PokerHand.withCardsSorted)

      assert(actual.map(_.print()) === expected.map(_.print()))
    }
  }
}
