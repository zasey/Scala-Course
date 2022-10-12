package lectures.week2oop

object CaseClasses extends App {

  case class Person(name: String, occupation: String)

  val bob = Person("Bob", "Developer")

  val anotherBob = bob.copy()
  println(bob) // Person(Bob,Developer)
  println(anotherBob) // Person(Bob,Developer)

  val bobsTwin = bob.copy(name = "John")
  println(bobsTwin) // Person(John,Developer)

  val alice = Person("Alice", "Engineer") // метод apply в действии


}
