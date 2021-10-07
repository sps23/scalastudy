package silverstar.codewars.poker

import org.scalactic.TripleEquals._

// https://www.codewars.com/kata/5739174624fc28e188000465/train/java

trait Printable {
  def print(): String
}

trait CharEncoded extends Printable {
  def c: Char

  def print(): String = c.toUpper.toString
}

trait Ranked {
  def rank: Int
}

object Ranked {
  def ordering[T <: Ranked]: Ordering[T] =
    Ordering.by { r: T =>
      r.rank
    }.reverse
}

trait CharEncodedFactory[T <: CharEncoded] {

  def values: IndexedSeq[T]

  def build(c: Char): Option[T] = values.find(_.c === c.toUpper)
}

sealed abstract class CardSuit(override val c: Char) extends CharEncoded

case object Clubs    extends CardSuit('C')
case object Diamonds extends CardSuit('D')
case object Hearts   extends CardSuit('H')
case object Spades   extends CardSuit('S')

object CardSuit extends CharEncodedFactory[CardSuit] {

  def values: IndexedSeq[CardSuit] = IndexedSeq(
    Clubs,
    Diamonds,
    Hearts,
    Spades
  )
}

sealed abstract class CardValue(override val rank: Int, override val c: Char) extends CharEncoded with Ranked

case object Two   extends CardValue(2, '2')
case object Three extends CardValue(3, '3')
case object Four  extends CardValue(4, '4')
case object Five  extends CardValue(5, '5')
case object Six   extends CardValue(6, '6')
case object Seven extends CardValue(7, '7')
case object Eight extends CardValue(8, '8')
case object Nine  extends CardValue(9, '9')
case object Ten   extends CardValue(10, 'T')
case object Jack  extends CardValue(11, 'J')
case object Queen extends CardValue(12, 'Q')
case object King  extends CardValue(13, 'K')
case object Ace extends CardValue(14, 'A') {
  val rankS: Int = 1
}

object CardValue extends CharEncodedFactory[CardValue] {

  val ordering = Ranked.ordering[CardValue]

  override def values: IndexedSeq[CardValue] = IndexedSeq(
    Two,
    Three,
    Four,
    Five,
    Six,
    Seven,
    Eight,
    Nine,
    Ten,
    Jack,
    Queen,
    King,
    Ace
  )
}

final case class Card(value: CardValue, suit: CardSuit) extends Printable {
  override def print(): String = value.print() + suit.print()
}

object Card {

  val ordering: Ordering[Card] = Ordering.by { card: Card =>
    card.value
  }(CardValue.ordering)

  def build(s: String): Option[Card] = s.toCharArray match {
    case Array(v, s) =>
      (CardValue.build(v), CardSuit.build(s)) match {
        case (Some(cv), Some(cs)) => Option(Card(cv, cs))
        case _                    => Option.empty[Card]
      }
    case _ => Option.empty[Card]
  }
}

sealed abstract class PokerHand(cards: Seq[Card]) extends Printable with Ranked {
  def printMore(): String
  override def print(): String = cards.map(_.print()).mkString(" ")
  val cardsSorted: Seq[Card]   = cards.sorted(Card.ordering)

//  override def rank: Int = cards.foldLeft(0)(_ + _.value.rank) // not that simple

  def withCardsSorted: PokerHand
}

