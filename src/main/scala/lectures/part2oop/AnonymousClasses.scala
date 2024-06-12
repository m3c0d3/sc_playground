package lectures.part2oop

object AnonymousClasses extends App {
  abstract class Animal {
    def eat(): Unit
  }

  val funnyAnimal = new Animal:
    override def eat(): Unit = println("aaaaaa")

  funnyAnimal.eat()

  println(funnyAnimal.getClass)

  class Person(name: String):
    def sayHi(): Unit = println(s"Hi, my name is $name, how can I help you?")

  val jim = new Person("ceva") {
    override def sayHi(): Unit = println("Something else")
  }

  jim.sayHi()

  trait DummyTrait {
    def something(): Unit
  }

  val a: DummyTrait = new DummyTrait {
    override def something(): Unit = println("dummy doing some implementation")
  }

  a.something()
}
