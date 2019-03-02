package frees.demo
/**
  * Created by Ilya Volynin on 02.03.2019 at 11:18.
  */
import cats.effect.IO
import frees.demo.Interaction.Op
import freestyle.free._
import iota.CopK

object EntryDemo {

  implicit val validationHandler = new Validation.Handler[IO] {
    override def minSize(s: String, n: Int): IO[Boolean] = IO {
      s.size >= n
    }

    override def hasNumber(s: String): IO[Boolean] = IO {
      s.exists(c => "0123456789".contains(c))
    }
  }

  implicit val interactionHandler = new Interaction.Handler[IO] {
    def apply[AA$9385](fa$9386: CopK[iota.TListK,Interaction.Op[AA$9385]]): IO[AA$9385] = super.apply(fa$9386.value.asInstanceOf[Interaction.Op[AA$9385]])

    override def tell(s: String): IO[Unit] = IO {
      println(s)
    }

    override def ask(s: String): IO[String] = IO {
      println(s)
      "This could have been user input 1"
    }
  }

  def main(args: Array[String]): Unit = {
    //
    import cats.implicits._
    Application.instance.program.
      //      asInstanceOf[FreeS[iota.CopK[iota.TListK, Interaction.Op[Unit]], Unit]].
      asInstanceOf[FreeS[Op, Unit]].
//      asInstanceOf[FreeS[iota.CopK[iota.TListK.Op.Concat[iota.TConsK[frees.demo.Validation.Op,iota.TNilK],iota.TConsK[frees.demo.Interaction.Op,iota.TNilK]],Unit],Unit]].
      interpret[IO].unsafeRunSync
  }
}
