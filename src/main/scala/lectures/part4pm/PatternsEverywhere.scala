package lectures.part4pm

object PatternsEverywhere extends App {
  try {
    // code
  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "something else"
  }
  // --- 1. catches are actually matches
  /*
   try{
   // code
   }catch(e){
    e match {
     case e: RuntimeException => "runtime"
     case npe: NullPointerException => "npe"
     case _ => "something else"
    }
   }
   */

  val list = List(1, 2, 3, 4)
  val evenOnes = for {
    x <- list if x % 2 == 0 //???
  } yield (10 * x)
  // --- 2. generators are also based on pattern matching
  val tuplesList = List((1, 2), (3, 4))
  // because of this I can iterate on tuples using next syntax
  val filterTuples = for {
    (first, second) <- tuplesList
  } yield first * second

  // case classes, :: operators, ...

  // --- 3. multiple value definition based on pattern matching, ant not only for tuples but
  // also for lists
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple // tuple destructuring using pattern matching too

  println(a + ", " + b + ", " + c)
  // for lists
  val head :: tail = list
  println("head=" + head)
  println("tail=" + tail)

  // --- 4. partial functions are also based on pattern matching
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even"
    case 1 => "The one"
    case _ => "something else"
  } // this is called a PARTIAL FUNCTION LITERAL

  // and the above for comprehension is as if I said
  val mappedList2 = list.map { x =>
    x match {
      case v if v % 2 == 0 => v + " is even"
      case 1 => "The one"
      case _ => "something else"
    }
  }

  println(mappedList)
}
