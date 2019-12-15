package silverstar.adventofcode2019.day1

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import silverstar.adventofcode2019.day1.TheTyrannyOfTheRocketEquation.sumOfFuelRequirements

class TheTyrannyOfTheRocketEquationTest extends AnyFunSpec with Matchers {

  describe("sumOfFuelRequirements1") {
    val testData: Seq[(Seq[Long], Long)] = Seq(
      (Seq(12L), 2L),
      (Seq(14L), 2L),
      (Seq(1969L), 654L),
      (Seq(100756L), 33583L)
    )

    testData.foreach(t => {
      it(s"should return '${t._2}' for '${t._1}'") {
        sumOfFuelRequirements(t._1) shouldBe t._2
      }
    })

    val example = Seq(
      118602L, 60644L, 136064L, 134771L, 62530L, 129043L, 120233L, 126092L, 112839L, 86210L, 132501L, 75894L, 109369L,
      83641L, 92700L, 64983L, 90418L, 130659L, 92555L, 104100L, 121330L, 87819L, 63021L, 138752L, 108491L, 113214L,
      136107L, 55602L, 131025L, 90689L, 132480L, 134220L, 135760L, 148945L, 57010L, 115909L, 67605L, 108478L, 111094L,
      129875L, 102541L, 133169L, 76547L, 113079L, 126981L, 81066L, 104994L, 134551L, 61053L, 136512L, 67895L, 127712L,
      58077L, 107426L, 115178L, 99316L, 64532L, 107293L, 129534L, 114100L, 88382L, 133621L, 93803L, 107214L, 75795L,
      51422L, 50876L, 98171L, 121970L, 92130L, 89814L, 130753L, 58561L, 61666L, 144353L, 142168L, 143592L, 94461L,
      116978L, 135420L, 88165L, 97926L, 114772L, 143455L, 53613L, 60408L, 94299L, 98996L, 142167L, 78063L, 98974L,
      65392L, 140263L, 126726L, 141285L, 111074L, 95977L, 124871L, 136636L, 81935L
    )

    it("should return result for example data") {
      sumOfFuelRequirements(example) shouldBe 3479429L
    }
  }
}
