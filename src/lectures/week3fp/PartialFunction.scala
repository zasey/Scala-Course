package lectures.week3fp

object PartialFunction extends App{

  val aPartialFunction: PartialFunction[String, String] = {
    case "mon" => "Work!"
    case "fri" => "Party Time"
    case "sun" => "Relax a little"
  }

  println(aPartialFunction.isDefinedAt("tue")) // false

  val lifted = aPartialFunction.lift // теперь на выходе имеем тип Option[String]

  println(lifted("fri")) // Some(Party Time)
  println(lifted("thu")) // None

  val pfChain: PartialFunction[String, String] = aPartialFunction.orElse[String, String] {
    case "sat" => "It`s just a Saturday"
  }

  println(pfChain("sat")) //It`s just a Saturday


}
