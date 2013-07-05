package controllers

import play.api._
import play.api.mvc._
import play.api.mvc.Result
import play.api.libs.json
import play.api.libs.json._


object Application extends Controller {
  
  def index = Action {
    Ok("Hola")
  }

  def secondChoice(name : String) = Action {
    Ok("Hola" + name)
  }

  def thirdChoice = Action {
     val value1: Client = Client(1 , "Juan")
     val value2: Client = Client(2 , "Maria")
     val value3: Client = Client(3 , "Carlos")
     val values: Vector[Client] = Vector(value1 , value2 , value3)

    //Ok(Json.toJson(values))
    Ok(Json.parse("Hola"))
  }

  case class Client(val id: Int, val name: String)
}