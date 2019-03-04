package frees.validInterp.entries
import cats.Eval
import frees.validInterp.{App, Interaction, Validation}
import freestyle.free.logging.LoggingM
import freestyle.free._
import freestyle.free.effects.error
import freestyle.free.implicits._
import freestyle.free.logging._
import freestyle.free.loggingJVM.implicits._
import scala.util.Try
import freestyle.free.effects.error._
import freestyle.free.effects.error.implicits._
import scala.util.control.NonFatal

/**
  * Created by Ilya Volynin on 04.03.2019 at 10:30.
  */
object EitherVIEntry {

  def taglessProgram3[F[_]]
  (implicit log: LoggingM[F],
   validation: Validation.StackSafe[F],
   interaction: Interaction.StackSafe[F]) = {
    import cats.implicits._
    for {
      userInput <- interaction.ask("Give me 5")
      _ <- validation.minSize(userInput, 3)
      _ <- validation.hasNumber(userInput)
      _ <- interaction.tell("valid input!")
      _ <- log.debug("Program finished")
    } yield ()
  }

  def main(args: Array[String]): Unit = {
    import cats.implicits._
    import freestyle.free._
    import frees.validInterp.Handlers._

      Try(taglessProgram3[App.Op].interpret[Target]).recover({
        case NonFatal(e) => println(s"caught  ${e.fillInStackTrace().toString}")
      })

  }
}