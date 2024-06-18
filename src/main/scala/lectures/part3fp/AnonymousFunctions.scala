package lectures.part3fp

object AnonymousFunctions extends App {

  // lambda expression or anonymous function
  val doubler: Int => Int = x => x * 2 // <= this a Fuction1

  // multiple parameters in a lambda
  val adder = (a: Int, b: Int) => a + b

  // no params
  val doSomething = () => 3

  println(doSomething) // when it comes with lambdas you mus use parenthesis for calling them
  println(doSomething())

  // curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // more syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x=>x+1
  val niceAdder: (Int, Int) => Int = (a, b) => a + b
  // and this is equivalent with previous
  val niceAdder2: (Int, Int) => Int = _ + _ // for this the (Int, Int) => Int is really necessary, otherwise the compiler 
  // won't be able to figure out the types


}
