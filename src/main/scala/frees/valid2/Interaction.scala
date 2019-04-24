package frees.valid2

/**
  * Created by Ilya Volynin on 23.04.2019 at 20:19.
  */
import freestyle.tagless._

@tagless(true) trait Interaction {
  def tell(msg: String): FS[Unit]
  def ask(prompt: String): FS[String]
}
