package silverstar.adventofcode2019.day1

object TheTyrannyOfTheRocketEquation {

  def sumOfFuelRequirements(mass: Seq[Long]): Long = mass.foldLeft(0L)((acc, m) => acc + (m / 3) - 2)
}
