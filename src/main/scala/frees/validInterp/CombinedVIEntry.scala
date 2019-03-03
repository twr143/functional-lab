package frees.validInterp

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
                      interaction.tell("awesomesauce!")
                   else
                      interaction.tell(s"$userInput is not valid")
      _         <- log.debug("Program finished")
    } yield ()
  }

  @module trait App {
    val interaction: Interaction.StackSafe
    val validation: Validation.StackSafe
    val log: LoggingM
  }

  def main(args: Array[String]): Unit = {
    import Handlers._
    import cats.implicits._
    taglessProgram[App.Op].interpret[Try]
  }
}
