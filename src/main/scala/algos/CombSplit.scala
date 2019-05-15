package algos
import scala.collection.mutable.ListBuffer

/**
  * Created by Ilya Volynin on 14.05.2019 at 17:40.
  */
object CombSplit {

  def nonRepeatingPerm[T](elems: List[T], genSize: Int, f: List[T] => Unit)(implicit aggregate: ListBuffer[List[T]]): Unit = {
    def removeElem(elems: List[T], elem: T): List[T] = elems match {
      case Nil => Nil
      case head :: tail => if (head == elem) removeElem(tail, elem) else head :: removeElem(tail, elem)
    }

    def nonRepeatingPermRec(elems: List[T], depth: Int, f: List[T] => Unit, partResult: List[T])(implicit aggregate: ListBuffer[List[T]]): Unit = depth match {
      case 0 => f(List())
      case 1 => for (elem <- elems) f(elem :: partResult)
      case _ => for (elem <- elems) nonRepeatingPermRec(removeElem(elems, elem), depth - 1, f, elem :: partResult)
    }

    if (genSize < 0) throw new IllegalArgumentException("Negative generation sizes not allowed in nonRepeatingPerm...")
    if (genSize > elems.size) throw new IllegalArgumentException("Generation sizes over elems.size not allowed in nonRepeatingPerm...")
    nonRepeatingPermRec(elems, genSize, f, Nil)
  }

  implicit class listExt[T](s: List[T]) {

    def isSorted(implicit ord: Ordering[T]): Boolean = s match {
      case Seq() => true
      case Seq(_) => true
      case s2 => s2.sliding(2).forall { case Seq(x, y) => ord.lt(x, y) }
    }
  }

  def main(args: Array[String]): Unit = {
    //choosing three groups of length 2, 3 and 4 out of 9 elements
    val l = List(1, 2, 3, 4, 5)

    def f(thresh1: Int, thresh2: Int)(implicit agg: ListBuffer[List[Int]]): (List[Int]) => Unit =
      l => if (l.slice(0, thresh1).isSorted
        && l.slice(thresh1, thresh2).isSorted
        && l.slice(thresh2, l.size).isSorted)
        agg += l

    implicit val agg = ListBuffer.empty[List[Int]]
    println("perms")
    val thresh1 = 2
    val thresh2 = 3
    nonRepeatingPerm(l, 5, f(thresh1, thresh2))
    println(agg.map(_.foldLeft("")(_ + _)).sortWith((a, b) => a < b))
    println(agg.size)
  }
}
