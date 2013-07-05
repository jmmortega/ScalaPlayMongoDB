package controllers

import play.api.mvc.{Action, Controller}
import MongoDB.MongoDBService
import play.libs.Json
import play.api.libs.json.{JsString, JsArray, JsValue}
import model.{Category, Article}


object MongoDBApp extends Controller{

  def SelectArticle(id : String = "") = Action {
    var jsonList = new JsArray()

    var jsonarray = List[JsValue]()

    if(id == "")
    {

      for(value <- MongoDBService.Select("testArticle" , "test"))
      {
        val jsonValue =
        new Article(
          value.get("_id").toString(),
          value.get("Name").toString(),
          value.get("Price").asInstanceOf[scala.Double],
          value.get("Qty").asInstanceOf[scala.Double],
          value.get("Categ").toString()
        ).toJson()

         jsonarray ::= jsonValue
        //jsonList.append(jsonValue)
      }

       //jsonList :+ MongoDBService.Select("article" , "testArticle").foreach(x => x.toJson())
    }
    else
    {
      val art = new Article(id)
      for(value <- MongoDBService.Select("testArticle" , "test" , art))
      {
        //jsonList.append(value.toJson())
        val jsonValue =
          new Article(
            value.get("id").toString(),
            value.get("name").toString(),
            value.get("price").asInstanceOf[scala.Double],
            value.get("qty").asInstanceOf[scala.Double],
            value.get("categ").toString()
          ).toJson()

        jsonList :+ jsonValue

      }
      //jsonList :+ MongoDBService.Select("testArticle" , "test" , art).foreach(x => x.toJson())
    }

    //Ok(Json.toJsonjsonList.toMap[String,AnyRef])).as("application/json")
    //Ok(jsonList).as("application/json")
    val array = JsArray(jsonarray)
    Ok(array).as("application/json")

  }

  def SelectCategory(id : String = "") = Action {
    // var jsonList : List[JsValue] = Nil
    var jsonList : JsArray = JsArray()

    if(id == "")
    {
      for(value <- MongoDBService.Select("testCategorie" , "test"))
      {
        //jsonList.append(value.toJson())
        jsonList.append(
          new Category(
          value.get("id").toString(),
          value.get("name").toString()
          ).toJson()
        )
      }
      //MongoDBService.Select("testCategorie" , "test").foreach(x => x.toJson())
    }
    else
    {
      val cat = new Category(id)
      for(value <- MongoDBService.Select("testCategorie" , "test" , cat))
      {
        jsonList.append(
          new Category(
            value.get("id").toString(),
            value.get("name").toString()
          ).toJson()
        )
        //jsonList.append(value.toJson())
      }
      //jsonList.append(MongoDBService.Select("testCategorie" , "test" , cat).foreach(x => x.toJson()))
    }

    Ok(jsonList).as("application/json")
  }

  def InsertCategory(id : String , parent : String = "") = Action {

    val cat : Category = new Category(id , parent)

    if(MongoDBService.Insert("testCategorie" , "test" , cat) == true)
    {
      Ok("").as("application/json")
    }
    else
    {
      BadRequest("").as("application/json")
    }
  }

  def InsertArticle(id : String , name : String , price : scala.Double , qty : scala.Double , categ : String) = Action {
    val art : Article = new Article(id , name , price , qty , categ)

    if(MongoDBService.Insert("testArticle" , "test" , art) == true)
    {
      Ok("").as("application/json")
    }
    else
    {
      BadRequest("").as("application/json")
    }

  }

}
