package silverstar.modeling

case class DataRow(dataPoints: Seq[DataPoint]) {
  val dataPointsByNameMap: Map[String, DataPoint] = dataPoints.map(dp => dp.name -> dp).toMap

  def getByName(name: String): Option[DataPoint] = dataPointsByNameMap.get(name)
}
