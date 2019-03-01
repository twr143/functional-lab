/**
  * Created by Ilya Volynin on 20.07.2018 at 18:22.
  */

import cats.Show
import cats.instances.all._
import cats.syntax.show._


object Intro extends App {
  val showInt : Show[Int] = Show.apply[Int]
  val showString : Show[String] = Show.apply[String]
  val intAsString  = showInt.show(123)
  val intAsString2  = 123.show
  val shownString = "abc".show

}
