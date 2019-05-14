package algos
import scala.collection.mutable.ListBuffer

/**
  * Created by Ilya Volynin on 04.05.2019 at 21:05.
  */
object LengthSubSort {

  def lSort: List[List[Symbol]] => List[List[Symbol]] =
    l => l.map(_.foldLeft("")(_ + _).replace("'","")).sortWith(_.length < _.length).map(_.toList.map(c => Symbol(c.toString)))

  def main(args: Array[String]): Unit = {
    val l = List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o))
    println(lSort(l))
  }
}
