package frees.demo
import freestyle.free.module
import cats.implicits._
import freestyle.free.implicits._
import freestyle.free._

/**
  * Created by Ilya Volynin on 02.03.2019 at 11:16.
  */
@module trait Application {

  val validation: Validation
  val interaction: Interaction
  import cats.implicits._

  def program: FS.Seq[Unit] =
    for {
      userInput <- interaction.ask("Give me something with at least 3 chars and a number on it")
      valid <- (validation.minSize(userInput, 3), validation.hasNumber(userInput)).mapN(_ && _).freeS
      _ <- if (valid)
        interaction.tell("awesomesauce!")
      else
        interaction.tell(s"$userInput is not valid")
    } yield ()
}
