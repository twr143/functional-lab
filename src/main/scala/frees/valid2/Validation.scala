package frees.valid2
import freestyle.tagless._

/**
  * Created by Ilya Volynin on 23.04.2019 at 20:21.
  */
@tagless(true) trait Validation {

  def rules: FS[List[String => String]]

}
