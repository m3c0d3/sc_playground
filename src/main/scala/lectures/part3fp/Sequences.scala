package lectures.part3fp

import scala.util.Random

object Sequences extends App {
  // --- Seq - have a well defined order
  // head, tail

  val aSequence = Seq(1, 20, 3, 4) // the implementation used is a list, but the declared
  // type it is Seq[Int]
  println(aSequence)

  println(aSequence.reverse)

  println(aSequence(2)) // get from index

  println(aSequence ++ Seq(5, 6, 7))

  println(aSequence.sorted)

  // Ranges are also sequences
  val aRange = 1 to 10
  val aRangeWithTheEndNonInclusive = 1 until 10

  aRange.foreach(println)
  aRangeWithTheEndNonInclusive.foreach(println)

  // --- Lists are immutable and extend linear Seq
  // head, tail, isEmpty methods are fast, O(1)
  // most operations are O(n), length, reverse
  // List are sealed, and they have 2 subtypes:
  // - Nil, empty list
  // - class ::
  val aList = List(1, 2, 3)
  val prepended = 42 :: aList // prepend operator
  println(prepended)
  // another operator for prepending it is +:, if you want to do appending you can use :+
  println(44 +: aList :+ 99)

  val apples5 = List.fill(5)("apple")
  println(apples5)

  println(aList.mkString("-|-")) // 1-|-2-|-3

  // --- Array - equivalent to Java arrays
  val numbers = Array(1, 2, 3, 4)
  val threeElements = Array.ofDim[Int](5) // for primitives the defaults are used
  println(threeElements)
  threeElements.foreach(println)

  val threeElementsObj = Array.ofDim[String](5) // for reference types are initialized with nulls
  threeElementsObj.foreach(println)

  //mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2,0), syntax sugar similar to apply
  println(numbers.mkString(" "))

  // connection between arrays and sequences
  val numberSeq: Seq[Int] = numbers // though the types are different, the conversion can be applied
  // the = sign from previous line it is called implicit conversion
  println(numberSeq)

  // --- Vector, constant time for read and write O loc 32 of n
  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  // vectors vs lists perf test
  val maxRuns = 1000
  val maxCapacity = 1_000_000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), 0)
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns // average time for the collection to be updated with a value at a random index
  }

  val numbersList = (1 to maxCapacity).toList // created a list
  val numbersVector = (1 to maxCapacity).toVector // created a vector with some elements
  // list saves the reference to the tail=> updating the first element is very efficient,
  // updating an element randomly is not very efficient, takes a long time
  println(getWriteTime(numbersList))
  // depth of the tree is small, it needs to replace an entire 32 element chunk, but this disadvantage is
  // not so big
  println(getWriteTime(numbersVector))
}
