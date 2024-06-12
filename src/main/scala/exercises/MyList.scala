package exercises

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
}

object Empty extends MyList[Nothing] {

  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](value: B): MyList[B] = new Cons(value, Empty)

  def printElements: String = ""
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {

  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](value: B): MyList[B] = new Cons[B](value, this)

  def printElements: String =
    if (t.isEmpty) "" + h
    else "" + h + " " + t.printElements

}

object ListTest extends App {
  val listOfInt:MyList[Int] = new Cons(1,new Cons(2,Empty))
  val listOfString:MyList[String] =  new Cons("A",new Cons("B",Empty))

  println(listOfInt)
  println(listOfString)
}