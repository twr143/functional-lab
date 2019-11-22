package algos.leetCode.twitter
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import util.control.Breaks._

/**
  * Created by Ilya Volynin on 22.11.2019 at 12:57.
  */
object Utils {

  //  implicit object orderingMock extends Ordering[Int] {
  //
  //    def compare(o1: Int, o2: Int): Int = o1 - o2
  //  }
  def mergeKSortedSetsTakeN(sets: ArrayBuffer[mutable.TreeSet[Int]], limit: Int)
                           (implicit o: Ordering[Int]): mutable.TreeSet[Int] = {
    var resultSet = new mutable.TreeSet[Int]()
    val iters = new Array[Iterator[Int]](sets.size)
    for (i <- 0 to sets.size - 1)
      iters(i) = sets(i).iterator
    var current = mutable.Set.empty[(Int, Int)] //(index,value)
    for (i <- 0 to sets.size - 1)
      if (iters(i).hasNext)
        current += ((i, iters(i).next()))
    while (resultSet.size < limit && current.nonEmpty) {
      val headPair = findMax2(current)
      resultSet += headPair._2
      current.remove(headPair)
      if (iters(headPair._1).hasNext)
        current += ((headPair._1, iters(headPair._1).next()))
    }
    resultSet
  }

  def findMax2(set: mutable.Set[(Int, Int)]): (Int, Int) = {
    val orderingPairsByVals = new Ordering[(Int, Int)] {
      def compare(o1: (Int, Int), o2: (Int, Int)): Int = o1._2 - o2._2
    }
    set.max(orderingPairsByVals)
  }

  def main(args: Array[String]): Unit = {
    implicit val orderingVals = new Ordering[Int] {
      def compare(o1: Int, o2: Int): Int = o2 - o1
    }
    val set1 = mutable.TreeSet(1, 8, 4)
    val set2 = mutable.TreeSet(2, 7, 3)
    val set3 = mutable.TreeSet(3, 5, 6)
    val set4 = mutable.TreeSet(12, 13, 17)
    val set5 = mutable.TreeSet(13, 15, 16)
    println(mergeKSortedSetsTakeN(ArrayBuffer(set1, set2, set3, set4, set5), 4).toList)
    println(mergeKSortedSetsTakeN(ArrayBuffer(set1, set2, set3, set4, set5), 10).toList)
    println(mergeKSortedSetsTakeN(ArrayBuffer(set1, set2, set3, set4, set5), 20).toList)
  }
}
