package frees.validInterp
import freestyle.free.logging.LoggingM
import freestyle.free.module
import freestyle.free._
import freestyle.free.implicits._
import freestyle.free.logging._
import freestyle.free.loggingJVM.implicits._

/**
  * Created by Ilya Volynin on 04.03.2019 at 10:27.
  */
@module trait App {
  val interaction: Interaction.StackSafe
  val validation: Validation.StackSafe
  val log: LoggingM
}
