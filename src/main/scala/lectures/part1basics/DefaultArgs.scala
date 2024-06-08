package lectures.part1basics

object DefaultArgs extends App {

  def trFact(n: Int, acc: Int = 1): Int =
    if (n <= 1) acc
    else trFact(n - 1, n * acc)

  val fact10 = trFact(3)
  println(fact10)

  def savePicture(format: String = "jpg", height: Int, width: Int): Unit = {
    println("Saving")
  }

  // default values for methods and naming parameters
  savePicture(height = 40, width = 44)

  savePicture(format = "ss", 23, 24)

}
