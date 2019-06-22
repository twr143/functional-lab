package algos.leetCode
import scala.collection.mutable.Stack

/**
  * Created by Ilya Volynin on 20.06.2019 at 21:37.
  */
object MaxRectInM2 {

  def maximalRectangle(matrix: Array[Array[Char]]): Int = {
    if (matrix == null || matrix.length == 0 || matrix(0).length == 0) return 0
    val m = matrix.length
    val n = matrix(0).length
    var max = 0
    val height = new Array[Int](n)
    var i = 0
    for (i <- 0 to m - 1) {
      print(s"i:$i heigths:")
      for (j <- 0 to n - 1)
        if (matrix(i)(j) == '1') height(j) += 1
        else height(j) = 0
      for (j <- 0 to n - 1)
        print(s"${height(j)} ")
      println()
      val s = Stack[Int]()
      for (k <- 0 to n) {
        val cur = if (k == n) -1 else height(k)
        while (!s.isEmpty && cur < height(s.top)) {
          println(s"cur =$cur, h(s.top)= ${height(s.top)}")
          val hspop = height(s.pop)
          val w = if (s.isEmpty) k else k - s.top - 1
          max = Math.max(hspop * w, max)
          println(s"hspop =$hspop, w= $w, max = $max")
        }
        s.push(k)
        println(s"k $k stack: $s")
      }
      println()
    }
    max
  }

  def main(args: Array[String]): Unit = {
    import Array._
    //    val array = ofDim[Char](10, 10)
    //    for (i <- 0 to 9) {
    //      for (j <- 0 to 9)
    //        array(i)(j) = {
    //          val c: Integer = (48 + (i + j) % 2)
    //          print(c.toChar)
    //          print(' ')
    //          c.toChar
    //        }
    //      println()
    //    }
    val array = Array(
      Array('0', '0', '1', '1', '0'),
      Array('0', '0', '1', '1', '1'),
      Array('1', '1', '0', '1', '1'),
      Array('0', '0', '0', '0', '0'),
      Array('0', '1', '1', '0', '1')
    )
    println(maximalRectangle(array))
  }
}
