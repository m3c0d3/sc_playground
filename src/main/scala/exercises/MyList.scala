package exercises

abstract class MyList {
  /*
  head = first elem of the list
  tail = remainder of the list
  isEmpty = if list is empty
  add(int) => new list with the elem added
  toString => string repr of the list
  */
  def head: Int

  def tail: MyList

  def isEmpty: Boolean

  def add(value: Int): MyList // this is an immutable list, we'll return another one every time we add to it

  def printElements: String

  override def toString: String = "[" + printElements + "]"
}

object Empty extends MyList {

  override def head: Int = throw new NoSuchElementException

  override def tail: MyList = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add(value: Int): MyList = new Cons(value, Empty)

  def printElements: String = ""
}

class Cons(h: Int, t: MyList) extends MyList {

  override def head: Int = h

  override def tail: MyList = t

  override def isEmpty: Boolean = false

  override def add(value: Int): MyList = new Cons(value, this)

  def printElements: String =
    if (t.isEmpty) h + ""
    else h + " " + t.printElements

}

object ListTest extends App {
  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(list.tail.tail.head)
  list.add(4)
  println(list.head)

  println(list)
}