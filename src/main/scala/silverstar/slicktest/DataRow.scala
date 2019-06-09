package silverstar.slicktest

case class DataRow(trade: Trade, dataPoints: Seq[DataPoint])

object DataRow {

  def dataRowsFrom(tradesMap: Map[Trade, Seq[(Trade, DataPoint)]]): Seq[DataRow] = {
    (tradesMap map {
      case (trade, tuples) => DataRow(trade, tuples.map(_._2))
    }).toSeq
  }
}
