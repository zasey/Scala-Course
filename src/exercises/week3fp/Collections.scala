package exercises.week3fp

object Collections extends App {

  val progLanguages = List("java", "scala", "python")
  val lngAbbrev = List("JA", "SCA", "PY")

  val out = for {
    lng <- progLanguages
    abrv <- lngAbbrev if lng.substring(0,2).toUpperCase == abrv.substring(0,2)
  } yield (abrv, lng)

  println(out)

  val sampleTuple = new Tuple2(2, "Hello, World")
  println(sampleTuple.copy(_2 = "Scala").swap._1 + 5)

  val list = List(1, 2, 3)
  val doubler = (x: Int) => List(x, x * 2)
  println(list.map(doubler))

  val nums1 = List(1, 2, 3)
  val nums2 = List(4, 6, 7)
  val nums3 = List(10, 100, 1000)
  val result = for {
    a <- nums1
    b <- nums2 if b % 2 == 1
    c <- nums3
  } yield (a + b) * c
  println(result)
}
