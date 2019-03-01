package free
import cats.free.Free
import cats.free.Free.liftF

/**
  * Created by Ilya Volynin on 27.02.2019 at 15:05.
  */
object KVStore {

  sealed trait KVStoreA[A]

  case class Put[T](key: String, value: T) extends KVStoreA[Unit]

  case class Get[T](key: String) extends KVStoreA[Option[T]]

  case class Delete(key: String) extends KVStoreA[Unit]

  type KVStore[A] = Free[KVStoreA, A]

  def put[T](key: String, value: T): KVStore[Unit] =
    liftF[KVStoreA, Unit](Put[T](key, value))

  // Get returns a T value.
  def get[T](key: String): KVStore[Option[T]] =
    liftF[KVStoreA, Option[T]](Get[T](key))

  // Delete returns nothing (i.e. Unit).
  def delete(key: String): KVStore[Unit] =
    liftF(Delete(key))

  // Update composes get and set, and returns nothing.
  def update[T](key: String, f: T => T): KVStore[Unit] =
    for {
      vMaybe <- get[T](key)
      _ <- vMaybe.map(v => put[T](key, f(v))).getOrElse(Free.pure(()))
    } yield ()

  def program: KVStore[Option[Int]] =
    for {
      _ <- put("wild-cats", 2)
      _ <- update[Int]("wild-cats", _ + 12)
      _ <- put("tame-cats", 5)
      n <- get[Int]("wild-cats")
      _ <- delete("tame-cats")
    } yield n

  def program2: Option[Int] => KVStore[Option[Int]] =
    _ => for {
      _ <- update[Int]("wild-cats", _ + 50)
      n <- get[Int]("wild-cats")
    } yield n
}
