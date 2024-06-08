package lectures.part2oop

object Objects extends App {
  // objects in scala are a dedicated concept

  // SCALA DOES NOT HAVE CLASS LEVEL FUNCTIONALITY (no static like functionality from java)
  // instead of that scala has object, and everything inside it can be considered static
  object Person {
    val NO_OF_EYES = 2

    def canFly: Boolean = false

    // factory method - and these are called conveniently apply(..)
    def apply(mother: Person, father: Person): Person = new Person("the kid")
  }

  // we can write class and object with the same name in the same file to split the static
  // functionality from instance specific functionality
  // and object Person with class Person are referred to as COMPANIONS
  // companions can access each other's private members
  class Person(val name: String) {
    // instance level functionality
  }

  println(Person.NO_OF_EYES)
  println(Person.canFly)


  // differences - Scala object is a singleton instance
  val mary = Person
  val john = Person
  // they both point to the same instance
  println(mary == john)

  // when having a companion class instantiated, the next  == statement will no longer print
  // different instances
  val mary2 = new Person("Mary")
  val john2 = new Person("tom")
  println(mary2 == john2)

  // more conciseness
  println(Person(mary2, john2).name)

  // Scala applications = scala object with a main method
  // def main(args: Array[String]): Unit
  // the main method is same as the one from Java because scala is translated into Java
  //  def main(args: Array[String]): Unit = {
  //  }

}
