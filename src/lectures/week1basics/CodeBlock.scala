package lectures.week1basics

object CodeBlock extends App {
  val aCodeBlock = {
    val someVal = 1
    val y = 2

    if (someVal + y > 1) true else false
  }

  println(aCodeBlock) // выводит true
}
