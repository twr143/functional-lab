package cats.taglelss
/**
  * Created by Ilya Volynin on 01.03.2019 at 17:37.
  */
trait KVStore[F[_]] {

  def get(key: String): F[Option[String]]

  def put(key: String, a: String): F[Unit]
}
