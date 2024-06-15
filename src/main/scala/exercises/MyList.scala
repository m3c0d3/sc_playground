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

  def map[B](transformer: Function1[A, B]): MyList[B]

  def flatMap[B](transformer: Function1[A, MyList[B]]): MyList[B]

  def filter(predicate: Function1[A, Boolean]): MyList[A]

  // B >: A, read B supertype of A
  @targetName("concatenation")
  def ++[B >: A](list: MyList[B]): MyList[B]
}

// only by adding the case keyword, we have implemented equals/hashcode, tostring, and made this class a case class
// which means that all those 7 things supported for case classes will be supported here too
case object Empty extends MyList[Nothing] {

  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](value: B): MyList[B] = new Cons(value, Empty)

  def printElements: String = ""

  override def map[B](transformer: Function1[Nothing, B]): MyList[B] = Empty

  override def flatMap[B](transformer: Function1[Nothing, MyList[B]]): MyList[B] = Empty

  override def filter(predicate: Function1[Nothing, Boolean]): MyList[Nothing] = Empty

  @targetName("concatenation")
  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
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

  override def map[B](transformer: Function1[A, B]): MyList[B] =
    new Cons[B](transformer(head), tail.map(transformer))

  /*
  [1,2].flatMap(n=>[n,n+1])
  = [1,2] ++ [2].flatMap(n=>[n,n+1])
  = [1,2] ++ [2,3] ++ Empty.flatMap(n=>[n,n+1])
  = [1,2] ++ [2,3] ++ Empty
  = [1,2,3,4]


   */
  override def flatMap[B](transformer: Function1[A, MyList[B]]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  override def filter(predicate: Function1[A, Boolean]): MyList[A] =
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
}

object ListTest extends App {
  val listOfInt: MyList[Int] = new Cons(1, new Cons(2, Empty))
  val listOfIntCloned: MyList[Int] = new Cons(1, new Cons(2, Empty))
  val listOfString: MyList[String] = new Cons("A", new Cons("B", Empty))

  println(listOfInt)
  println(listOfString)

  private val myTransformer: Function1[Int, String] = new Function1[Int, String]:
    override def apply(elem: Int): String = "a" + elem

  private val myPredicate: Function1[Int, Boolean] = new Function1[Int, Boolean]:
    override def apply(elem: Int): Boolean = elem % 2 == 1

  println(listOfInt.map(myTransformer))

  println(listOfInt.filter(a => a % 2 == 1))

  println(listOfInt.filter(myPredicate))

  val my2ndTransformer: Function1[Int, MyList[Int]] = new Function1[Int, MyList[Int]]:
    override def apply(elem: Int): MyList[Int] = new Cons(elem, new Cons(elem + 1, Empty))

  println(listOfInt.flatMap(my2ndTransformer).toString)

  // no need to implement equals because it is already implemented out of the box by adding the case keyword for the class
  println(listOfInt == listOfIntCloned)
}