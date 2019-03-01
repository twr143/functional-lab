package free.combineADTs

import cats.data.EitherK
import cats.free.Free
import cats.{Id, InjectK, ~>}

/**
  * Created by Ilya Volynin on 01.03.2019 at 15:40.
  */
object DTs {
  /* Handles user interaction */
  sealed trait Interact[A]
  case class Ask(prompt: String) extends Interact[String]
  case class Tell(msg: String) extends Interact[Unit]

  /* Represents persistence operations */
  sealed trait DataOp[A]
  case class AddCat(a: String) extends DataOp[Unit]
  case class GetAllCats() extends DataOp[List[String]]
  type CatsApp[A] = EitherK[DataOp, Interact, A]
}
