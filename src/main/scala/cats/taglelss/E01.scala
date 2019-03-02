package cats.taglelss
import cats._
import cats.data.Const
import cats.implicits._

/**
  * Created by Ilya Volynin on 01.03.2019 at 17:39.
  */
object E01 {

  val analysisInterpreter: KVStore[Const[(Set[String], Map[String, String]), ?]] =
    new KVStore[Const[(Set[String], Map[String, String]), ?]] {
      def get(key: String) = Const((Set(key), Map.empty))

      def put(key: String, a: String) = Const((Set.empty, Map(key -> a)))
    }

  def program1[M[_] : FlatMap, F[_]](a: String)(K: KVStore[M])(implicit P: Parallel[M, F]) =
    for {
      _ <- K.put("A", a)
      x <- (K.get("B"), K.get("C")).parMapN(_ |+| _)
      _ <- K.put("X", x.getOrElse("-"))
    } yield x

  def program2[F[_] : Apply](F: KVStore[F]): F[List[String]] =
    (F.get("Cats"), F.get("Dogs"), F.put("Mice", "42"), F.get("Cats"))
      .mapN((f, s, _, t) => List(f, s, t).flatten)

  def main(args: Array[String]): Unit = {
    println(program2[Const[(Set[String], Map[String, String]), ?]](analysisInterpreter).getConst)
  }
}
