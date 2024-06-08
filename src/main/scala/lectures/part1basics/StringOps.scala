package lectures.part1basics

object StringOps extends App {

  val str = "Hello, I am learning Scala"

  println(str.charAt(2))
  println(str.substring(8, 14))

  println(str.split(" ").toList)

  println(str.replace(" ", "."))

  val aNumber = "23"
  println(aNumber.toInt)
  println('a' +: aNumber :+ 'z')
  println(str.take(2))

  // String interpolator s
  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name, and I am $age years old and I will be ${age+1}"
  println(greeting)
  val speed = 0.23555;
  // f interpolators
  println(f"$speed%1.2f")
  // raw interpolators
  private val value = raw"This a \n newline"
  println(value)
  val escaped = "This a \n new line"
  private val escapedStr = raw"$escaped"
  println(escapedStr)
}
