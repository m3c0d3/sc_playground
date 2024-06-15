package lectures.part2oop

// import playground.{Cinderella, PrinceCharming}
// import playground._

// import with aliases:
//import playground.{PrinceCharming, Cinderella => Princess}
import playground.{PrinceCharming, Cinderella as Princess}
// this is useful for example when working with Date from sql and util
object PackagingAndImports extends App {
  // package members are accessible by their simple name
  val writer = new Writer("Dan", "somet", 222)

  val cinderrella = new Princess
  val cinderrellaFullyQualifiedClassName = new playground.Cinderella

  // package object
  println(SPEED_OF_LIGHT)

  val prince = new PrinceCharming

  // default imports:
  // java.lang
  // scala: Int, Boolean
  // scala predef: println...
}
