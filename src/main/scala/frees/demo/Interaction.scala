package frees.demo

import freestyle.free._
// import freestyle.free._


import freestyle.free.implicits._
/**
  * Created by Ilya Volynin on 02.03.2019 at 10:11.
  */
@free trait Interaction {
  def tell(msg: String): FS[Unit]
  def ask(prompt: String): FS[String]
}