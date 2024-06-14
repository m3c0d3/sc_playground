package lectures.part2oop

object CaseClasses extends App {
  /*
  equals, hashCode, toString
  */
  case class Person(name: String, age: Int)

  // 1. for case classes class parameters are fields, they can be used as if 
  // they are declared with vals
  var jim = new Person("Jim", 22)
  println(jim.name)

  // 2. sensible toString = the toString it is properly implemented by default for it
  println(jim)

  // 3. equals and hashCode are implemented out of the box
  val jim2: Person = new Person("Jim", 22)
  println(jim == jim2) // true because both instances are created using the same parameters

  // 4. handy copy methods out of the box
  val jim3 = jim.copy()
  val jim4 = jim.copy(age = 245)
  println("" + jim3 + " " + jim4)

  // 5. Case Classes have companion objects, no need to use the new keyword
  // when instantiating them, because they have the apply method implemented out of the box
  val thePerson = Person
  val mary = Person("Mary", 33)
  println(thePerson)

  // 6. Case Classes are serializable
  // this is specially useful for the akka framework, akka deals with sending serializable messages through the network
  // and our messages in akka are in practice, in general case classes

  // 7. case classes have extractor patterns => they can be used for PATTERN MATCHING

  // there is also such a thing like a case object => they don't need companion objects because they are already companion objects
  case object UnitedKingdom:
    def name: String = "The UK of GB and NI"


  println(mary)
}
