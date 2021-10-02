package silverstar.codewars.poker

import org.scalactic.TripleEquals._

// https://www.codewars.com/kata/5739174624fc28e188000465/train/java

trait Printable {
  def print(): String
}

trait CharEncoded extends Printable {
  val c: Char

  def print(): String = c.toUpper.toString
}

trait Ranked {
  val rank: Int
}

object Ranked {
  def ordering[T <: Ranked]: Ordering[T] = Ordering.by { r: T =>
    r.rank
  }
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

final class PokerHand(cards: Seq[Card]) extends Printable {
  override def print(): String = cards.map(_.print()).mkString(" ")
}

object PokerHand {

  private val HAND_LENGTH: Int = 5

  def build(s: String): Option[PokerHand] = s.split(" ").flatMap(Card.build) match {
    case a if a.length === HAND_LENGTH => Option(new PokerHand(a.toSeq))
    case _                             => Option.empty[PokerHand]
  }
}
