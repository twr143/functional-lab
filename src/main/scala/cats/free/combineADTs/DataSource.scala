package cats.free.combineADTs
import cats.free.Free
import cats.{Id, InjectK, ~>}
import cats.free.combineADTs.DTs._
/**
  * Created by Ilya Volynin on 01.03.2019 at 15:43.
  */
class DataSource[F[_]](implicit I: InjectK[DataOp, F]) {
  def addCat(a: String): Free[F, Unit] = Free.inject[DataOp, F](AddCat(a))
  def getAllCats: Free[F, List[String]] = Free.inject[DataOp, F](GetAllCats())
}

object DataSource {
  implicit def dataSource[F[_]](implicit I: InjectK[DataOp, F]): DataSource[F] = new DataSource[F]
}
