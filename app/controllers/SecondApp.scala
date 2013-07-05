package controllers

import play.api._
import play.api.mvc._
import play.api.mvc.Controller

import play.api.libs.json._

object SecondApp extends Controller {

  def FirstTest = Action {
    //val jsonObject = Json.obj("Welcome" -> JsString("Hola"))
    val jsonObject = JsString("Hola")

    Ok(jsonObject)
  }

  def SecondTest(name : String) = Action {
    val jsonObject = JsString("Hola " + name)

    Ok(jsonObject).as("application/json")
  }

  def ThirdTest(name : String , edad : Long) = Action {
    val jsonObject = JsString("Hola " + name + " tu edad es: " + edad.toString())
    val jsonObject2 = JsString("Hola " + name + " tu edad es: " + (edad + 1).toString())
    val jsonObject3 = JsString("Hola " + name + " tu edad es: " + (edad + 2).toString())

    val jsonArray = JsArray(Seq[JsValue](jsonObject , jsonObject2 , jsonObject3))

    //Ok(jsonObject)
    Ok(jsonArray)

  }


}
