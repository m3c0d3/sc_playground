package exercises

import scala.annotation.targetName

abstract class MyList[+A] {
  /*
  head = first elem of the list
  tail = remainder of the list
  isEmpty = if list is empty
  add(int) => new list with the elem added
  toString => string repr of the list
  */
  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](value: B): MyList[B] // this is an immutable list, we'll return another one every time we add to it

  def printElements: String

  override def toString: String = "[" + printElements + "]"

  // high order functions - receive functions as parameters or return functions
  def map[B](transformer: A => B): MyList[B]

  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  def filter(predicate: A => Boolean): MyList[A]

  // B >: A, read B supertype of A
  @targetName("concatenation")
  def ++[B >: A](list: MyList[B]): MyList[B]

  // hofs
  def foreach(a: A => Unit): Unit

  def sort(compare: (x: A, y: A) => Int): MyList[A]

  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]

  // similar to reduce from js/java
  def fold[B](start: B)(operator: (B, A) => B): B
}

// only by adding the case keyword, we have implemented equals/hashcode, tostring, and made this class a case class
// which means that all those 7 things supported for case classes will be supported here too
case object Empty extends MyList[Nothing] {

  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](value: B): MyList[B] = new Cons(value, Empty)

  def printElements: String = ""

  override def map[B](transformer: (Nothing) => B): MyList[B] = Empty

  override def flatMap[B](transformer: (Nothing) => MyList[B]): MyList[B] = Empty

  override def filter(predicate: (Nothing) => Boolean): MyList[Nothing] = Empty

  @targetName("concatenation")
  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  override def foreach(a: Nothing => Unit): Unit = {}

  override def sort(compare: (x: Nothing, y: Nothing) => Int): MyList[Nothing] = Empty

  override def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw RuntimeException("lists do not have the same length")
    else Empty

  override def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

// only by adding the case keyword, we have implemented equals/hashcode, tostring, copy, serializable and made this class a case class
// which means that all those 7 things supported for case classes will be supported here too
case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {

  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](value: B): MyList[B] = new Cons[B](value, this)

  def printElements: String =
    if (t.isEmpty) "" + h
    else "" + h + " " + t.printElements

  override def map[B](transformer: (A) => B): MyList[B] =
    new Cons[B](transformer(head), tail.map(transformer))

  /*
  [1,2].flatMap(n=>[n,n+1])
  = [1,2] ++ [2].flatMap(n=>[n,n+1])
  = [1,2] ++ [2,3] ++ Empty.flatMap(n=>[n,n+1])
  = [1,2] ++ [2,3] ++ Empty
  = [1,2,3,4]
   */
  override def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  override def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(head)) new Cons(head, tail.filter(predicate))
    else tail.filter(predicate)

  /*
  [1,2] ++ [3,4,5]
  = new Cons(1, [2] ++ [3,4,5]
  = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
  = new Cons(1, new Cons(2, [3,4,5]))
  =...
  = new Cons(1, new Cons(2, new Cons (3, new Cons(4, new Cons(5)))))
   */
  @targetName("concatenation")
  override def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  override def foreach(a: A => Unit): Unit =
    a(head)
    tail.foreach(a)

  override def sort(compare: (A, A) => Int): MyList[A] = {
    // this is not tail recursive
    def insert(x: A, sortedList: MyList[A]): MyList[A] = {
      if (sortedList.isEmpty) Cons(x, Empty)
      else if (compare(x, sortedList.head) <= 0) Cons(x, sortedList)
      else Cons(sortedList.head, insert(x, sortedList.tail))
    }

    // insertion sort
    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  override def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] =
    if (list.isEmpty) throw RuntimeException("Lists do not have the same length")
    else new Cons(zip(h, list.head), tail.zipWith(list.tail, zip))

  override def fold[B](start: B)(operator: (B, A) => B): B =
    val newStart = operator(start, head)
    t.fold(newStart)(operator)
  // can be also written more concise as
  // t.fold(operator(start, head))(operator)
}

object ListTest extends App {
  val listOfInt: MyList[Int] = new Cons(1, new Cons(2, Empty))
  val listOfIntCloned: MyList[Int] = new Cons(1, new Cons(2, Empty))
  val listOfString: MyList[String] = new Cons("A", new Cons("B", Empty))

  println(listOfInt)
  println(listOfString)

  private val myTransformer: Int => String = new Function1[Int, String]:
    override def apply(elem: Int): String = "a" + elem

  private val myPredicate: Int => Boolean = new Function1[Int, Boolean]:
    override def apply(elem: Int): Boolean = elem % 2 == 1

  println(listOfInt.map(myTransformer))

  println(listOfInt.filter(a => a % 2 == 1))

  println(listOfInt.filter(myPredicate))

  val my2ndTransformer: Int => MyList[Int] = new Function1[Int, MyList[Int]]:
    override def apply(elem: Int): MyList[Int] = new Cons(elem, new Cons(elem + 1, Empty))

  println(listOfInt.flatMap(my2ndTransformer).toString)

  // no need to implement equals because it is already implemented out of the box by adding the case keyword for the class
  println(listOfInt == listOfIntCloned)
  println("foreach test")
  listOfInt.foreach(println)

  println(listOfInt.sort((x, y) => y - x))

  // zip is very useful by spark developers
  println(listOfInt.zipWith(listOfString, _ + "-" + _))

  // fold
  println("fold: " + listOfInt.fold(0)((x, y) => x + y))
  println("fold: " + listOfInt.fold(1)(_ * _))
}