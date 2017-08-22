package silverstar.adventofcode2016.day7

import org.scalatest.{FunSpec, Matchers}

import scala.io.Source

class InternetProtocolVersion7Spec extends FunSpec with Matchers {

  import InternetProtocolVersion7._

  describe("hasABBA") {
    val testData = Seq(
      ("abba", true),
      ("xyyx", true),
      ("aaaa", false),
      ("ioxxoj", true)
    )

    for (t <- testData) {
      it(s"should return '${t._2}' for '${t._1}'") {
        hasABBA(t._1) shouldBe t._2
      }
    }
  }

  describe("parseIPv7") {
    val testData = Seq(
      ("abba[mnop]qrst", IPv7(List("abba", "qrst"), List("mnop"))),
      ("abcd[bddb]xyyx", IPv7(List("abcd", "xyyx"), List("bddb"))),
      ("aaaa[qwer]tyui", IPv7(List("aaaa", "tyui"), List("qwer"))),
      ("ioxxoj[asdfgh]zxcvbn", IPv7(List("ioxxoj", "zxcvbn"), List("asdfgh"))),
      ("a[aa]b[bb]cc[ccc]ddd", IPv7(List("a", "b", "cc", "ddd"), List("aa", "bb", "ccc")))
    )

    for (t <- testData) {
      it(s"should return '${t._2}' for '${t._1}'") {
        parseIPv7(t._1) shouldBe t._2
      }
    }
  }

  describe("getABA") {
    val testData = Seq(
      ("zazbz", List(ABA('z', 'a'), ABA('z', 'b'))),
      ("aaada", List(ABA('a', 'd')))
    )

    for (t <- testData) {
      it(s"should return '${t._2}' for '${t._1}'") {
        getABA(t._1) shouldBe t._2
      }
    }
  }

  describe("howManyIPv7SupportTLS") {
    val testData = Seq(
      ("abba[mnop]qrst", 1),
      ("abcd[bddb]xyyx", 0),
      ("aaaa[qwer]tyui", 0),
      ("ioxxoj[asdfgh]zxcvbn", 1)
    )

    for (t <- testData) {
      it(s"should return '${t._2}' for '${t._1}'") {
        howManyIPv7SupportTLS(t._1) shouldBe t._2
      }
    }

    it("should return result for example data") {
      val input = getClass.getResourceAsStream("InternetProtocolVersion7.txt")
      val data = Source.fromInputStream(input, "UTF8").mkString
      howManyIPv7SupportTLS(data) shouldBe 110
    }
  }

  describe("howManyIPv7SupportSSL") {
    val testData = Seq(
      ("aba[bab]xyz", 1),
      ("xyx[xyx]xyx", 0),
      ("aaa[kek]eke", 1),
      ("zazbz[bzb]cdb", 1)
    )

    for (t <- testData) {
      it(s"should return '${t._2}' for '${t._1}'") {
        howManyIPv7SupportSSL(t._1) shouldBe t._2
      }
    }

    it("should return result for example data") {
      val input = getClass.getResourceAsStream("InternetProtocolVersion7.txt")
      val data = Source.fromInputStream(input, "UTF8").mkString
      howManyIPv7SupportSSL(data) shouldBe 242
    }
  }
}
