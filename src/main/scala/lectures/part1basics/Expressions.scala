package lectures.part1basics

object Expressions extends App {
  val a = 3;
  println(3);

  var b = 5;
  println(b);

  val aCondition = true

  val aConditionedValue = if (aCondition) 5 else 3;

  println(aConditionedValue)

  println(if (aCondition) 5 else 3)

  // NEVER DO THIS IN SCALA, THIS IS IMPERATIVE
  // everything in scala is a expression
  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  }

  val aWeirdValue = (b = 3) // Unit ===void <= Side effects
  println(aWeirdValue) //

  // side effects: println, whiles, reassigning <= imperative

  // this next block is an expression and its value is the last value returned from it,
  // whatever you declare in the block stays defined only inside the block
  val aCodeBlock = {
    val y = 2
    val z = y + 1
    if (z > 2) "hello" else "goodbye"
  }

  // instruction = do something
  // expression = are evaluated, give me something

  // 1. difference between "hello world" and println("hello world"): literal with type String and side effect with type Unit
  // 2. difference between next 2: expressions both Boolean and Int
  val someValue = {
    2 < 3
  } // Boolean

  val someOtherValue = {
    if (someValue) 239 else 986
    42
  } // Int
}
