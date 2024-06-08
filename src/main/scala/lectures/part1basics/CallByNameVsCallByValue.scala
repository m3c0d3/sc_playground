package lectures.part1basics

object CallByNameVsCallByValue extends App {

  def callByValue(x: Long) = {
    println(x)
    println(x)
  }

  def callByName(x: => Long) = {
    println(x)
    println(x)
  }
  // the value is computed before calling the function
  callByValue(System.nanoTime())

  // parameter evaluation is delayed, the function name is passed as value, and
  // the expression passed as parameter is evaluated lazily later inside the function
  callByName(System.nanoTime())

  def infinite(): Int = 1 + infinite()

  def printFirst(x: Int, y: => Int) = println(x)

  // crashes with stackoverflow
  // printFirst(infinite(), 34)

  printFirst(34, infinite())

}
