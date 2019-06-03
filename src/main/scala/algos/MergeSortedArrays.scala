package algos
import scala.collection.mutable.ListBuffer

/**
  * Created by Ilya Volynin on 16.05.2019 at 10:04.
  */
object MergeSortedArrays {

  def merge(arrays: Array[Array[Int]]): ListBuffer[Int] = {
    val indexes: Array[Int] = Array.fill(arrays.length)(0)
    val result = ListBuffer.empty[Int]
    for {
      value <- List.range(1, 100)
      index <- List.range(0, indexes.length)
      _ = while (indexes(index) < arrays(index).length && arrays(index)(indexes(index)) == value) {
        indexes(index) += 1
        result += value
      }
    } yield ()
    result
  }

  def main(args: Array[String]): Unit = {
    val ar1 = Array[Int](5, 53, 76, 86, 91,95)
    val ar2 = Array[Int](4, 53, 76, 76, 92)
    val ar3 = Array[Int](1, 2, 3, 3, 3)
    val source = Array[Array[Int]](ar1, ar2, ar3)
    println(merge(source))
  }
}
