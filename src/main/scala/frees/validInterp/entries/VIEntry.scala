package frees.validInterp.entries
import cats.Monad
import frees.validInterp.{Handlers, Interaction, Validation}
import freestyle.free._
import cats.implicits._
import scala.util.Try

/**
  * Created by Ilya Volynin on 03.03.2019 at 15:26.
  */
object VIEntry {

  def taglessProgram[F[_] : Monad](implicit validation: Validation[F], interaction: Interaction[F]) =
    for {
      userInput <- interaction.ask("Give me something with at least 3 chars and a number on it")
      valid <- (validation.minSize(userInput, 3), validation.hasNumber(userInput)).mapN(_ && _)
      _ <- if (valid)
        interaction.tell("awesomesauce!")
      else
        interaction.tell(s"$userInput is not valid")
    } yield ()

  def main(args: Array[String]): Unit = {
    import Handlers._
    taglessProgram[Try]
    //    taglessProgram[Free[Try, ?]].runTailRec    //how to inject?
  }
}
