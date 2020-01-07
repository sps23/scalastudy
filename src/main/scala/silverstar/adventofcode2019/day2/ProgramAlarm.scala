package silverstar.adventofcode2019.day2

object ProgramAlarm {

  sealed trait ProgramOperation {
    def opcode: Int
    def input1Index: Int
    def input2Index: Int
    def outputIndex: Int
    def run(input: Array[Int]): Array[Int]

    protected def run(input: Array[Int], operation: (Int, Int) => Int): Array[Int] = {
      val input1 = input(input1Index)
      val input2 = input(input2Index)
      input(outputIndex) = operation(input1, input2)
      input
    }
  }
  case class AddOperation(override val input1Index: Int, override val input2Index: Int, override val outputIndex: Int)
      extends ProgramOperation {
    override def opcode: Int = 1

    override def run(input: Array[Int]): Array[Int] = run(input, _ + _)
  }
  case class MultiplyOperation(override val input1Index: Int,
                               override val input2Index: Int,
                               override val outputIndex: Int)
      extends ProgramOperation {
    override def opcode: Int = 2

    override def run(input: Array[Int]): Array[Int] = run(input, _ * _)
  }
  case object TerminationOperation extends ProgramOperation {
    override val opcode: Int      = 99
    override val input1Index: Int = -1
    override val input2Index: Int = -1
    override val outputIndex: Int = -1

    override def run(input: Array[Int]): Array[Int] = input
  }

  object ProgramOperation {

    def create(input: Array[Int]): ProgramOperation = input match {
      case in if in(0) == 99 => TerminationOperation
      case Array(opcode, input1Index, input2Index, outputIndex) =>
        opcode match {
          case 1     => AddOperation(input1Index, input2Index, outputIndex)
          case 2     => MultiplyOperation(input1Index, input2Index, outputIndex)
          case other => throw new UnsupportedOperationException(s"unknown opcode: $other")
        }
      case _ => throw new UnsupportedOperationException("invalid input: " + input.mkString(","))
    }
  }

  def intcodeComputer(input: Array[Int]): Array[Int] = {

    @scala.annotation.tailrec
    def iter(in: Array[Int], index: Int): Array[Int] = {
      val slice: Array[Int] = in.slice(index, index + 4)
      val operation         = ProgramOperation.create(slice)
      operation match {
        case TerminationOperation => in
        case op                   => iter(op.run(in), index + 4)
      }
    }

    iter(input, 0)
  }

  def compute(input: Array[Int]): Int = {
    input(1) = 12
    input(2) = 2
    intcodeComputer(input)(0)
  }
}
