package silverstar.coursera.mongo

import org.mongodb.scala.{MongoClient, MongoDatabase}

object MongoClientApp extends  App{


  val uri: String = "mongodb+srv://admin:admin@silver-star-gpefo.azure.mongodb.net/mflix?retryWrites=true&w=majority"
//  System.setProperty("org.mongodb.async.type", "netty")
  val client: MongoClient = MongoClient(uri)
  val db: MongoDatabase = client.getDatabase("mflix")

println(db.listCollectionNames())

}
