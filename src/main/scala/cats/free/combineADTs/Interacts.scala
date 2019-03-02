package cats.free.combineADTs

import cats.data.EitherK
import cats.free.Free
import cats.{Id, InjectK, ~>}
import cats.free.combineADTs.DTs._

import scala.collection.mutable.ListBuffer
/**
  * Created by Ilya Volynin on 01.03.2019 at 15:41.
  */
class Interacts[F[_]](implicit I: InjectK[Interact, F]) {
  def tell(msg: String): Free[F, Unit] = Free.inject[Interact, F](Tell(msg))
  def ask(prompt: String): Free[F, String] = Free.inject[Interact, F](Ask(prompt))
}

object Interacts {
  implicit def interacts[F[_]](implicit I: InjectK[Interact, F]): Interacts[F] = new Interacts[F]
}