package exercises.week3fp

object FunctionsFP extends App {
  val strlen = (str: String) => str.length
  println(strlen("asdasf")) // 6

  //////////////////////////////////////////////

  def someFunc1: Int => Function1[Int, Int] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x * y
    }
  }

  def someFunc: Int => Int => Int = x => y => x * y
  val mult = someFunc1(2)
  println(mult(5)) // 10
  println(someFunc(2)(5)) // 10
}
