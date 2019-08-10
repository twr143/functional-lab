package frees.valid2
import freestyle.free.logging.LoggingM
import freestyle.free.module
import freestyle.free._
import freestyle.free.implicits._
import freestyle.free.logging._
import freestyle.free.loggingJVM.implicits._

/**
  * Created by Ilya Volynin on 23.04.2019 at 20:33.
  */
@module trait Module {
  val interaction: Interaction.StackSafe
  val validation: Validation.StackSafe
  val log: LoggingM
}


