package silverstar.adventofcode2016.day7

import scala.annotation.tailrec

/**
  * --- Day 7: Internet Protocol Version 7 ---
  * While snooping around the local network of EBHQ, you compile a list of IP addresses (they're IPv7, of course;
  * IPv6 is much too limited). You'd like to figure out which IPs support TLS (transport-layer snooping).
  * An IP supports TLS if it has an Autonomous Bridge Bypass Annotation, or ABBA. An ABBA is any four-character sequence
  * which consists of a pair of two different characters followed by the reverse of that pair, such as xyyx or abba.
  * However, the IP also must not have an ABBA within any hypernet sequences, which are contained by square brackets.
  *
  * For example:
  * - abba[mnop]qrst supports TLS (abba outside square brackets).
  * - abcd[bddb]xyyx does not support TLS (bddb is within square brackets, even though xyyx is outside square brackets).
  * - aaaa[qwer]tyui does not support TLS (aaaa is invalid; the interior characters must be different).
  * - ioxxoj[asdfgh]zxcvbn supports TLS (oxxo is outside square brackets, even though it's within a larger string).
  * How many IPs in your puzzle input support TLS?
  *
  * --- Part Two ---
  * You would also like to know which IPs support SSL (super-secret listening).
  * An IP supports SSL if it has an Area-Broadcast Accessor, or ABA, anywhere in the supernet sequences
  * (outside any square bracketed sections), and a corresponding Byte Allocation Block, or BAB, anywhere in the
  * hypernet sequences. An ABA is any three-character sequence which consists of the same character twice with a
  * different character between them, such as xyx or aba. A corresponding BAB is the same characters but in reversed
  * positions: yxy and bab, respectively.
  *
  * For example:
  * - aba[bab]xyz supports SSL (aba outside square brackets with corresponding bab within square brackets).
  * - xyx[xyx]xyx does not support SSL (xyx, but no corresponding yxy).
  * - aaa[kek]eke supports SSL (eke in supernet with corresponding kek in hypernet;
  *   the aaa sequence is not related, because the interior character must be different).
  * - zazbz[bzb]cdb supports SSL (zaz has no corresponding aza, but zbz has a corresponding bzb,
  *   even though zaz and zbz overlap).
  * How many IPs in your puzzle input support SSL?
  */
object InternetProtocolVersion7 {

  def howManyIPv7SupportTLS(s: String): Int = {
    val split = s.split("\r\n")
    split.map(parseIPv7).count(_.tlsSupport)
  }

  def howManyIPv7SupportSSL(s: String): Int = {
    val split = s.split("\r\n")
    split.map(parseIPv7).count(_.sslSupport)
  }

  @tailrec
  def hasABBA(s: String): Boolean = s.toList match {
    case a1 :: b1 :: b2 :: a2 :: t =>
      if (a1 != b1 && a1 == a2 && b1 == b2) true
      else hasABBA((b1 :: b2 :: a2 :: t).mkString)
    case _ => false
  }

  def getABA(s: String): List[ABA] = s.sliding(3).flatMap(toABA).toList

  def toABA(s: String): Option[ABA] = s.toList match {
    case List(a, b, c) if a == c && a != b => Option(ABA(s.head, s.tail.head))
    case _ => None
  }

  def parseIPv7(ip: String): IPv7 = {

    @tailrec
    def iter(s: List[Char], supernets: List[String], hypernets: List[String], tmp: String): IPv7 = s match {
      case List() => IPv7((tmp :: supernets).reverse, hypernets.reverse)
      case '[' :: t => iter(t, tmp :: supernets, hypernets, "")
      case ']' :: t => iter(t, supernets, tmp :: hypernets, "")
      case a :: t => iter(t, supernets, hypernets, tmp + a)
    }

    iter(ip.toList, List(), List(), "")
  }

  case class IPv7(supernets: List[String], hypernets: List[String]) {
    def tlsSupport: Boolean = !hypernets.exists(hasABBA) && supernets.exists(hasABBA)

    def sslSupport: Boolean = {
      val abaFromHypernets: List[ABA] = hypernets.flatMap(getABA)
      val babToCheck: List[String] = abaFromHypernets.map(_.getBAB)
      val v: Boolean = supernets.exists(sn => babToCheck.exists(b => sn.contains(b)))
      v
    }
  }

  case class ABA(a: Char, b: Char) {
    def getABA: String = Array(a, b, a).mkString

    def getBAB: String = Array(b, a, b).mkString
  }
}
