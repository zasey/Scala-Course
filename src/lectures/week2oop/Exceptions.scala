package lectures.week2oop

object Exceptions extends App {

  def intOrNothing(hasException: Boolean)= {
    if(hasException) throw new RuntimeException("Exception is here")
    else 200
  }

  val potentialException = try {
    intOrNothing(false)
  } catch {
    case e: RuntimeException => println("RTE is here")
  } finally {
    println("I will be there no matter what")
  }


  //val exceptionVal = throw new NullPointerException

  println(Int.MaxValue + 1)
}
