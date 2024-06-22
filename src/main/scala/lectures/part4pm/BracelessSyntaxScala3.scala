package lectures.part4pm

object BracelessSyntaxScala3 extends App {
  // if expressions
  val anIfExpr = if (2 > 3) "bigger" else "smaller"
  val anIfExpr2 =
    if (2 > 3) {
      "bigger"
    } else {
      "smaller"
    }

  val anIfExpr3 = if (2 > 3) "bigger"
  else "smaller"

  // scala 3
  val anIfExpr4 =
    if 2 > 3 then
      "bigger"
    else
      "smaller"
  println(anIfExpr4)

  // for comprehensions too
  val aForComprehension = for {
    n <- List(1, 2, 3)
    s <- List("black", "white")
  } yield s"$n$s"

  // scala3 -
  val aForComprehension2 =
    for
      n <- List(1, 2, 3)
      s <- List("black", "white")
    yield s"$n$s"

  println(aForComprehension2)

  // pattern matching
  val meaningOfLife = 42
  val aPatternMatching = meaningOfLife match {
    case 1 => "the one"
    case 2 => "double"
    case _ => "else"
  }

  // pattern matching scala 3
  val aPatternMatchingScala3 = meaningOfLife match
    case 1 => "the one"
    case 2 => "double"
    case _ => "else"

  // whatever you choose, significant indentation or curly braces, stick to it through the
  // whole project

  // significant indentation can be used for function too
  def aFunction() =
    3

  // classes, objects, enums, traits also can be defined with significant indentation

  class Animal: // : significant indentation colon, needed for classes, traits, anonymous classes
    def eat() =
      println("eating")

    def grow() =
      println("eating")
    // end token
    end grow

    // end token
  end Animal

}
