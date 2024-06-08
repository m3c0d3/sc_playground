package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int = {
    if (n == 1) n
    else {
      println("computing factorial of " + n + " needs factorial of " + (n - 1))
      val result: Int = n * factorial(n - 1)
      println("computed factorial of " + n)
      result
    }
  }

  // println(factorial(5000))
  def factorialTailRec(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, acc: BigInt): BigInt =
      if (x <= 1) acc
      else factHelper(x - 1, x * acc)

    factHelper(n, 1)
  }

  private val i = 50
  private val value: Any = factorialTailRec(i)
  println(value)

  // the key to tailrec is using auxiliary accumulators for storing th intermediate results, some times you may need more accumulators depending on how many recursive calls are on your path

  private def prime(n: Int): Boolean = {
    @tailrec
    def primeAux(t: Int, isStillPrime: Boolean): Boolean = {
      if (!isStillPrime) false
      else if (t == 1) true
      else primeAux(t - 1, n % t != 0 && isStillPrime)
    }

    primeAux(n / 2, true)
  }

  println(prime(11))

  def fibonacci(n: Int): BigInt = {
    @tailrec
    def fibTailRec(i: Int, last: BigInt, nextToLast: BigInt): BigInt = {
      if (i >= n) last
      else {
        fibTailRec(i + 1, last + nextToLast, last)
      }
    }

    if (n <= 2) 1
    else fibTailRec(2, 1, 1)
  }

  println(fibonacci(4))

}
