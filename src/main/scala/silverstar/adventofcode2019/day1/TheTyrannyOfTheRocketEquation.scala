package silverstar.adventofcode2019.day1

object TheTyrannyOfTheRocketEquation {

  private def fuelRequired(mass: Long): Long = (mass / 3) - 2

  def sumOfFuelRequirements(mass: Seq[Long]): Long = mass.foldLeft(0L)((acc, m) => acc + fuelRequired(m))

  def sumOfFuelRequirementsCorrection(mass: Seq[Long]): Long = {

    @scala.annotation.tailrec
    def iter(m: Long, sum: Long): Long = {
      val fuelReq: Long = fuelRequired(m)
      if (fuelReq <= 0) sum
      else iter(fuelReq, sum + fuelReq)
    }

    mass.foldLeft(0L)((acc, m) => acc + iter(m, sum = 0L))
  }
}
