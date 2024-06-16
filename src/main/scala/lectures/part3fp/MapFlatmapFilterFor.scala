package lectures.part3fp

import exercises.{Cons, Empty, Just, MaybeNot}

object MapFlatmapFilterFor extends App {
  val list = List(1, 2, 3)
  println(list)
  println(list.head)
  println(list.tail)

  //map
  println(list.map(_ + 1))

  //filter
  println(list.filter(_ % 2 == 0))

  // flatmap
  val toTriple = (x: Int) => List(x, x + 1, x + 2)
  println(list.flatMap(toTriple))

  // Exercise - print out all combinations between two lists
  val numbers = List(1, 2, 3, 4)
  val characters = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")
  // List("a1", "a2"..."d4")
  val combinations = numbers.flatMap(n => characters.map(c => "" + c + n))
  println(combinations)
  // THEREFORE => for(for..) loops from iterative languages can be rewritten
  // as flatmap(flatmap(..(map))) combinations
  val combinations2 = numbers.flatMap(n => characters.flatMap((c => colors.map(col => "" + c + n + col))))
  println(combinations2)

  // foreach
  list.foreach(println)

  // shorthand for map,flatmap, foreach chain calls => COMPREHENSIONS
  // behind the scenes the compiler will translate this into the flatmap/map chains from
  // above
  val forCombinations = for {
    n <- numbers
    c <- characters
    col <- colors
  } yield "" + c + n + col // <= this will have the same result as the code written with
  // flatmap/map combinations above when we compute val combinations2, only difference being
  // now that the readability is much more improved, and IT IS PREFERRED IN PRACTICE
  println(forCombinations)

  // if I want to filter out some numbers, I would put in a guard
  val forCombinationsWithEvenNumbers = for {
    n <- numbers if n % 2 == 0 // these are translated into a filter call
    c <- characters
    col <- colors
  } yield "" + c + n + col
  // the compiler will do:
  val forCombinationsWithEvenNumbersByCompiler = numbers.filter(_ % 2 == 0).flatMap(n => characters.flatMap((c => colors.map(col => "" + c + n + col))))
  // but the compiler code is not easy to read/understand
  println(forCombinationsWithEvenNumbers)

  // for doing side effects, the for comprehensions can still be used
  for {
    n <- numbers
  } println(n)

  // syntax overload
  val alternativeSyntaxResult = list.map { x =>
    x * 2
  }

  println(alternativeSyntaxResult)

  /*
  1. MyList supports for comprehensions?
  2. A small collection of at most ONE element - Maybe[+T] (T type parameter covariant)
    - implement for it map, flatMap, filter
   */
  // 1. it works because MyList implements map/flatMap/fiter
  val aMyList = new Cons(1, new Cons(2, new Cons(3, Empty)));
  val aMyList2 = new Cons("a", new Cons("b", new Cons("c", Empty)));

  val resultMyList = for {
    elem <- aMyList // if elem % 2 == 0 doesn't seem to work: withFilter missing
    elem2 <- aMyList2
  } yield "comb=" + elem + ":" + elem2

  println(resultMyList)


  // 2.
  val just5 = Just(5)
  println(just5)
  val notPresent = MaybeNot
  println(notPresent)
  println(just5.map(_ * 2)) // Just(10)
  println(just5.flatMap(x => Just(x % 2 == 0))) // =>Just(false)
  println(just5.map(x => Just(x % 2 == 0))) // =>Just(Just(false))
  println(just5.filter(x => x % 2 == 0)) // =>MaybeNot

}
