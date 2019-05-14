package algos
import scala.collection.mutable.ListBuffer

/**
  * Created by Ilya Volynin on 04.05.2019 at 21:51.
  */
object Combinations {

  def combs(length: Int, source: => String): List[String] =
    if (length == 1) source.toList.map(_.toString)
    else combs(length - 1, source.substring(1)).flatMap(str => (source.substring(0, length - str.length).map(_ + str)))

  def nonRepeatingComb[T](elems: List[T], genSize: Int, f: List[T] => Unit)(implicit aggregate: ListBuffer[List[T]]): Unit = {
    def nonRepeatingCombRec(elems: List[T], depth: Int, partResult: List[T], desc: String)(implicit aggregate: ListBuffer[List[T]]): Unit = {
      //      println(s"desc: $desc")
      if (elems.size == depth) f(elems.reverse ::: partResult)
      else {
        if (!elems.isEmpty) {
          //          println(s"tail: ${elems.tail}, depth: $depth , part result: $partResult, elems head: ${elems.head} ")
          nonRepeatingCombRec(elems.tail, depth, partResult, "only "
            + s"tail:${elems.tail.reverse}, " +
            s"depth: $depth, pr:${partResult}")
          if (depth > 0) nonRepeatingCombRec(elems.tail, depth - 1, elems.head :: partResult,
            s"head ${elems.head} added, tail:${elems.tail.reverse}, " +
              s"depth: ${depth - 1}, pr:${partResult}")
        }
      }
    }

    if (genSize < 0) throw new IllegalArgumentException("Negative generation sizes not allowed in nonRepeatingComb...")
    if (genSize > elems.size) throw new IllegalArgumentException("Generation sizes over elems.size not allowed in nonRepeatingComb...")
    nonRepeatingCombRec(elems.reverse, genSize, Nil, "initial")
  }

  def nonRepeatingPerm[T](elems: List[T], genSize: Int, f: List[T] => Unit)(implicit aggregate: ListBuffer[List[T]]): Unit = {
    def removeElem(elems: List[T], elem: T): List[T] = elems match {
      case Nil => Nil
      case head :: tail => if (head == elem) removeElem(tail, elem) else head :: removeElem(tail, elem)
    }

    def nonRepeatingPermRec(elems: List[T], depth: Int, f: List[T] => Unit, partResult: List[T])(implicit aggregate: ListBuffer[List[T]]): Unit = depth match {
      case 0 => f(List())
      case 1 => for (elem <- elems) f(elem :: partResult)
      case n => for (elem <- elems) nonRepeatingPermRec(removeElem(elems, elem), depth - 1, f, elem :: partResult)
    }

    if (genSize < 0) throw new IllegalArgumentException("Negative generation sizes not allowed in nonRepeatingPerm...")
    if (genSize > elems.size) throw new IllegalArgumentException("Generation sizes over elems.size not allowed in nonRepeatingPerm...")
    nonRepeatingPermRec(elems, genSize, f, Nil)
  }

  def main(args: Array[String]): Unit = {
    val l = List(1, 2, 3, 4, 5)

    def f(implicit agg: ListBuffer[List[Int]]): (List[Int]) => Unit =
      l => agg.append(l)

    implicit var agg = ListBuffer.empty[List[Int]]
    nonRepeatingPerm(l, 5, f)
    println(agg.map(_.foldLeft("")(_ + _)).sortWith((a, b) => a < b))
    println(agg.size)

    agg = ListBuffer.empty[List[Int]]
    nonRepeatingComb(l, 3, f)
    println("now combs")
    println(agg.map(_.foldLeft("")(_ + _)).sortWith((a, b) => a < b))
    println(agg.size)
  }
}
