package utils
import scala.util.{Failure, Success, Try}

/**
  * Created by Ilya Volynin on 23.04.2019 at 20:46.
  */
object ImplicitExtensions {

  implicit class StringExt(s: String) {

    def toOption(): Option[String] = if (s.isEmpty) None else Some(s)
  }

  implicit class OptionExt[+A](self: Option[A]) {

    def toTry(onEmpty: => Throwable): Try[A] = {
      if (self.isEmpty) Failure(onEmpty)
      else Success(self.get)
    }
  }

}
