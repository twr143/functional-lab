package free
import cats.arrow.FunctionK
import cats.{Id, ~>}
import cats.free.Free
import cats.free.Free.liftF
import free.KVStore._

import scala.collection.mutable

/**
  * Created by Ilya Volynin on 27.02.2019 at 14:43.
  */
object E01 {



  def impureCompiler: KVStoreA ~> Id  =
    new (KVStoreA ~> Id) {

      // a very simple (and imprecise) key-value store
      val kvs = mutable.Map.empty[String, Any]

      def apply[A](fa: KVStoreA[A]): Id[A] =
        fa match {
          case Put(key, value) =>
            println(s"put($key, $value)")
            kvs(key) = value
            ()
          case Get(key) =>
            println(s"get($key)")
            kvs.get(key).map(_.asInstanceOf[A])
          case Delete(key) =>
            println(s"delete($key)")
            kvs.remove(key)
            ()
        }
    }
  
  def main(args: Array[String]): Unit = {
    val result: Option[Int] = program.foldMap(impureCompiler)
    println(result)
  }
}
