package lectures.week1basics

object Expressions extends App {
  val aCondition = true
  val ifExpressionValue = if (aCondition) "True Condition" else "False Condition"
  println(ifExpressionValue) // выведет True Condition

  val aNumber = if (("string".length == 6 & 1 < 2) & ('1' +: "23" :+ '4').toInt == 1234 ) 24 else 123
  println(aNumber)

  val someVal = print("It is just a value")
  print(someVal)
}
