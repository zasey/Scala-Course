package lectures.week3fp

object Collections extends App {
  //set
  val emptySet: Set[Int] = Set()
  val aSet = Set(10,20,30,40)
  val anotherSet = Set(30,40,50,60)

  aSet.isEmpty      //false
  emptySet.isEmpty  //true

  aSet.head         //10
  aSet.tail         //Set(20,30,40)

  aSet.min          //10
  aSet.max          //40

  aSet.intersect(anotherSet)    //Set(30,40)
  aSet.&(anotherSet)            //Set(30,40)

  //aSet.++(anotherSet)
  aSet ++ anotherSet            //HashSet(10,20,60,50,40,30)


  //seq
  val aSequence = Seq(1,2,3,4)

  println(aSequence)        //List(1,3,2,4)

  aSequence.length          //4

  aSequence ++ Seq(6,7,5)   //List(1,3,2,4,6,7,5)

  aSequence.reverse         //List(4,2,3,1)
  aSequence.sorted          //List(1,2,3,4)
  aSequence(1)              //3
  aSequence.search(3)       //Found(1)


  //map
  val aMap: Map[String, Int] = Map()
  val colors: Map[String, String] =
    Map(("black", "#000000"), "blue" -> "#0d1ad1", ("Blue", "#161f96")).withDefaultValue("na")

  println(colors)           //Map(black -> #000000, blue -> #0d1ad1, Blue -> #161f96)
  println(colors.contains("black"))     //true
  println(colors("black"))  //#000000
  println(colors("red"))    //na

  val color: (String, String) = "green" -> "#339616"
  val newColors: Map[String, String] = colors + color

  println(newColors)    //Map(black -> #000000, blue -> #0d1ad1, Blue -> #161f96, green -> #339616)

  println(colors.toList)  //List((black,#000000),(...),(...))
  println(List(("White", "#ffffff")).toMap)   //Map(White -> #ffffff)
}
