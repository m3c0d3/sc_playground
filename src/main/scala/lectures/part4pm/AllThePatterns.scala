package lectures.part4pm

import exercises.{Cons, Empty, MyList}

object AllThePatterns extends App {
  // 1 - constants
  val x: Any = "Scala"
  val constants = x match
    case 1 => "a number"
    case "Scala" => "THE Scala"
    case true => "The Truth"
    case AllThePatterns => "A singleton object"

  // 2 - match anything
  // 2.1 wildcard
  val matchAnything = x match
    case _ =>

  // 2.2 match a variable
  val matchAVariable = x match {
    case something => s"Ive found $something"
  }

  // 3 tuples
  val aTuple = (1, 2)
  val matchAtouple = aTuple match
    case (1, 2) => println("1 and 2")
    case (1, n2) => println(1 + " " + n2)
    case (n1, n2) => println(n1 + " " + n2)

  // same thing works for nested touples
  val aNestedTouple = (1, (2, 4))
  val matchANestedtouple = aNestedTouple match
    case (n1, (n2, n3)) => n2 + " " + n3
    case null => 1

  // 4 - case classes - constructor pattern matching
  val myList: MyList[Int] = Cons(1, Cons(2, Empty))

  val matchAList = myList match
    case Empty => 0
    case Cons(head, Cons(subHead, subTail)) => subHead
    case Cons(head, someTail) => head
    case _ => -1

  // 5 - list patterns
  val aList: List[Int] = List(1, 2, 3, 42)
  val standardListMatching = aList match
    case List(1, _, _, _) => // extractor for list
    case List(1, _*) => // list of arbitrary length
    case 1 :: List(_) => // infix pattern
    case List(1, 2, 3) :+ 42 => // other infix pattern

  // 6 - type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match
    case list: List[Int] => // explicit type specifier
    case _ =>

  // 7 - name binding
  val nameBindingMatch = myList match
    case nonEmptyList@Cons(_, _) => nonEmptyList.head // name binding
    case Cons(1, rest@Cons(2, _)) => rest.head // name binding inside nested patterns

  // 8 - multi-patterns or compound patterns
  val multipattern = myList match
    case Empty | Cons(0, _) =>

  // 9 - if guards
  val secondElementSpecial = myList match
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 =>

  // ALL

  // Question
  val numbers = List(1, 2)
  val numbersMatch = numbers match
    case listOfStrings: List[String] => "strings"
    case listOfInts: List[Int] => "Ints"
    case _ =>

  println(numbersMatch) // strings because of java's type erasure
}
