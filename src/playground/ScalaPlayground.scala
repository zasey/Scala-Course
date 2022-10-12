package playground

object ScalaPlayground extends App {

  abstract class Class {
    def func(x: Int) : String
  }

  val x = {
    "text"
  }

  object A {
    val a: String = "value a"
    println("object A")
  }

  val aValo = A
  val anotherValo = A

  //println(aValo.a)
  //println(anotherValo.a)

  class A(val a: String) {
    println("class A")
  }

  val aVal = new A("val")
  val anotherVal = new A("another val")

  println(aVal.a)
  println(anotherVal.a)

  val str : String = "I like     Scala"
  val words = str.replaceAll("\\s{2,}", " ")
  println(str)
  println(words)

}
