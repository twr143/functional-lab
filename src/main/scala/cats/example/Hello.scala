package cats.example
import cats._
import cats.instances.all._
import cats.syntax.functor._
import fastparse.core.Parsed
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Success
import utils.ImplicitExtensions._
import scala.util.control.NonFatal

object Hello extends App {

  val len: String => Int = _.length

  val result = Functor[List].map(List("scala", "cats"))(len)

  val result2 = List("scala", "cats").fproduct(len)

  println(s"leghts: $result")

  println(s"pairs: $result2")

  val lst1 = List(1, 2, 3)

  val lst2 = List(4, 5, 6)
  import cats.implicits._

  println(s"++: " + (lst1 ++ lst2))

  println(s"|@|: " + (lst1, lst2).mapN((a, b) => (a + b)))

  (Future(1), Future(2)).mapN((a, b) => a + b).andThen { case Success(x) => println(x) }

  val cValNone: Option[Int] = None
  val cValSome: Option[Int] = Some(3)

  (Some(1).toTry(new IllegalArgumentException("should be 1")),
    Some(2).toTry(new IllegalArgumentException("should be 2")),
    cValSome.toTry(new IllegalArgumentException("should be something"))
  ).mapN {
    (a, b, c) =>
      println(s"success: $c")
      c
  }.recover { case NonFatal(e) => println(s"exc ${e.getMessage}") }
}

