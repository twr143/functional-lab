package cats.example

/**
  * Created by Ilya Volynin on 23.04.2019 at 18:31.
  */
object Hello2 {

  def main(args: Array[String]): Unit = {
    val intMatcher: PartialFunction[Int, String] = {
        case 1 => "jak się masz!"
    }

    val liftedIntMatcher: Int => Option[String] = intMatcher.lift

    println(liftedIntMatcher(1))
    //Some(jak się masz!)
    println(liftedIntMatcher(0))
    //None
  }
}
