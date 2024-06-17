package lectures.part3fp

import scala.util.Random

object Options extends App {
  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)

  // unsafe API
  def unsafeMethod(): String =
    null

  //  val result = Some(null) // WRONG
  val result = Option(unsafeMethod())
  println(result)

  // chained methods
  def backupMethod(): String = "A valid result"

  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))
  println(chainedResult)

  // DESIGN UNSAFE API - make your methods return option of something in
  // case of returning nulls - this is the better approach
  // unsafe API
  def betterUnsafeMethod(): Option[String] =
    None

  def betterBackupMethod(): Option[String] =
    Some("ValidResuld")

  val betterChainedResult = betterUnsafeMethod().orElse(betterBackupMethod())

  // functions on options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UNSAFE
  //  println(betterUnsafeMethod().get)
  println(betterUnsafeMethod().getOrElse("DEFAULT"))

  // map flatmap filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(_ % 2 == 1))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // for comprehensions
  /*
  Exercise.
  // establish a connection
   */
  val config: Map[String, String] = Map(
    // these were fetched from somewhere else, it is not certain that the host or port are in the map
    "host" -> "176.45.0.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" // in reality it would connect to some server
  }

  // companion object for this object has
  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection())
      else None
  }

  // try to establish a connection, if so - print connect method
  val resultSafe: Option[(String, String)] =
    if (config("port") == null || config("host") == null) None
    else Some((config("host") -> config("port")))

  println(resultSafe.flatMap(x => Connection(x._1, x._2)).map(_.connect))

  val host: Option[String] = config.get("host")
  val port: Option[String] = config.get("port")
  val maybeConnection: Option[Connection] = host.flatMap(h => port.flatMap(p => Connection(h, p)))
  val maybeConnectionStatus: Option[String] = maybeConnection.map(_.connect)
  maybeConnectionStatus.foreach(println)

  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  forConnectionStatus.foreach(println)
}
