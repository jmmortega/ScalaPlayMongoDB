package model

import MongoDB.{IMongoDBModel}
import com.mongodb.casbah.Imports._



  class Article(id : String , name : String , price : Double = 0.0, qty : Double = 0.0, categ : String = "") extends IMongoDBModel{
  //Overload
  def this(id : String) = this(id , "")

  var Name : String = name
  var Price : Double = price
  var Qty : Double = qty
  var Categ : String = categ


  def toMongoDBModel(): DBObject = {

    val builder = MongoDBObject.newBuilder

    builder += "Name" -> name
    builder += "Price" -> price
    builder += "Qty" -> qty
    builder += "Categ" -> categ

    return builder.result()
  }

  def toValues(): List[String] = Article.toValues()
}

object Article extends IMongoDBModel {

  def toValues() : List[String] = {
    return List[String](
      "Name",
      "Price",
      "Qty",
      "Categ"
    )
  }

  def toMongoDBModel(): DBObject =
  {
    val builder = MongoDBObject.newBuilder

      for(value <- this.toValues())
      {
        builder += value -> ""
      }

    return builder.result()
  }
}
