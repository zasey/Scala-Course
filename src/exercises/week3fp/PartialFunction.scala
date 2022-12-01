package exercises.week3fp

import scala.Option

object PartialFunction extends App{

  val pf: PartialFunction[String, String] =  {
    case "hello" => "Hi, I'm Bot"
    case "bye" => "Bye-bye"
    case "what's up" => "sup-sup"
  }

  val chatbot = pf.lift

  println(chatbot("hello"))
  println(chatbot("asfas"))

}
