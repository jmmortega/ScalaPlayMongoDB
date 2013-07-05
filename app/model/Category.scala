package model

import MongoDB.IMongoDBModel
import com.mongodb.casbah.Imports._

class Category(id : String , parent : String = "") extends IMongoDBModel{
  var Id : String = id
  var Parent : String = parent

  def toMongoDBModel(): DBObject = {
    val builder = MongoDBObject.newBuilder

    builder += "_Id" -> id
    builder += "Parent" -> Parent

    return builder.result()
  }

  def toValues(): List[String] = Article.toValues()

}

object Category extends IMongoDBModel {

  def toMongoDBModel(): DBObject = {
      val builder = MongoDBObject.newBuilder

    builder += "_Id" -> ""
    builder += "Parent" -> ""

    return builder.result()
  }

  def toValues(): List[String] = {
    return List[String](
      "Id",
      "Parent"
    )



  }
}
