package lectures.part3fp

object TuplesAndMaps extends App {
  // tuples = finite ordered lists ~~
  val aTuple = new Tuple2(2, "hey hey") // Tuple2[Int, String]
  // same thing
  val aTuple2 = (2, "hey hey") // Tuple2[Int, String]
  val aTuple3 = 2 -> "hey hey" // Tuple2[Int, String]

  println(aTuple2._1)
  println(aTuple2._2)
  println(aTuple2.copy(_2 = "goodbye Java"))
  println(aTuple2.swap) // swap the elements

  // Maps - associate keys with values
  val aMap: Map[String, Int] = Map()
  val aPhonebook: Map[String, Int] = Map(("Jim", 5555), ("Daniel", 2222))
  // syntactic sugar for the tuple2
  val aPhonebook2: Map[String, Int] = Map("Jim" -> 5555, "Daniel" -> 2222)

  println(aPhonebook2.contains("Jim")) // true
  println(aPhonebook2("Jim")) // 5555
  // println(aPhonebook2("Mary")) // NoSuchElementException
  // solution for NoSuchElementException
  val aPhonebook3: Map[String, Int] = Map("Jim" -> 5555, "Daniel" -> 2222).withDefaultValue(-1)
  println(aPhonebook3("mary"))

  // add a pairing
  val newPhoneBook = aPhonebook3 + ("Mary" -> 1111)
  println(newPhoneBook("Mary")) //1111

  // functionals on maps
  // map, flatmap, filter
  println(aPhonebook3.map(pair => pair._1 + "=" + pair._2))
  println(aPhonebook3)
  println(aPhonebook3.view.filterKeys(x => x.startsWith("J")).toMap)
  // mapValues
  println(aPhonebook3.view.mapValues(x => x * 10).toMap)


  // CONVERSIONS to other collections
  println(aPhonebook3.toList)
  println(List(("Daniel", 555)).toMap)

  // group by functionality
  val names = List("Bob", "Angela", "Mary", "Jim", "James")
  println(names.groupBy(name => name.charAt(0)))
}
