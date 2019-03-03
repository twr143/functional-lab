package frees.kvstore

import freestyle.free._
// import freestyle.free._

import cats.implicits._
import freestyle.free.implicits._
@free trait KVStore {
  def put[A](key: String, value: A): FS[Unit]
  def get[A](key: String): FS[Option[A]]
  def delete(key: String): FS[Unit]
  def update[A](key: String, f: A => A): FS.Seq[Unit] =
    get[A](key).freeS flatMap {
      case Some(a) => put[A](key, f(a)).freeS
      case None => ().pure[FS.Seq]
    }
}
