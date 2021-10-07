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

//  printPokerHands(pokerHandsSorted)

  "PokerHand.build" should {
    "create only valid PokerHand" in {
      assert(pokerHandsSorted.length === 15)
    }
  }

  "PokerHand.rank" should {
    "return rank for a Pair" in {
      val hand = PokerHand.build("AH AC KH QH JS").getOrElse(EmptyHand)
      println(hand.withCardsSorted.printMore())
      assert(hand.rank === 1436)
    }
    "return rank for a TwoPair" in {
      val hand = PokerHand.build("AH AC KH KD QS").getOrElse(EmptyHand)
      println(hand.withCardsSorted.printMore())
      assert(hand.rank === 29312)
    }
    "return rank for a ThreeOfAKind" in {
      val hand = PokerHand.build("AH AC AD KD QS").getOrElse(EmptyHand)
      println(hand.withCardsSorted.printMore())
      assert(hand.rank === 210025)
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
  }

  "PokerHand.RoyalFlush" should {
    "return true" in {
      assert(PokerHand.build("KS AS TS QS JS").collect { case _: RoyalFlush => true } === Option(true))
      assert(PokerHand.build("KD TD AD QD JD").collect { case _: RoyalFlush => true } === Option(true))
    }
    "return false" in {
      assert(PokerHand.build("KS AS TS QS JH").collect { case _: RoyalFlush => true } === None)
      assert(PokerHand.build("KS AS TS QS 2S").collect { case _: RoyalFlush => true } === None)
    }
  }

  "PokerHand.StraightFlush" should {
    "return true" in {
      assert(PokerHand.build("2H 3H 4H 5H 6H").collect { case _: StraightFlush => true } === Option(true))
      assert(PokerHand.build("2D AD 3D 4D 5D").collect { case _: StraightFlush => true } === Option(true))
      assert(PokerHand.build("7S 6S 3S 4S 5S").collect { case _: StraightFlush => true } === Option(true))
    }
    "return false" in {
      assert(PokerHand.build("TS 3S 4S 5S 6S").collect { case _: StraightFlush => true } === None)
      assert(PokerHand.build("KS AS TD QS 2H").collect { case _: StraightFlush => true } === None)
      assert(PokerHand.build("2S 3H 4H 5S 7C").collect { case _: StraightFlush => true } === None)
    }
  }

  "PokerHand.FourOfAKind" should {
    "return true" in {
      assert(PokerHand.build("AS AD AC AH JD").collect { case _: FourOfAKind => true } === Option(true))
      assert(PokerHand.build("JS JD JC JH 3D").collect { case _: FourOfAKind => true } === Option(true))
    }
    "return false" in {
      assert(PokerHand.build("9S 9C 9H 4S 2H").collect { case _: FourOfAKind => true } === None)
      assert(PokerHand.build("2S 3H 4H 5S 6C").collect { case _: FourOfAKind => true } === None)
    }
  }

  "PokerHand.FullHouse" should {
    "return true" in {
      assert(PokerHand.build("2S AH 2H AS AC").collect { case _: FullHouse => true } === Option(true))
      assert(PokerHand.build("3S 3C JC JH 3D").collect { case _: FullHouse => true } === Option(true))
    }
    "return false" in {
      assert(PokerHand.build("9S 9C 9H 4S 2H").collect { case _: FullHouse => true } === None)
      assert(PokerHand.build("2S 3H 4H 5S 6C").collect { case _: FullHouse => true } === None)
    }
  }

  "PokerHand.Flush" should {
    "return true" in {
      assert(PokerHand.build("TS 3S 4S 5S 6S").collect { case _: Flush => true } === Option(true))
      assert(PokerHand.build("2D AD 3D QD 5D").collect { case _: Flush => true } === Option(true))
    }
    "return false" in {
      assert(PokerHand.build("9S AS 3S 4S 2H").collect { case _: Flush => true } === None)
      assert(PokerHand.build("2S 3H 4H 5S 6C").collect { case _: Flush => true } === None)
    }
  }

  "PokerHand.Straight" should {
    "return true" in {
      assert(PokerHand.build("2S 3H 4H 5S 6C").collect { case _: Straight => true } === Option(true))
      assert(PokerHand.build("2D AC 3H 4H 5S").collect { case _: Straight => true } === Option(true))
      assert(PokerHand.build("7D 6C 3H 4H 5S").collect { case _: Straight => true } === Option(true))
      assert(PokerHand.build("KS AS TS QS JH").collect { case _: Straight => true } === Option(true))
    }
    "return false" in {
      assert(PokerHand.build("KS AS 3S 4S 2H").collect { case _: Straight => true } === None)
      assert(PokerHand.build("KS AS TD QS 2H").collect { case _: Straight => true } === None)
    }
  }

  "PokerHand.ThreeOfAKind" should {
    "return true" in {
      assert(PokerHand.build("AH AC 5H 6H AS").collect { case _: ThreeOfAKind => true } === Option(true))
      assert(PokerHand.build("2D AD 2C QD 2H").collect { case _: ThreeOfAKind => true } === Option(true))
      assert(PokerHand.build("2D TD TC QD TH").collect { case _: ThreeOfAKind => true } === Option(true))
    }
    "return false" in {
      assert(PokerHand.build("9S AS 3S 4S AH").collect { case _: ThreeOfAKind => true } === None)
      assert(PokerHand.build("2S 3H 4H 4S 6C").collect { case _: ThreeOfAKind => true } === None)
    }
  }

  "PokerHand.TwoPairs" should {
    "return true" in {
      assert(PokerHand.build("AH AC 5H QH QS").collect { case _: TwoPairs => true } === Option(true))
      assert(PokerHand.build("2S 2H 4H 5S 4C").collect { case _: TwoPairs => true } === Option(true))
      assert(PokerHand.build("2D TD TC 2C 8H").collect { case _: TwoPairs => true } === Option(true))
    }
    "return false" in {
      assert(PokerHand.build("9S AS 3S 4S AH").collect { case _: TwoPairs => true } === None)
      assert(PokerHand.build("2S 3H 4H 4S 6C").collect { case _: TwoPairs => true } === None)
    }
  }

  "PokerHand.Pair" should {
    "return true" in {
      assert(PokerHand.build("AH AC 5H TH QS").collect { case _: Pair => true } === Option(true))
      assert(PokerHand.build("2S 2H 8H 5S 4C").collect { case _: Pair => true } === Option(true))
      assert(PokerHand.build("2D TD TC 3C QH").collect { case _: Pair => true } === Option(true))
      assert(PokerHand.build("8D TD KC 3C 8H").collect { case _: Pair => true } === Option(true))
    }
    "return false" in {
      assert(PokerHand.build("9S AS 3S 4S TH").collect { case _: Pair => true } === None)
      assert(PokerHand.build("2S 3H QH 4S 6C").collect { case _: Pair => true } === None)
    }
  }

  "PokerHand." should {
    "return true" in {
      assert(PokerHand.build("2S AH 4H 5S KC").collect { case _: HighCard => true } === Option(true))
      assert(PokerHand.build("2S 3H 6H 7S 9C").collect { case _: HighCard => true } === Option(true))
    }
    "return false" in {
      assert(PokerHand.build("9S AS 3S 4S AH").collect { case _: HighCard => true } === None)
      assert(PokerHand.build("2S 3H 4H 4S 6C").collect { case _: HighCard => true } === None)
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

      val actual = input.map(_.withCardsSorted)

      assert(actual.map(_.print()) === expected.map(_.print()))
    }
  }
}
