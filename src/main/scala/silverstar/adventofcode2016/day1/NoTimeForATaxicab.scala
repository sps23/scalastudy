package silverstar.adventofcode2016.day1

import scala.annotation.tailrec
import scala.math.abs

/**
  * --- Day 1: No Time for a Taxicab ---
  *
  * Santa's sleigh uses a very high-precision clock to guide its movements, and the clock's oscillator is regulated
  * by stars. Unfortunately, the stars have been stolen... by the Easter Bunny. To save Christmas, Santa needs you to
  * retrieve all fifty stars by December 25th.
  * *
  * Collect stars by solving puzzles. Two puzzles will be made available on each day in the advent calendar; the second
  * puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
  *
  * You're airdropped near Easter Bunny Headquarters in a city somewhere. "Near", unfortunately, is as close as you can
  * get - the instructions on the Easter Bunny Recruiting Document the Elves intercepted start here, and nobody had time
  * to work them out further.
  *
  * The Document indicates that you should start at the given coordinates (where you just landed) and face North.
  * Then, follow the provided sequence: either turn left (L) or right (R) 90 degrees, then walk forward the given number
  * of blocks, ending at a new intersection.
  *
  * There's no time to follow such ridiculous instructions on foot, though, so you take a moment and work out
  * the destination. Given that you can only walk on the street grid of the city, how far is the shortest path
  * to the destination?
  *
  * For example:
  * Following R2, L3 leaves you 2 blocks East and 3 blocks North, or 5 blocks away.
  * R2, R2, R2 leaves you 2 blocks due South of your starting position, which is 2 blocks away.
  * R5, L5, R5, R3 leaves you 12 blocks away.
  * How many blocks away is Easter Bunny HQ?
  *
  * --- Part Two ---
  * Then, you notice the instructions continue on the back of the Recruiting Document. Easter Bunny HQ is actually
  * at the first location you visit twice.
  * For example, if your instructions are R8, R4, R4, R8, the first location you visit twice is 4 blocks away, due East.
  * How many blocks away is the first location you visit twice?
  */
object NoTimeForATaxicab {

  def parseInput(input: String): List[Move] = input.split(',').map(_.trim).collect {
    case left if left.startsWith("L") => L(left.tail.toInt)
    case right if right.startsWith("R") => R(right.tail.toInt)
  }.toList

  def howManyBlocksAway(input: String): Int = {

    @tailrec
    def iter(tmpMoves: List[Move], tmpPosition: Position): Position = tmpMoves match {
      case Nil => tmpPosition
      case h :: t => iter(t, makeMove(tmpPosition, h))
    }

    val moves: List[Move] = parseInput(input)
    val finalPosition: Position = iter(moves, Position(0, 0, N))
    finalPosition.distance
  }

  def howManyBlocksAwayFirstVisitedTwice(input: String): Int = {

    val initialPosition = Position(0, 0, N)

    @tailrec
    def iter(tmpMoves: List[Move], tmpPosition: Position, previousPositions: List[Position]): Position = tmpMoves match {
      case Nil => initialPosition
      case h :: t =>
        val (nextPosition, prev) = makeMoveWithVisited(tmpPosition, h)
        val intersection = prev.intersect(previousPositions)
        if(intersection.nonEmpty)
          intersection.head
        else
          iter(t, nextPosition, prev ::: previousPositions)
    }

    val moves: List[Move] = parseInput(input)
    val finalPosition: Position = iter(moves, initialPosition, List.empty)
    finalPosition.distance
  }

  def makeMove(position: Position, move: Move): Position = {
    val newPosition: Position = (position.direction, move) match {
      case (N, L(d)) => Position(position.x - d, position.y, W)
      case (N, R(d)) => Position(position.x + d, position.y, E)
      case (E, L(d)) => Position(position.x, position.y + d, N)
      case (E, R(d)) => Position(position.x, position.y - d, S)
      case (S, L(d)) => Position(position.x + d, position.y, E)
      case (S, R(d)) => Position(position.x - d, position.y, W)
      case (W, L(d)) => Position(position.x, position.y - d, S)
      case (W, R(d)) => Position(position.x, position.y + d, N)
    }
    newPosition
  }

  def makeMoveWithVisited(position: Position, move: Move): (Position, List[Position]) = {
    val newPosition: (Position, List[Position]) = (position.direction, move) match {
      case (N, L(d)) => (Position(position.x - d, position.y, W), (0 until d).map(i => Position(position.x - i, position.y, N)).toList)
      case (N, R(d)) => (Position(position.x + d, position.y, E), (0 until d).map(i => Position(position.x + i, position.y, N)).toList)
      case (E, L(d)) => (Position(position.x, position.y + d, N), (0 until d).map(i => Position(position.x, position.y + i, N)).toList)
      case (E, R(d)) => (Position(position.x, position.y - d, S), (0 until d).map(i => Position(position.x, position.y - i, N)).toList)
      case (S, L(d)) => (Position(position.x + d, position.y, E), (0 until d).map(i => Position(position.x + i, position.y, N)).toList)
      case (S, R(d)) => (Position(position.x - d, position.y, W), (0 until d).map(i => Position(position.x - i, position.y, N)).toList)
      case (W, L(d)) => (Position(position.x, position.y - d, S), (0 until d).map(i => Position(position.x, position.y - i, N)).toList)
      case (W, R(d)) => (Position(position.x, position.y + d, N), (0 until d).map(i => Position(position.x, position.y + i, N)).toList)
    }
    newPosition
  }

  sealed trait Move {
    val distance: Int
  }

  case class L(distance: Int) extends Move

  case class R(distance: Int) extends Move

  sealed trait Direction

  case object N extends Direction

  case object E extends Direction

  case object S extends Direction

  case object W extends Direction

  case class Position(x: Int, y: Int, direction: Direction) {
    def distance: Int = abs(x) + abs(y)
  }

}
