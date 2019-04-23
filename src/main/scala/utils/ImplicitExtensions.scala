package utils
/**
  * Created by Ilya Volynin on 23.04.2019 at 20:46.
  */
object ImplicitExtensions {


    implicit class StringExt(s: String) {

      def toOption(): Option[String] = if (s.isEmpty) None else Some(s)
    }

}
