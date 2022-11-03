package lectures.week3fp

object Options extends App {

  def unsafeMethod(): String = null
  def safeMethod(): String = "There is a String"

  val unsafeRes = Option(unsafeMethod())
  val safeRes = Option(safeMethod())
  println(unsafeRes) // None
  println(safeRes) // Some(There is a String)

  val someOption: Option[String] = Some("Success")
  val noneOption: Option[Int] = None
  println(someOption.isEmpty) // false
  println(noneOption.isEmpty) // true

  
  def maybeSafeMethod(): String = "There is no harm"

  val chainedResult = Option(unsafeMethod()).orElse(Option(maybeSafeMethod()))
  println(chainedResult) // Some(There is no harm)
}
