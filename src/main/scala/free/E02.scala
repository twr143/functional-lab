package free
import cats.data.State

/**
  * Created by Ilya Volynin on 27.02.2019 at 15:07.
  */
import cats.arrow.FunctionK
import cats.{Id, ~>}
import cats.free.Free
import cats.free.Free.liftF
import free.KVStore._

import scala.collection.mutable

object E02 {


  type KVStoreState[A] = State[Map[String, Any], A]
  val pureCompiler: KVStoreA ~> KVStoreState = new (KVStoreA ~> KVStoreState) {
    def apply[A](fa: KVStoreA[A]): KVStoreState[A] =
      fa match {
        case Put(key, value) => State.modify(_.updated(key, value))
        case Get(key) =>
          State.inspect(_.get(key).map(_.asInstanceOf[A]))
        case Delete(key) => State.modify(_ - key)
      }
  }

  def main(args: Array[String]): Unit = {
    val result: (Map[String, Any], Option[Int]) = program.flatMap(program2).foldMap(pureCompiler).run(Map.empty).value
    println(result)
  }
}
