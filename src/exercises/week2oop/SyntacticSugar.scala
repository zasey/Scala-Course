package exercises.week2oop

object SyntacticSugar extends App {

  class Person(val name: String) {
    def unary_+ : Person = new Person(s"$name NoSurname")
  }

  val person = new Person("Bob")
  println((+person).name)

}
