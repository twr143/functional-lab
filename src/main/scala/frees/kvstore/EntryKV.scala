package frees.kvstore
import frees.kvstore.Handlers.KVStoreState
import freestyle.free.FreeS

/**
  * Created by Ilya Volynin on 03.03.2019 at 13:17.
  */
object EntryKV {
  def program[F[_]](implicit B: Backend[F]): FreeS[F, Option[Int]] = {
    import B.store._, B.log._
    import freestyle.free._
    for {
      _ <- put("wild-cats", 2)
      _ <- info("Added wild-cats")
      _ <- update[Int]("wild-cats", (_ + 12))
      _ <- info("Updated wild-cats")
      _ <- put("tame-cats", 5)
      n <- get[Int]("wild-cats")
      _ <- delete("tame-cats")
      _ <- warn("Deleted tame-cats")
    } yield n
  }

  def main(args: Array[String]): Unit = {
    import Handlers._
    import freestyle.free.implicits._
    import freestyle.free._
    println(program[Backend.Op].interpret[KVStoreState].run(Map.empty).value)
  }
}
