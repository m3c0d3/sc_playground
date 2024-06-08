package lectures.part2oop

object AbstractDataTypes extends App {
  // abstract members
  abstract class Animal:
    val creatureType: String = "Wild"

    def eat: Unit


  // abstract classes cannot be instantiated
  // abstract members don't have the implementation/values
  // abstract classes are meant to be extended later

  class Dog extends Animal:
    override val creatureType: String = "canine"

    override def eat: Unit = println("crunch crunch")

  // traits == interfaces

  trait Carnivore(name: String) {
    def eat(animal: Animal): Unit

    val preferredMeal: String = "fresh meat"
  }

  trait ColdBlooded

  class Crocodile extends Animal with ColdBlooded with Carnivore("Gigi") {

    override val creatureType: String = "croc"

    override def eat: Unit = println("nom nom nom I'm a crocodile")

    override def eat(animal: Animal): Unit = println(s"I'm a croc and I eat ${animal.creatureType}")
  }

  val dog = new Dog
  val crocodile = new Crocodile
  crocodile.eat(dog)

  // traits vs abstract classes
  // abstract classes can have abstract and non abstract members but so can traits

  // how are traits actually different from abstract classes?
  // 1 - traits cannot have constructor parameters (this is now possible in Scala 3)
  // 2 - you can have multiple traits (they may be inherited) but you can extend only one abstract class
  // 3 - we choose a trait when this describes a behavior, traits describe what they do, abstract classes
  // describe what they are

  // SCALA'S TYPE HIERARCHY
  // scala.Any - is the mother of all types
  // scala.AnyRef (java.lang.Object) - String, list, Set...
  // scala.Null
  // scala.AyVal = all scala primitives, Int, Boolean...
  // scala.Nothing are all previously mentioned types
  var a: Any = 3;
  println(a)

  a = null
}
