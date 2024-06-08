package lectures.part2oop

import scala.annotation.targetName
import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie


    @targetName("hangsOutWith")
    def +(person: Person): String = s"${this.name} hangs out with ${person.name}"

    def unary_! : String = s"${this.name}!"

    def isAlive: Boolean = true

    def apply(): String = s"hi my name is $name and I like $favoriteMovie"

    def apply(noOfTimes: Int): String = s"$name watched $favoriteMovie $noOfTimes times"

    @targetName("withAnotherStringInName")
    def +(aString: String): Person = new Person(this.name + aString, this.favoriteMovie)

    @targetName("increaseAge")
    def unary_+ : Person = new Person(this.name, this.favoriteMovie, this.age + 1)

    def learns: String = s"$name learns Scala"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  // INFIX NOTATION or OPERATOR NOTATION and it works only for methods with a single operator
  println(mary likes "Inception") // equivalent

  // "operators" in scala
  private val tom = new Person("Tom", "Fight club")
  println(mary + tom)
  println(mary.+(tom))

  println(1.+(2))
  // ALL operators are methods
  // Akka actors have ! ?

  // syntactic sugar = brings the language closer to natural language
  // PREFIX notation
  val x = -1 // is equivalent to 1.unary_-
  val y = 1.unary_-

  // unary operators overloading works only with  +, -, ~, !
  println(!mary)
  println(mary.unary_!)

  // POSTFIX notation
  println(mary.isAlive)
  println(mary isAlive) // works can introduce potential ambiguities when reading code

  println(mary.apply())
  println(mary()) // equivalent and uses the defined apply()

  // INFIX NOTATION
  private val person: Person = mary + "the rockstar"
  println(person.name)
  // PREFIX NOTATION
  println((+mary).age)
  // POSTFIX NOTATION
  println(mary learns)
  println(mary(3))


}