case object EmptyHand extends PokerHand(Seq.empty[Card]) {
  override def printMore(): String        = s"Empty[$rank]"
  override def withCardsSorted: PokerHand = EmptyHand
  override def rank: Int                  = 0
}
class HighCard(cards: Seq[Card]) extends PokerHand(cards) {
  override def printMore(): String        = s"HighCard[$rank] ${print()}"
  override def withCardsSorted: PokerHand = new HighCard(cardsSorted)
  override def rank: Int                  = cards.map(_.value.rank).max
}
class Pair(cards: Seq[Card]) extends PokerHand(cards) {
  override def printMore(): String        = s"Pair[$rank] ${print()}"
  override def withCardsSorted: PokerHand = new Pair(cardsSorted)
  override def rank: Int =
    cardsSorted
      .groupBy(_.value.rank)
      .toSeq
      .sortBy(_._2.size)
      .foldLeft((0, 100)) {
        case ((acc, inc), (rank, Seq(_, _))) => (acc + rank * inc, inc)
        case ((acc, inc), (rank, _))         => (acc + rank, inc)
      }
      ._1
}
class TwoPairs(cards: Seq[Card]) extends PokerHand(cards) {
  override def printMore(): String        = s"TwoPairs[$rank] ${print()}"
  override def withCardsSorted: PokerHand = new TwoPairs(cardsSorted)

  override def rank: Int =
    cardsSorted
      .groupBy(_.value.rank)
      .toSeq
      .sortBy(c => (c._2.size, c._1))
      .foldLeft((0, 100)) {
        case ((acc, inc), (rank, Seq(_, _))) => (acc + rank * inc, inc * 20)
        case ((acc, inc), (rank, _))         => (acc + rank, inc)
      }
      ._1
}
class ThreeOfAKind(cards: Seq[Card]) extends PokerHand(cards) {
  override def printMore(): String        = s"ThreeOfAKind[$rank] ${print()}"
  override def withCardsSorted: PokerHand = new ThreeOfAKind(cardsSorted)
  override def rank: Int =
    cardsSorted
      .groupBy(_.value.rank)
      .toSeq
      .sortBy(c => (c._2.size, c._1))
      .foldLeft((0, 15000)) {
        case ((acc, inc), (rank, Seq(_, _, _))) => (acc + rank * inc, inc)
        case ((acc, inc), (rank, _))            => (acc + rank, inc)
      }
      ._1
}
class Straight(cards: Seq[Card]) extends PokerHand(cards) {
  override def printMore(): String        = s"Straight[$rank] ${print()}"
  override def withCardsSorted: PokerHand = new Straight(cardsSorted)
  override def rank: Int =
    300000 + (cardsSorted match {
      case Seq(Card(Ace, _), Card(Five, _), _, _, _) => Ace.rankS + Five.rank + Four.rank + Three.rank + Two.rank
      case c                                         => c.foldLeft(0)(_ + _.value.rank)
    })
}
class Flush(cards: Seq[Card]) extends PokerHand(cards) {
  override def printMore(): String        = s"Flush[$rank] ${print()}"
  override def withCardsSorted: PokerHand = new Flush(cardsSorted)
  override def rank: Int                  = 400000 + cards.foldLeft(0)(_ + _.value.rank)
}
class FullHouse(cards: Seq[Card]) extends PokerHand(cards) {
  override def printMore(): String        = s"FullHouse[$rank] ${print()}"
  override def withCardsSorted: PokerHand = new FullHouse(cardsSorted)
  override def rank: Int =
    cardsSorted
      .groupBy(_.value.rank)
      .toSeq
      .sortBy(c => (c._2.size, c._1))
      .foldLeft((0, 100)) {
        case ((acc, inc), (rank, Seq(_, _, _))) => (acc + rank * inc, inc)
        case ((acc, inc), (rank, Seq(_, _)))    => (acc + rank, inc)
      }
      ._1 + 400000
}
class FourOfAKind(cards: Seq[Card]) extends PokerHand(cards) {
  override def printMore(): String        = s"FourOfAKind[$rank] ${print()}"
  override def withCardsSorted: PokerHand = new FourOfAKind(cardsSorted)
  override def rank: Int =
    cardsSorted
      .groupBy(_.value.rank)
      .toSeq
      .sortBy(c => (c._2.size, c._1))
      .foldLeft((0, 100)) {
        case ((acc, inc), (rank, Seq(_, _, _, _))) => (acc + rank * inc, inc)
        case ((acc, inc), (rank, _))               => (acc + rank, inc)
      }
      ._1 + 500000
}
class StraightFlush(cards: Seq[Card]) extends PokerHand(cards) {
  override def printMore(): String        = s"StraightFlush[$rank] ${print()}"
  override def withCardsSorted: PokerHand = new StraightFlush(cardsSorted)
  override def rank: Int                  = 300000 + new Straight(cards).rank
}
class RoyalFlush(cards: Seq[Card]) extends PokerHand(cards) {
  override def printMore(): String        = s"RoyalFlush[$rank] ${print()}"
  override def withCardsSorted: PokerHand = new RoyalFlush(cardsSorted)
  override def rank: Int                  = 1000000
}

