package frees.validInterp.entries
import frees.validInterp.{App, Interaction, Validation}
import freestyle.free.logging.LoggingM
import freestyle.free._
import freestyle.free.implicits._
import freestyle.free.logging._
import freestyle.free.loggingJVM.implicits._

import scala.util.Try

/**
  * Created by Ilya Volynin on 03.03.2019 at 15:54.
  */
object CombinedVIEntry {
  def taglessProgram[F[_]]
     (implicit log: LoggingM[F],
               validation : Validation.StackSafe[F],
               interaction: Interaction.StackSafe[F]) = {

    import cats.implicits._

    for {
      userInput <- interaction.ask("Give me something with at least 3 chars and a number on it")
      valid     <- (validation.minSize(userInput, 3), validation.hasNumber(userInput)).mapN(_ && _)
      _         <- if (valid)
                      interaction.tell("valid and good input!")
                   else
                      interaction.tell(s"$userInput is not valid")
      _         <- log.debug("Program finished")
    } yield ()
  }

  def main(args: Array[String]): Unit = {
    import cats.implicits._
    import freestyle.free._
    import frees.validInterp.Handlers._
    taglessProgram[App.Op].interpret[Try]
  }
}
