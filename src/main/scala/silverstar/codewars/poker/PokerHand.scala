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
case object Ace   extends CardValue(14, 'A')

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

final class PokerHand(cards: Seq[Card]) extends Printable with Ranked {
  override def print(): String = cards.map(_.print()).mkString(" ")
  val cardsSorted: Seq[Card]   = cards.sorted(Card.ordering)

  override def rank: Int = cards.foldLeft(0)(_ + _.value.rank) // not that simple

  lazy val isPair: Boolean = cardsSorted match {
    case Seq(a, b, c, d, e) =>
      a.value.rank === b.value.rank || b.value.rank === c.value.rank || c.value.rank === d.value.rank || d.value.rank === e.value.rank
    case _ => false
  }

  lazy val isTwoPairs: Boolean = cardsSorted match {
    case Seq(a, b, c, d, e) =>
      (a.value.rank === b.value.rank && c.value.rank === d.value.rank) ||
        (b.value.rank === c.value.rank && d.value.rank === e.value.rank) ||
        (a.value.rank === b.value.rank && d.value.rank === e.value.rank)
    case _ => false
  }

  lazy val isThreeOfAKind: Boolean = cardsSorted match {
    case Seq(a, b, c, d, e) =>
      (a.value.rank === b.value.rank && b.value.rank === c.value.rank) ||
        (b.value.rank === c.value.rank && c.value.rank === d.value.rank) ||
        (c.value.rank === d.value.rank && d.value.rank === e.value.rank)
    case _ => false
  }

  lazy val isStraight: Boolean = cardsSorted match {
    case Seq(Card(Ace, _), Card(Five, _), Card(Four, _), Card(Three, _), Card(Two, _)) => true
    case Seq(a, b, c, d, e) =>
      e.value.rank + 1 === d.value.rank &&
        d.value.rank + 1 === c.value.rank &&
        c.value.rank + 1 === b.value.rank &&
        b.value.rank + 1 === a.value.rank
    case _ => false
  }

  lazy val isFlush: Boolean = cards match {
    case Seq(Card(_, a), Card(_, b), Card(_, c), Card(_, d), Card(_, e)) => a === b && b === c && c === d && d === e
    case _                                                               => false
  }

  lazy val isFullHouse: Boolean = cardsSorted match {
    case Seq(a, b, c, d, e) =>
      (a.value.rank === b.value.rank && b.value.rank === c.value.rank && d.value.rank === e.value.rank) ||
        (a.value.rank === b.value.rank && c.value.rank === d.value.rank && d.value.rank === e.value.rank)
    case _ => false
  }

  lazy val isFourOfAKind: Boolean = cardsSorted match {
    case Seq(a, b, c, d, _)
        if a.value.rank === b.value.rank && b.value.rank === c.value.rank && c.value.rank === d.value.rank =>
      true
    case Seq(_, a, b, c, d)
        if a.value.rank === b.value.rank && b.value.rank === c.value.rank && c.value.rank === d.value.rank =>
      true
    case _ => false
  }

  lazy val isStraightFlush: Boolean = isStraight && isFlush

  lazy val isRoyalFlush: Boolean = {
    cardsSorted match {
      case Seq(Card(Ace, _), Card(King, _), Card(Queen, _), Card(Jack, _), Card(Ten, _)) => isFlush
      case _                                                                             => false
    }
  }
}

object PokerHand {

  private val HAND_LENGTH: Int = 5

  def build(s: String): Option[PokerHand] = s.split(" ").flatMap(Card.build) match {
    case a if a.length === HAND_LENGTH => Option(new PokerHand(a.toSeq))
    case _                             => Option.empty[PokerHand]
  }

  def withCardsSorted(self: PokerHand): PokerHand = new PokerHand(self.cardsSorted)

  val ordering: Ordering[PokerHand] = (x: PokerHand, y: PokerHand) => (x, y) match {
    case (xx, yy) if xx.isRoyalFlush && yy.isRoyalFlush => 0
    case (xx, _) if xx.isRoyalFlush => 1
    case (xx, yy) if xx.isStraightFlush && yy.isStraightFlush => xx.rank.compare(yy.rank)
    case (xx, _) if xx.isStraightFlush => 1
    case (xx, yy) if xx.isFourOfAKind && yy.isFourOfAKind => xx.rank.compare(yy.rank)
    case (xx, _) if xx.isFourOfAKind => 1
    case (xx, yy) => xx.rank.compare(yy.rank)
  }
}
