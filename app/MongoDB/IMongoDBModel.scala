package MongoDB

import com.mongodb._
import com.mongodb.casbah.Imports._
import play.api.libs.json._
import java.util

trait IMongoDBModel {

    def toMongoDBModel() : DBObject
    def toValues() : List[String]

    def toJson() : JsValue =
    {
      return Json.toJson(this.toMongoDBModel().toString())
    }









}
