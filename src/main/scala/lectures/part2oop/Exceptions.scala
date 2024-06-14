package lectures.part2oop

object Exceptions extends App {
  try
    val x: String = null
    println(x.length)
  catch
    case e => println("an exception")
  finally
    println("finally")

  // val a: Nothing = throw new NullPointerException
  //1. throwing an exception
  // val aWeirdValue: String = throw new NullPointerException // its type is nothing and all types extend nothing including Sting

  // throwable classes extend the Throwable: Exception and Error
  // Exception => something went wrong with the program
  // Error => something went wrong with the system (e.g. stackoverflow error that crashed the jvm)

  // 2. how to catch exceptions
  def getInt(withException: Boolean): Int =
    if (withException) throw new RuntimeException("no int for you")
    else 42

  // potentialFail has type AnyVal because the exception handling return unit, and the main branches return int
  val potentialFail = try {
    getInt(true)
  }
  catch {
    // exceptions handling in scala use the patterns syntax
    case e: NullPointerException => println("caught a NPE")
    case e: Exception => println("caught a E")
  } finally {
    println("executed no matter what")
  }

  // 3. type for try catch expression
  // here  potentialFail2 type is Int because all the code branches return an int
  val potentialFail2 = try {
    getInt(true)
  }
  catch {
    // exceptions handling in scala use the patterns syntax
    case e: NullPointerException => 1
    case e: Exception => 7
  } finally {
    println("executed no matter what")
  }

  // 4. how to define exceptions
  // the custom exceptions can have class parameters, fields...
  class MyException extends Exception

  val ex = new MyException
  // throw ex

  // OOM
  // val array: Array[Int] = Array.ofDim(Int.MaxValue)

  // SO
  //  def infinite: Int = 1 +infinite
  //  val a = infinite

  // Pocket calc handling overflow/undeflow exceptions
  class OverflowException extends RuntimeException

  class UnderflowException extends RuntimeException

  object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      if (x < 0 && y < 0 && result > 0) throw new UnderflowException

      result
    }

    def subtract(x: Int, y: Int): Int = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      if (x < 0 && y > 0 && result > 0) throw new UnderflowException

      result
    }
  }
}
