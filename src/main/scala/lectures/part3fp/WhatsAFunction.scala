package lectures.part3fp

object WhatsAFunction extends App {
  // dream - use function as first class elements
  // problem oop

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  // doubler can be called like a function, this is supported out of the box in scala
  println(doubler(2))

  // by default scala supports this function types up to 22 parameters
  // function types = Function1,2,3,...22, Function [A,B]
  val stringToIntConverter = new Function1[String, Int]:
    override def apply(string: String): Int = string.toInt


  private val value: Int = stringToIntConverter("4")
  println(value + 4)

  val adder0: Function2[Int, Int, Int] = new Function2[Int, Int, Int]:
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  // same as previous
  val adder: (Int, Int) => Int = new Function2[Int, Int, Int]:
    override def apply(v1: Int, v2: Int): Int = v1 + v2

  // ALL SCALA FUNCTIONS ARE OBJECTS
  /*
   exercises:
  2. transform the myPredicate and mytransformer into function types
  3. define a function which takes an int and returns another function which takes an int and
  returns an int
      - what's the type of this function

  */
  def hoFunction(a: Int): Function1[Int, Int] =
    new Function[Int, Int]:
      override def apply(v1: Int): Int = a + v1

  val fiveAdder = hoFunction(5);

  val result = fiveAdder(2)

  println(if (result == 7) "the fiveAdder function added 5 and result is correct" else "problem")
}

// this is the most thing what you can do with OOP
trait MyFunction[A, B] {
  def apply(element: A): B
}

//class A:
//  def aMethod(): Unit = println("something")