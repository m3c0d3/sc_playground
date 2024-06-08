package lectures.part2oop

object Inheritance extends App {
  // scala has single class inheritance
  // much more intricate inheritance models when using traits
  class Animal {
    val creatureType = "wild"

    //    protected is usable only inside the subclass
    def eat = println("nom nom")
  }

  class Cat extends Animal {

    def crunch = {
      //eat
      println("crunch crunch")
    }
  }

  val aCat = new Cat
  aCat.crunch


  // CONSTRUCTORS - act in particular way when dealing with inheritance
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  // jvm needs to call a super constructor and Person without arguments doesn't exist, therefore we
  // have to pass the correct parameters
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  class Adult2(name: String, age: Int, idCard: String) extends Person(name)

  class Dog(override val creatureType: String = "canine") extends Animal {
    //    override val creatureType = "domestic";

    override def eat: Unit = {
      super.eat
      println("wof crunch")
    }

  }

  val dog = new Dog
  dog.eat
  println(dog.creatureType)

  // type substitution <=> polymorphism
  val unknownAnimal: Animal = new Dog

  // the most specific implementation, lowes on inheritance tree will be used
  // (here the dog implementation will be used)
  unknownAnimal.eat

  // overriding = supplying different implementations in inheriting classes
  // overloading = having multiple methods with same name in same class but different parameters

  // SUPER
  // super.eat

  // supressing/preventing overwriting of implementations
  // - final for members,
  // - final on classes...
  // - sealed - seal the class - extend the class only in this file
  // but prevent extension for it in other files


}
