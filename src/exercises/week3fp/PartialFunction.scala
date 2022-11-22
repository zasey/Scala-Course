package exercises.week3fp

import scala.Option

object PartialFunction extends App{

  val chatbot: PartialFunction[String, Option[String]] =  {
    case "hello" => Some("Hi, I'm Bot")
    case "bye" => Some("Bye-bye")
    case "what's up" => Some("sup-sup")
  }

  chatbot.lift

  println(chatbot("hello"))

}
