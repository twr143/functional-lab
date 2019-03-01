package free.combineADTs
import cats.free.Free
import cats.{Id, ~>}
import free.combineADTs.DTs._

import scala.collection.mutable.ListBuffer

/**
  * Created by Ilya Volynin on 01.03.2019 at 15:48.
  */
object EntryMultiADTs {

  def program(implicit I : Interacts[CatsApp], D : DataSource[CatsApp]): Free[CatsApp, Unit] = {

    import I._, D._

    for {
      cat <- ask("What's the kitty's name?")
      _ <- addCat(cat)
      cats <- getAllCats
      _ <- tell(cats.toString)
    } yield ()
  }

  object ConsoleCatsInterpreter extends (Interact ~> Id) {
    def apply[A](i: Interact[A]) = i match {
      case Ask(prompt) =>
        println(prompt)
        readLine()
      case Tell(msg) =>
        println(msg)
    }
  }

  object InMemoryDatasourceInterpreter extends (DataOp ~> Id) {

    private[this] val memDataSet = new ListBuffer[String]

    def apply[A](fa: DataOp[A]): Id[A] = fa match {
      case AddCat(a) => memDataSet.append(a)
      case GetAllCats() => memDataSet.toList
    }
  }

  val interpreter: CatsApp ~> Id = InMemoryDatasourceInterpreter or ConsoleCatsInterpreter

  def main(args: Array[String]): Unit = {
    program.foldMap(interpreter)
  }
}
