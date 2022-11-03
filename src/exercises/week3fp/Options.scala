package exercises.week3fp

object Options extends App {

  val someValue = Some(2)
  println(someValue.map(_ * 2))

  val someVal = Some(2)
  println(someVal.filter(x => x > 10))
  println(someVal.flatMap(x => Option(x * 2)))
  println(someVal.filter(x => x > 0))
}
