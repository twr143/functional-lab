package cats.example
import cats._
import cats.instances.all._
import cats.syntax.functor._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Success

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
}

