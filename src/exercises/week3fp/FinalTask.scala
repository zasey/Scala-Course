package exercises.week3fp

object FinalTask extends App{
  val v1 = "1.1.02.03.0.1.1.2.1"
  val v2 = "1.1.2.3.0.1.1.2.1.0.0.1"


  def compare(v1: String, v2: String): Int = {
    def list1 = v1.split('.').map(num => num.toInt).toList
    def list2 = v2.split('.').map(num => num.toInt).toList

    def loop(l1: List[Int], l2: List[Int]): Int = {

      if(l1.isEmpty && l2.isEmpty) 0
      else if(l1.nonEmpty && l2.nonEmpty) {
        if(l1.head > l2.head) 1
        else if (l1.head < l2.head) -1
        else loop(l1.tail, l2.tail)
      }
      else if(l1.nonEmpty && l2.isEmpty) {
        if(l1.head > 0) 1
        else loop(l1.tail,l2)
      } else {
        if(l2.head > 0) -1
        else loop(l1,l2.tail)
      }
    }
    loop(list1, list2)
  }

  println(compare(v1,v2))

}
