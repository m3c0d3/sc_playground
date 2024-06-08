package lectures.part2oop

object Generics extends App {
  class MyList[+A] {
    // use type A
    def add[B >: A](element: B): MyList[B] = ???

  }

  // you can have multiple type parameters
  class MyMap[Key, Value]

  // traits can also have type parameters
  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  object MyList {
    // generic methods
    def empty[A]: MyList[A] = ???
  }

  // to use the generic method
  val emptyListOfIntegers = MyList.empty[Int]

  // THE VARIANCE PROBLEM

  class Animal

  class Cat extends Animal

  class Dog extends Animal

  // if cat extends Animal, does a list of cat extends also list of animal?
  // ----1. YES - covariance

  class CovariantList[+A]

  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog)??? is this OK? HARD QUESTION - because we return a list of animals by using the code from from above
  // def add[B >: A](element: B): MyList[B] = ???

  // ----2. NO - invariance - here list of cats cannot substitute the list
  // of animal
  class InvariantList[A]

  val invariantList: InvariantList[Animal] = new InvariantList[Animal];

  // ---3. HELL NO! CONTRAVARIANCE
  class ContravariantList[-A]

  // this doesn't even make sense in this specific case
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

  // in next case the contravariance it makes sense because a trainer of
  // animals can train any kind of animals, therefore the trainer of animal
  // can be assigned do trainer of cat
  class Trainer[-A]

  val trainer: Trainer[Cat] = new Trainer[Animal]


  // -------BOUNDED TYPES
  // allow you to use the generic types for only come classes that are either only sub
  // classes of a certain type or only superclasses for a certain typ
  // ex:
  class Cage[A <: Animal](animal: Animal) // class cage only accepts type parameter A which is subtype for Animal

  val cage = new Cage(new Dog)

  class Car
  // next won't work because car is not an animal
  //  val newCage = new Cage(new Car)

  // bounded types solve a variance problem when working with generic types

  //  ------------
  // exercise - expand the MyList to be generic
}
