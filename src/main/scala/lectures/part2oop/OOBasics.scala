package lectures.part2oop

object OOBasics extends App {
  val person = new Person("John", 22)
  println(person)

  println(person.age)
  println(person.x)
  println(person.greet("gigi"))
  println(person.greet())
}

//constructor
class Person(name: String = "John", val age: Int = 0) {
  // body
  val x = 3
  println(x + 3)

  def greet(name: String) = s"${this.name} says: Hi $name"

  // overloading
  def greet(): String = s"Hi, I am $name"

  // overloading won't work because only the return type is different
  //  def greet(): Int = 23

  // auxiliary constructors
  def this(name: String) = this(name, 0)

  def this() = this("John Doe")

  // but the auxiliary constructors can be avoided by using default parameters for
  // the main constructor

}


// class parameters are NOT FIELDS, to convert parameters to fields
// add val keyword to it
class Writer(firstName: String, surname: String, year: Int)

class Novel(name: String, year: Int, author: Writer)
