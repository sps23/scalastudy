package silverstar.adventofcode2019.day1

object TheTyrannyOfTheRocketEquation {

  private def fuelRequired(mass: Long): Long = (mass / 3) - 2

  private def fuelRequirements(mass: Seq[Long], func: Long => Long): Long =
    mass.foldLeft(0L)(_ + func(_))

  def sumOfFuelRequirements(mass: Seq[Long]): Long = fuelRequirements(mass, fuelRequired)

  def sumOfFuelRequirementsCorrection(mass: Seq[Long]): Long = {

    @scala.annotation.tailrec
    def fuelRequiredCorrection(sum: Long)(m: Long): Long = {
      val fuelReq: Long = fuelRequired(m)
      if (fuelReq <= 0) sum
      else fuelRequiredCorrection(sum + fuelReq)(fuelReq)
    }

    fuelRequirements(mass, fuelRequiredCorrection(sum = 0L))
  }
}
