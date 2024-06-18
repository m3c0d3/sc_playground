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

  /*
  1. Expand MyList
    - foreach method, will receive a function, A => Unit, and the foreach will apply this
    function to each list element
    [1,2,3].foreach(x => println(x)) will print each elem

    - sort function, receives a sorting function which compares 2 elements (A1, A2) => Int
    negative if A1<A2 and positive otherwise, 0 if equal, and the sor will return MyList but
    sorted
    [1,2,3].sort((x,y) => y-x) this will return [3,2,1]

    - zipWith, takes another list and a zip function from 2 elements to a different element
    (list, (A, A) => B) => MyList[B]
    [1,2,3].zipWith([4,5,6], x*y) => [1*4,2*5, 3*6]

    - fold function will be curried and it will receive 2 parameter lists, one it will be the
    start value and the other one will be a function
    fold(start)(function) => a value
    [1,2,3].fold(0)(x+y)=6 (will return the sum)

  2. define toCurry which takes a function and returns
    toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
    fromCurry(f:(Int => Int => Int)) => (Int, Int) => Int

  3. define next abstract math functions
    compose(f,g) => x => f(g(x))
    andThen(f,g) => x => g(f(x))
  */

  // easy to write, not easy to think about it
  def toCurry(f: (Int, Int) => Int): Int => Int => Int =
    x => y => f(x, y)

  // easy to write, not easy to think about it
  def fromCurry(f: Int => Int => Int): (Int, Int) => Int =
    (x, y) => f(x)(y)

  // FunctionX
  // compose and andThen are 2 useful functions in functional programming
  def compose[A, B, T](f: A => B, g: T => A): T => B =
    x => f(g(x))

  def andThen[A, B, C](f: A => B, g: B => C): A => C =
    x => g(f(x))

  // dummy tests
  def superAdder2: Int => Int => Int = toCurry(_ + _)

  def add4 = superAdder2(4)

  println(add4(17) == 21)

  val simpleAdder = fromCurry(superAdder2)
  println(simpleAdder(4, 17) == 21)

  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x * 3

  val composed = compose(add2, times3)
  val ordered = andThen(add2, times3)

  println(composed(4) == (2 + (3 * 4)))
  println(ordered(4) == ((4 + 2) * 3))
}
