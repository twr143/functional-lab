package frees.validInterp.entries
import frees.validInterp.{App, Interaction, Validation}
import freestyle.free.logging.LoggingM
import freestyle.free._
import freestyle.free.implicits._
import freestyle.free.logging._
import freestyle.free.loggingJVM.implicits._
import scala.util.Try

/**
  * Created by Ilya Volynin on 04.03.2019 at 10:30.
  */
object OptionVIEntry {

  def taglessProgram2[F[_]]
  (implicit log: LoggingM[F],
   validation: Validation.StackSafe[F],
   interaction: Interaction.StackSafe[F]) = {
    import cats.implicits._
    for {
      userInput <- interaction.ask("Give me 5")
      valid <- (validation.minSize(userInput, 3), validation.hasNumber(userInput)).mapN(_ && _)
      _ <- if (valid)
        interaction.tell("valid input!")
      else
        interaction.tell(s"$userInput is not valid")
      _ <- log.debug("Program finished")
    } yield ()
  }

  def main(args: Array[String]): Unit = {
    import cats.implicits._
    import freestyle.free._
    import frees.validInterp.Handlers._
    taglessProgram2[App.Op].interpret[Option]
  }
}
