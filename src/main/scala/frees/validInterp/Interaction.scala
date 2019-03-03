package frees.validInterp

/**
  * Created by Ilya Volynin on 03.03.2019 at 15:24.
  */
import freestyle.tagless._

@tagless(true) trait Interaction {
  def tell(msg: String): FS[Unit]
  def ask(prompt: String): FS[String]
}