object PokerHand {

  private val HAND_LENGTH: Int = 5
  implicit val cardOrdering    = Card.ordering

  private def isFlush(cards: Seq[Card]): Boolean = cards.sorted match {
    case Seq(Card(_, a), Card(_, b), Card(_, c), Card(_, d), Card(_, e)) => a === b && b === c && c === d && d === e
    case _                                                               => false
  }

  private def isStraight(cards: Seq[Card]): Boolean = cards.sorted match {
    case Seq(Card(Ace, _), Card(Five, _), Card(Four, _), Card(Three, _), Card(Two, _)) => true
    case Seq(a, b, c, d, e) =>
      e.value.rank + 1 === d.value.rank &&
        d.value.rank + 1 === c.value.rank &&
        c.value.rank + 1 === b.value.rank &&
        b.value.rank + 1 === a.value.rank
    case _ => false
  }

  def build(s: String): Option[PokerHand] = s.split(" ").flatMap(Card.build) match {
    case a if a.length === HAND_LENGTH =>
      val cards       = a.toSeq
      val cardsSorted = cards.sorted
      cardsSorted match {
        case Seq(Card(Ace, _), Card(King, _), Card(Queen, _), Card(Jack, _), Card(Ten, _)) if isFlush(cardsSorted) =>
          Option(new RoyalFlush(cards))
        case cs if isStraight(cs) && isFlush(cs) => Option(new StraightFlush(cards))
        case Seq(a, b, c, d, _)
            if a.value.rank === b.value.rank && b.value.rank === c.value.rank && c.value.rank === d.value.rank =>
          Option(new FourOfAKind(cards))
        case Seq(_, a, b, c, d)
            if a.value.rank === b.value.rank && b.value.rank === c.value.rank && c.value.rank === d.value.rank =>
          Option(new FourOfAKind(cards))
        case Seq(a, b, c, d, e)
            if (a.value.rank === b.value.rank && b.value.rank === c.value.rank && d.value.rank === e.value.rank) ||
              (a.value.rank === b.value.rank && c.value.rank === d.value.rank && d.value.rank === e.value.rank) =>
          Option(new FullHouse(cards))
        case cs if isFlush(cs)    => Option(new Flush(cards))
        case cs if isStraight(cs) => Option(new Straight(cards))
        case Seq(a, b, c, d, e)
            if (a.value.rank === b.value.rank && b.value.rank === c.value.rank) ||
              (b.value.rank === c.value.rank && c.value.rank === d.value.rank) ||
              (c.value.rank === d.value.rank && d.value.rank === e.value.rank) =>
          Option(new ThreeOfAKind(cards))
        case Seq(a, b, c, d, e)
            if (a.value.rank === b.value.rank && c.value.rank === d.value.rank) ||
              (b.value.rank === c.value.rank && d.value.rank === e.value.rank) ||
              (a.value.rank === b.value.rank && d.value.rank === e.value.rank) =>
          Option(new TwoPairs(cards))
        case Seq(a, b, c, d, e)
            if a.value.rank === b.value.rank || b.value.rank === c.value.rank || c.value.rank === d.value.rank || d.value.rank === e.value.rank =>
          Option(new Pair(cards))
        case _ => Option(new HighCard(cards))
      }
    case _ => Option.empty[PokerHand]
  }

  val ordering: Ordering[PokerHand] = Ranked.ordering[PokerHand]
}
