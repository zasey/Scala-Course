package exercises.week1basics

import scala.annotation.tailrec

object Recursion {

  def powerOfTwo(pow: Int): BigInt = {
    @tailrec
    def loop(i: Int, acc: BigInt = 1): BigInt = {
      if (i <= 0) acc
      else loop(i - 1, acc * 2)
    }

    loop(pow)
  }

  def increas(x: Int, y: Int, n: Int): Int = {
    @tailrec
    def loop(i: Int, acc: Int = x): Int = {
      if(i <= 0) acc
      else loop(i - 1, acc + y)
    }
    loop(n)
  }

  def printResult(number: Int): Unit = {
    @tailrec
    def loop(i: Int, acc: Int = number): Unit = {
      print(acc + " ")
      if(i <= 1) return
      else loop(i - 1, acc)
    }
    loop(number.toString.length)
    println("is the result")
  }

  def reverseString(string: String): String = {
    val str = string.replaceAll("\\s{2,}", " ").split(" ").reverse

    @tailrec
    def loop(i: Int = 0, acc: String=""): String = {
      if(i == str.length) acc
      else if(i == str.length - 1) loop(i + 1, acc :++ str(i))
      else loop(i + 1, acc :++ str(i) :+ ' ')
    }

    loop()
  }

  def main(args: Array[String]): Unit ={

 //   println(powerOfTwo(2))
//    val x = args[0]
//    val y = args[1]
//    val n = args[2]


    printResult(increas(10,1,2))
    println(reverseString("I like     Scala"))
  }



}
