package frees.demo

import freestyle.free._
import freestyle.free.implicits._
// import freestyle.free._
/**
  * Created by Ilya Volynin on 02.03.2019 at 9:46.
  */
@free trait Validation {
  def minSize(s: String, n: Int): FS[Boolean]
  def hasNumber(s: String): FS[Boolean]
}