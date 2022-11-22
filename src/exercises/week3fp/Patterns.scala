package exercises.week3fp

import scala.io.StdIn.readLine

object Patterns extends App{

  def guard(data: Any, maxLength: Int) : String = {
    data match {
      case list: List[Any] if(list.length <= maxLength) => "Задан список List допустимой длины"
      case list: List[Any] if(list.length > maxLength) => "Длина списка больше максимально допустимого значения"
      case list: String    if(list.length <= maxLength) => "Длина строки не превышает максимально допустимого значения"
      case list: String    if(list.length > maxLength) => "Получена строка недопустимой длины"
      case _ => "Что это? Это не строка и не список"
    }
  }



    println(guard(List(1,2,3), 3))
    println(guard(List(1,2,3), 2))
    println(guard("Строка", 6))
    println(guard("Строка", 3))
    println(guard(42, 3))



  for (i <- 1 to 5) {
    i match {
      // здесь пропущен код
      //case 1 => println(s"$i - нечетное число")
      //case 2 => println(s"$i - четное число")
      //case 3 => println(s"$i - нечетное число")
      //case 4 => println(s"$i - четное число")
      //case 5 => println(s"$i - нечетное число")

      case i if (i % 2 == 0) => println(s"$i - четное число")
      case i if (i % 2 == 1) => println(s"$i - нечетное число")
    }
  }

  val fullname = readLine()
  val person = if(fullname != null) s"${fullname.split(" ")(0).head}. ${fullname.split(" ")(1).head}." else ""

  person match {
    case str:String if(person.length == 5) => println(person)
    case _ => println("Oops, something is wrong")
  }

}
