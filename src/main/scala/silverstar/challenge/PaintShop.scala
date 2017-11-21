package silverstar.challenge

import java.io.{File, FileInputStream, InputStream}

import scala.annotation.tailrec
import scala.io.Source

/**
  * You run a paint shop, and there are a few different colors of paint you can prepare.
  * Each color can be either "gloss" or "matte".
  * *
  * You have a number of customers, and each have some colors they like, either gloss or matte.
  * No customer will like more than one color in matte.
  * *
  * You want to mix the colors, so that:
  * - There is just one batch for each color, and it's either gloss or matte.
  * - For each customer, there is at least one color they like.
  * - You make as few mattes as possible (because they are more expensive).
  * *
  * Your program should accept an input file as a command line argument, and print a result to standard out.
  * *
  * An example input file is:
  * 5
  * 1 M 3 G 5 G
  * 2 G 3 M 4 G
  * 5 M
  * *
  * The first line specifies how many colors there are.
  * Each subsequent line describes a customer. For example, the first customer likes color 1 in matte,
  * color 3 in gloss and color 5 in gloss.
  * *
  * Your program should read an input file like this, and print out either that it is impossible to satisfy all
  * the customer, or describe, for each of the colors, whether it should be made gloss or matte.
  * *
  * The output for the above file should be:
  * G G G G M
  * *
  * ...because all customers can be made happy by every paint being prepared as gloss except number 5.
  * *
  * An example of a file with no solution is:
  * 1
  * 1 G
  * 1 M
  * *
  * Your program should print
  * No solution exists
  */
object PaintShop {

  case class Order(numberOfColors: Int, customerPreferences: List[CustomerPreference])

  case class CustomerPreference(colors: List[Paint]) {
    def isSingle: Boolean = colors.length == 1
  }

  case class Paint(color: Int, style: PaintStyle)

  abstract case class PaintStyle(value: Char)

  object PaintStyle {
    def apply(value: Char): PaintStyle = value match {
      case 'G' => Gloss
      case 'M' => Matt
      case _ => throw new UnsupportedOperationException
    }
  }

  object Gloss extends PaintStyle('G')

  object Matt extends PaintStyle('M')

  def satisfyOrder(order: Order): List[PaintStyle] = {
    def fillInWithDefault(s: List[Paint]): List[PaintStyle] = {
      val grouped: Map[Int, List[Paint]] = s.groupBy(_.color)
      (1 to order.numberOfColors).toList.map(i => if (grouped.contains(i)) grouped(i).head.style else Gloss)
    }

    @tailrec
    def checkMultiples(s: List[Paint], m: List[CustomerPreference]): List[PaintStyle] = {
      m match {
        case List() =>
          if (s.length == order.numberOfColors) s.sortBy(_.color).map(_.style)
          else fillInWithDefault(s)
        case h :: t =>
          if (h.colors.intersect(s).isEmpty) { //there is no match with paint discovered from singles
            if (s.length == order.numberOfColors) List() // all the paints were discovered from singes
            else {
              val withoutSingles = h.colors.filterNot(p => s.map(_.color).contains(p.color))
              println(s"withoutSingles=${withoutSingles.mkString(";")}")
              ???
            }
          }
          else checkMultiples(s, t)
      }
    }

    val (singles, multiples) = order.customerPreferences.partition(_.isSingle)
    val singlePaints: List[Paint] = singles.distinct.map(_.colors.head) // no choice here
    println(s"singlePaints=${singlePaints.mkString(";")}")
    val hasDuplicates = singlePaints.groupBy(_.color).exists(_._2.length > 1)
    if (hasDuplicates) List()
    else checkMultiples(singlePaints, multiples)
  }

  def parseInput(inputStream: InputStream): Order = {
    val lines = Source.fromInputStream(inputStream).getLines()
    val numberOfColors = lines.next().toInt
    val p = lines.toList.map(l => {
      val paints = l.split(" ").grouped(2).toList.map(g => Paint(g(0).toInt, PaintStyle.apply(g(1).charAt(0))))
      CustomerPreference(paints)
    })
    Order(numberOfColors, p)
  }
}
