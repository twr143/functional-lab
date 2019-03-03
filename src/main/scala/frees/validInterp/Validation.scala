package frees.validInterp


import freestyle.tagless._
/**
  * Created by Ilya Volynin on 03.03.2019 at 15:23.
  */
@tagless(true) trait Validation {
  def minSize(s: String, n: Int): FS[Boolean]
  def hasNumber(s: String): FS[Boolean]
}