package lectures.week1basics

object TypesValuesVariables extends App {
  var aString : String = "Hello"
  aString = "Hello, world!"
  val aFloat = 1.23F
  val aDouble = 1.23
  val aInt = 123
  val aLong = 123L
  println(aString)
  println(aInt.getClass)
  println(aLong.getClass)
}
