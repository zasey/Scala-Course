package lectures.week3fp

import scala.annotation.tailrec

object FunctionsFP extends App {

  val product = new Function2[Int, Int, Int] {
    override def apply(x: Int, y: Int): Int = x * y
  }

  val product2 = (x: Int, y: Int) => x * y

  val product3: (Int, Int) => Int = _ * _

  println(product(3, 4) ) // выводит 12

  ///////////////////////////////////////////////////////////////

  @tailrec
  def nTimes(f: Int => Int, x: Int,  n: Int): Int = {
    if (n <= 0) x
    else nTimes(f, f(x), n - 1)
  }
  val increment = (x: Int) => x + 1
  println(nTimes(increment, 0, 3)) // выведет 3

  def curryingNTimes(f: Int => Int, n: Int): Int => Int = {
    if (n <= 0) (x: Int) => x // лямбда-функция, что просто берет и возвращает значение
    else (x: Int) => curryingNTimes(f, n-1)(f(x))
  }
  println(curryingNTimes(increment, 3)(0)) // выведет 3

}
