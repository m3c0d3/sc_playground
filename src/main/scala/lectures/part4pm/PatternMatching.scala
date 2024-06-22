package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {
  // switch on steroids
  val random = new Random
  val x = random.nextInt(10)

  // pattern matching => looks like a switch but it is more powerful
  val description = x match {
    case 1 => "The one"
    case 2 => "Double"
    case 3 => "third time is a charm"
    case _ => "something else"
  }
  println(x)
  println(description)

  // 1. Can decompose values
  case class Person(name: String, age: Int)

  val bob = Person("Bob", 20)

  val greeting = bob match {
    case Person(n, a) => s"Hi my name is $n and I am $a years old" // name and age are extracted and used
    case _ => "I don't know who I am"
  }

  println(greeting)
  val gigi = Person("Gigi", 19)
  val greetingWithGuard = gigi match {
    case Person(n, a) if a < 21 => s"Hi my name is $n and I can't drink in the US" // GUARD
    case Person(n, a) => s"Hi my name is $n and I am $a years old" // name and age are extracted and used
    case _ => "I don't know who I am"
  }
  println(greetingWithGuard)

  // 1 cases are matched in order
  // 2 when no cases match, no default defined => MatchError (Try)
  // 3 return type is the unified type for all the cases
  // 4 pattern matching work extremely well with case classes
  // PM on sealed hierarchies
  sealed class Animal

  case class Dog(breed: String) extends Animal

  case class Parrot(greeting: String) extends Animal

  val animal = Dog("Athena")
  animal match {
    case Dog(someBreed) => println(s"A dog with $someBreed")
  }

  // new programmers match everything
  // Why the next thing??? this is overkill, don't do it
  val isEven = x match {
    case n if n % 2 == 0 => true
    case _ => false
  }

  trait Expr

  case class Number(n: Int) extends Expr

  case class Sum(e1: Expr, e2: Expr) extends Expr

  case class Product(e1: Expr, e2: Expr) extends Expr

  /*
  Exercise, simple function uses PM
  takes an Expr and returns the human readable format for it
  Sum(Number(1), Number(2))=>1+2
  Sum(Sum(Number(1), Number(2)), Number(3))=>(1+2)*3
   */
  def show(e: Expr): String = e match
    case Number(n) => s"$n"
    case Sum(e1, e2) => show(e1) + "+" + show(e2)
    case Product(e1, e2) => {
      def maybeShowParanthesis(expr: Expr) = expr match {
        case Product(_, _) => show(expr)
        case Number(_) => show(expr)
        case _ => "(" + show(expr) + ")"
      }

      maybeShowParanthesis(e1) + "*" + maybeShowParanthesis(e2)
    }


  println(show(Sum(Sum(Number(2), Number(2)), Number(2))))
  println(show(Sum(Product(Number(2), Number(2)), Number(2))))
  println(show(Product(Sum(Number(2), Number(2)), Number(2))))
  println(show(Product(Sum(Number(2), Number(2)), Sum(Number(2), Number(2)))))
}
