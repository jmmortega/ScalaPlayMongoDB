package MongoDB

import com.mongodb.casbah.{MongoCollection, MongoClient}
import scala.collection.mutable
import com.mongodb.{DBCollection, DBObject}
import model.Article
import play.api.libs.json.JsValue


object MongoDBService {

  var server : String = "localhost"
  var port : Int = 27017

  def getCollections(dataBase: String): mutable.Set[String] = {
    return MongoClient(server, port)(dataBase).getCollectionNames()
  }

  def getCollection(dataBase : String , collectionName : String) : DBCollection = {
    return MongoClient(server, port)(dataBase).getCollection(collectionName)
  }


   def Select(collectionName : String , dataBase : String) : List[DBObject]  =
   {
     var returnedList = List[DBObject]()

     for(dbobj <- MongoClient(server,port)(dataBase)(collectionName).find())
     {
       returnedList ::= dbobj
     }

     return returnedList
   }

   def Select(collectionName : String , dataBase : String , paramFind : IMongoDBModel) : List[DBObject] =
    {
      val returnedList = List[DBObject]()

      for(dbobj <- MongoClient(server,port)(dataBase)(collectionName).find())
      {
        returnedList :+ dbobj
      }

      return returnedList
    }

  def Insert(collectionName : String , dataBase : String , document : IMongoDBModel) : Boolean =
  {
    val mongoClient = MongoClient(server , port)
    return mongoClient(dataBase)(collectionName).insert(document.toMongoDBModel()).getError() == ""
  }



}
