package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}


object HandlingFailure extends App {
  // create success and failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("failure"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("No string for you")

  // Try objects via the apply method
  val potentialFailure: Try[String] = Try(unsafeMethod())
  println(potentialFailure)

  val anotheFailure = Try {
    // code that might throw
  }

  // utilities
  println(potentialFailure.isSuccess)
  println(potentialFailure.isFailure)

  def backupMethod = {
    "DEFAULT"
  }

  val fallBacktTry = potentialFailure.orElse(Success(backupMethod))
  println(fallBacktTry)

  // if you design the api - WRAP YOUR RESULT WITH TRY IF YOU KNOW IT MIGHT THROW AN EXCEPTION
  // SIMILAR AS FOR OPTION
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException("No string for you"))

  def betterBackupMethod(): Try[String] = Success("DEFAULT")

  val betterChained = betterUnsafeMethod().orElse(betterBackupMethod())
  betterChained.foreach(println)

  val betterChained2 = betterUnsafeMethod() orElse betterBackupMethod()
  betterChained2.foreach(println)

  // map, flatmap and filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10)) // 3 is not >10, FAILURE
  // => for comprehensions
  val host = "localhost"
  val port = "8080"

  def renderHTML(page: String) = println(page)

  class Connection {
    val random: Random = new Random(System.nanoTime())

    def get(url: String): String = {
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Something went wrong with connection")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HTTPService {
    val random: Random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection()
      else throw new RuntimeException("Someone else took the port, something went wrong with http service")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  val possibleConnection = HTTPService.getSafeConnection(host, port)
  val possibleHTML = possibleConnection.flatMap(con => con.getSafe("/home"))
  possibleHTML.foreach(renderHTML)

  // short version
  HTTPService.getSafeConnection(host, port)
    .flatMap(con => con.getSafe("/home"))
    .foreach(renderHTML)

  // for comprehension
  for {
    connection <- HTTPService.getSafeConnection(host, port)
    html <- connection.getSafe("/home")
  } renderHTML(html)

}
