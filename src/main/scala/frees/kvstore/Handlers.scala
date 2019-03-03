package frees.kvstore

/**
  * Created by Ilya Volynin on 03.03.2019 at 13:09.
  */
object Handlers {
  import cats.data.State
  import cats.implicits._
  import freestyle.free._

  // import cats.data.State
  type KVStoreState[A] = State[Map[String, Any], A]

  // defined type alias KVStoreState
  implicit val kvStoreHandler: KVStore.Handler[KVStoreState] = new KVStore.Handler[KVStoreState] {
    def put[A](key: String, value: A): KVStoreState[Unit] =
      State.modify(_.updated(key, value))

    def get[A](key: String): KVStoreState[Option[A]] =
      State.inspect(_.get(key).map(_.asInstanceOf[A]))

    def delete(key: String): KVStoreState[Unit] =
      State.modify(_ - key)
  }

  implicit def logHandler: Log.Handler[KVStoreState] =
    new Log.Handler[KVStoreState] {
      def info(msg: String): KVStoreState[Unit] = println(s"INFO: $msg").pure[KVStoreState]

      def warn(msg: String): KVStoreState[Unit] = println(s"WARN: $msg").pure[KVStoreState]
    }
}
