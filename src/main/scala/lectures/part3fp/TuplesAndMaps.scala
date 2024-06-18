package lectures.part3fp

import scala.annotation.tailrec

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

  /*
    1. What would happen if I had 2 original entries "Jim" -> 555 and "JIM" ->900 and I would run
      Map("Jim" -> 555 ,"JIM" ->900).map(pair=>pair._1.toLowerCase -> pair._2)
   */
  private val test: Map[String, Int] = Map("Jim" -> 555, "JIM" -> 900)
    .map(pair => pair._1.toLowerCase -> pair._2)
  println(test) // when the keys overlap, the second put will overwrite the first entry added

  /*
    2. Design an overly simplified social network based on maps
      Person = String (the name)
      name -> List(strings)
        - add a person
        - remove a person
        - friend (mutual)
        - unfriend (mutual)

        stats
        - no of friends of a person
        - person with most friends
        - how many people have no friends
        - if there is a social connection between two people (direct or not)
   */

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] =
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA + b)) + (b -> (friendsB + a))

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] =
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA - b)) + (b -> (friendsB - a))

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    @tailrec
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, friends.head, person))

    val unfriended = removeAux(network(person), network)
    unfriended - person
  // a better design would be to move these methods to a separate class, but this is beyond of the scope of this
  // course

  // tests
  println("-------")
  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))

  // Jim, Bob, Mary
  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Jim", "Bob")
  val testNet = friend(jimBob, "Mary", "Bob")

  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int = {
    if (!network.contains(person)) 0
    else network(person).size
  }


  println(nFriends(testNet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String = {
    val tuple = network.maxBy(pair => pair._2.size)
    tuple._1
  }

  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    //    network.view.filterKeys(k => network.isEmpty).size
    //    network.filter(k => k._2.isEmpty).size
    //    network.count(k => k._2.isEmpty)
    network.count(_._2.isEmpty)

  println(nPeopleWithNoFriends(testNet))

  /**
   * Checks whether there is a connection between two people a and b in network
   * @param network
   * @param a
   * @param b
   * @return
   */
  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    /**
     *
     * @param target           the person to search
     * @param consideredPeople the people which were already considered and no link was found to target
     * @param discoverPeople   yet to discover people
     * @return
     */
    def bfs(target: String, consideredPeople: Set[String], discoverPeople: Set[String]): Boolean = {
      if (discoverPeople.isEmpty) false
      else {
        val person = discoverPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoverPeople.tail)
        else bfs(target, consideredPeople + person, discoverPeople.tail ++ network(person))
      }
    }

    bfs(b, Set(), network(a) + a)
  }

  println(socialConnection(testNet, "Bob", "Mary"))
  println(socialConnection(testNet, "Bob", "Missing"))
}
