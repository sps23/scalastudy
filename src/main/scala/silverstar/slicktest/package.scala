package silverstar

import slick.lifted.TableQuery

package object slicktest {

  lazy val stringDataPoints = TableQuery[StringDataPoints]
  lazy val dateDataPoints   = TableQuery[DateDataPoints]
  lazy val doubleDataPoints = TableQuery[DoubleDataPoints]
  lazy val trades           = TableQuery[Trades]
}
