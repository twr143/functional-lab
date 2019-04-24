package frees.valid2.entries
import frees.valid2.{Interaction, Module, Validation}
import freestyle.free.logging.LoggingM
import freestyle.free._
import freestyle.free.implicits._
import freestyle.free.logging._
import freestyle.free.loggingJVM.implicits._

import scala.util.Try

/**
  * Created by Ilya Volynin on 23.04.2019 at 20:37.
  */
object TryE1 {

  def taglessProgram[F[_]]
  (implicit log: LoggingM[F],
   validation: Validation.StackSafe[F],
   interaction: Interaction.StackSafe[F]) = {
    import cats.implicits._
    import utils.ImplicitExtensions._
    for {
      userInput <- interaction.ask("Give me something with at least 3 chars and a number on it")
      rules <- validation.rules
      msgs = rules.foldLeft(Set.empty[String])((currentErrors, rule) =>
        currentErrors ++
          rule(userInput).toOption)

       _ <- if (msgs.isEmpty)

      interaction.tell(s"${userInput} is a valid and good input!")
      else
      interaction.tell(s""""${userInput}" is not valid, the errors are:  $msgs""" )
        _ <- log.debug("Program finished")
    } yield ()
  }

  def main(args: Array[String]): Unit = {
    import cats.implicits._
    import freestyle.free._
    import frees.valid2.Handlers._
    taglessProgram[Module.Op].interpret[Try]

  }
}
