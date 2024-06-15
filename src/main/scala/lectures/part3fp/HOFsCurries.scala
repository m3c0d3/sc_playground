package lectures.part3fp

object HOFsCurries extends App {
  // this superfunction takes 2 parameters, one int and one function
  // (String, (Int => Boolean)) => Int, and the result it is another function
  // (Int => Int)
  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null

  // this previous function is called a higher order function because these either receive
  // parameters as functions or return functions as result
  //
  // function that applies a function n times over a value x
  // nTimes(f, n, x), f is a function, n is the number for the function to be applied
  // nTimes(f, 3, x) = f(f(f(x)))
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n == 0) x
    else nTimes(f, n - 1, f(x))

  val plusOne = (x: Int) => x + 1

  println(nTimes(plusOne, 7, 0))

  // ntb(f,n) = x = f(f(f...(x)))
  // increment10 = ntb(plusOne, 10) = x=> plusOne(plusOne...(x))
  def nTimesBetter(f: Int => Int, n: Int): Int => Int =
    if (n == 0) x => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))

  val plusTen = nTimesBetter(plusOne, 10)

  println(plusTen(0))

  // curried functions
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y

  val add3 = superAdder(3) // y=>3+y
  println(add3(10))
  println(superAdder(10)(3))

  // functions with multiple parameter lists - these will act like curried functions
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  // for the smaller functions the type must be specified, e.g. bellow the (Double => String)
  // otherwise the compiler is throwing error
  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))


}
