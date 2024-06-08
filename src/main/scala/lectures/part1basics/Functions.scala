package lectures.part1basics

import scala.annotation.tailrec

object Functions extends App {
  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("hello", 3))
  private val str: String = aFunction("hello", 3)
  println(str)

  def aParameterlessFunction() = 42

  println(aParameterlessFunction())

  def aRepeteadFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeteadFunction(aString, n - 1)
  }

  println(aRepeteadFunction("hello", 3))

  // in scala or a functional language when you need loops you use recursion


  // RETURN TYPES OF FUNCTIONS
  // compiler can infer the return type of a function for most of the functions. E.g. for recursive functions the compiler cannot figure it out on its own

  // you can use unit as a return type  for a function doing only side effects
  def aFunctionWithSideEffects(aString: String): Unit = println(aString)


  // you can define auxiliary definitions inside functions or code blocks too
  def aBigFunction(n: Int): Int = {
    def aSmallFunction(a: Int, b: Int): Int = {
      a + b
    }

    aSmallFunction(n, n - 1)
  }

  // Exercises
  // 1. A greeting function(name, age)
  def hi(name: String, age: Int) = "hi " + name + ", my age is " + age

  // 2. factorial function
  def factorial(n: Int): Int = if (n == 1) n else n * factorial(n - 1)

  println(factorial(3))

  // 3. a fibonacci function
  private def fibonnacci(n: Int): Int = {
    if (n <= 2) 1 else fibonnacci(n - 1) + fibonnacci(n - 2)
  }
  // 1 1 2 3 5 8 13 21
  println(fibonnacci(3))
  println(fibonnacci(8))
  // 4. test if a number is prime

  private def prime(n: Int): Boolean = {
    @tailrec
    def primeAux(n: Int, div: Int): Boolean = {
      if (div == 1) true else n % div != 0 && primeAux(n, div - 1)
    }

    primeAux(n, n / 2)
  }

  println(prime(7))

  println(prime(10))
  // type inference for expressions, and for functions except for those that are recursive where the compiler is not able to figure the return type on its own
  
}
