package algos.leetCode
import scala.collection.mutable.Queue
import scala.collection.mutable.ArrayBuffer

/**
  * Created by Ilya Volynin on 18.06.2019 at 18:37.
  */
object MaxRectInMatrix {

  def spiralOrder[T](matrix: Array[Array[T]]): ArrayBuffer[T] = {
    val ans = ArrayBuffer[T]()
    if (matrix.length == 0) return ans
    var r1 = (matrix.length - 1) / 2
    var r2 = matrix.length / 2
    var c1 = (matrix(0).length - 1) / 2
    var c2 = matrix(0).length / 2
    while (r1 >= 0 && c1 >= 0) {
      for (c <- c1 to c2) ans += matrix(r1)(c)
      for (r <- r1 + 1 to r2) ans += matrix(r)(c2)
      if (r1 < r2 && c1 < c2) {
        for (c <- c2 - 1 to c1 + 1 by -1) ans += matrix(r2)(c)
        for (r <- r2 to r1 + 1 by -1) ans += matrix(r)(c1)
      }
      r1 -= 1
      r2 += 1
      c1 -= 1
      c2 += 1
    }
    ans
  }

  def reverseSpiralOrderSubMCond2(matrix: Array[Array[Char]], left: Int, top: Int,
                                  right: Int, bottom: Int, f: Char => Boolean, q: Queue[Task]): Int = {
    //    println(s"left:$left top$top right:$right, bottom:$bottom")
    //    val ans = ArrayBuffer[Char]()
    if (matrix.length == 0) return 0
    //for testing only
    //    val p = particularCases(matrix, left, top, right, bottom)
    //    if (p._1) return p._2
    val width = right - left + 1
    val heigth = bottom - top + 1
    var t = top
    var b = bottom
    var l = left
    var r = right
    if (width > heigth) { //to make matrix squared
      l = left + (width - heigth + 1) / 2
      r = right - (width - heigth) / 2
    } else if (width < heigth) {
      t = top + (heigth - width + 1) / 2
      b = bottom - (heigth - width) / 2
    }
    var r1 = t + (b - t) / 2
    var r2 = t + (b - t + 1) / 2
    var c1 = l + (r - l) / 2
    var c2 = l + (r - l + 1) / 2
    while (r1 >= t && c1 >= l) {
      for (c <- c1 to c2) {
        //        ans += matrix(r1)(c)
        if (f(matrix(r1)(c))) {
          return decomposeOnto4SubM(matrix, left, top, right, bottom, f, c, r1, q)
        }
      }
      for (r <- r1 + 1 to r2) {
        //        ans += matrix(r)(c2)
        if (f(matrix(r)(c2))) {
          return decomposeOnto4SubM(matrix, left, top, right, bottom, f, c2, r, q)
        }
      }
      if (c1 < c2 && r1 < r2) {
        for (c <- c2 - 1 to c1 + 1 by -1) {
          //          ans += matrix(r2)(c)
          if (f(matrix(r2)(c))) {
            return decomposeOnto4SubM(matrix, left, top, right, bottom, f, c, r2, q)
          }
        }
        for (r <- r2 to r1 + 1 by -1) {
          //          ans += matrix(r)(c1)
          if (f(matrix(r)(c1))) {
            return decomposeOnto4SubM(matrix, left, top, right, bottom, f, c1, r, q)
          }
        }
      }
      r1 -= 1
      r2 += 1
      c1 -= 1
      c2 += 1
    }
    if (width > heigth) {
      for (i <- 1 to right - r)
        for (j <- t to b) {
          //          ans += matrix(j)(r + i)
          if (f(matrix(j)(r + i))) {
            return decomposeOnto4SubM(matrix, left, top, right, bottom, f, r + i, j, q)
          }
          //          ans += matrix(j)(l - i)
          if (f(matrix(j)(l - i))) {
            return decomposeOnto4SubM(matrix, left, top, right, bottom, f, l - i, j, q)
          }
        }
    }
    if (l - left > right - r)
      for (j <- t to b) {
        //        ans += matrix(j)(left)
        if (f(matrix(j)(left))) {
          return decomposeOnto4SubM(matrix, left, top, right, bottom, f, left, j, q)
        }
      }
    else if (width < heigth) {
      for (j <- 1 to bottom - b)
        for (i <- l to r) {
          //          ans += matrix(t - j)(i)
          //          ans += matrix(b + j)(i)
          if (f(matrix(t - j)(i))) {
            return decomposeOnto4SubM(matrix, left, top, right, bottom, f, i, t - j, q)
          }
          if (f(matrix(b + j)(i))) {
            return decomposeOnto4SubM(matrix, left, top, right, bottom, f, i, b + j, q)
          }
        }
      if (t - top > bottom - b)
        for (i <- l to r) {
          //          ans += matrix(top)(i)
          if (f(matrix(top)(i))) {
            return decomposeOnto4SubM(matrix, left, top, right, bottom, f, i, top, q)
          }
        }
    }
    (bottom - top + 1) * (right - left + 1)
  }

  def decomposeOnto4SubM(matrix: Array[Array[Char]], left: Int, top: Int,
                         right: Int, bottom: Int, f: Char => Boolean, x: Int, y: Int, q: Queue[Task]): Int = {
    //top
    //    println(s"point x=$x,y=$y")
    var topArea = 0
    if (y > top) {
      val p = particularCases(matrix, left, top, right, y - 1)
      if (p._1)
        topArea = p._2
      else q += Task(matrix, left, top, right, y - 1)
    }
    var rightArea = 0
    if (x < right) {
      val p = particularCases(matrix, x + 1, top, right, bottom)
      if (p._1)
        rightArea = p._2
      else q += Task(matrix, x + 1, top, right, bottom)
    }
    var bottomArea = 0
    if (y < bottom) {
      val p = particularCases(matrix, left, y + 1, right, bottom)
      if (p._1)
        bottomArea = p._2
      else q += Task(matrix, left, y + 1, right, bottom)
    }
    var leftArea = 0
    if (x > left) {
      val p = particularCases(matrix, left, top, x - 1, bottom)
      if (p._1) {
        leftArea = p._2
      } else q += Task(matrix, left, top, x - 1, bottom)
    }
    val tr = Math.max(topArea, rightArea)
    val bl = Math.max(bottomArea, leftArea)
    Math.max(tr, bl)
  }

  def particularCases(matrix: Array[Array[Char]], left: Int, top: Int,
                      right: Int, bottom: Int): (Boolean, Int) = {
    //    println(s"part cases: l:$left,t:$top,r:$right,b:$bottom")
    if (right - left == 2 && bottom - top == 1) {
      //3x2
      val a11 = matrix(top)(left).toInt - 48
      val a12 = matrix(top)(left + 1).toInt - 48
      val a13 = matrix(top)(left + 2).toInt - 48
      val a21 = matrix(top + 1)(left).toInt - 48
      val a22 = matrix(top + 1)(left + 1).toInt - 48
      val a23 = matrix(top + 1)(left + 2).toInt - 48
      val s = a11 + a12 + a13 + a21 + a22 + a23
      if (s == 0 || s == 1 || s == 6) (true, s)
      else if (s == 2)
        if (a11 + a12 == 2 || a12 + a13 == 2 || a13 + a23 == 2
          || a21 + a22 == 2 || a22 + a23 == 2 || a11 + a21 == 2) (true, 2)
        else (true, 1)
      else if (s == 3)
        if (a11 + a12 + a13 == 3 || a21 + a22 + a23 == 3)
          (true, 3)
        else if (a21 + a12 + a23 == 0 || a11 + a22 + a13 == 0) (true, 1)
        else (true, 2)
      else if (s == 4)
        if (a12 + a22 == 0 || a11 + a22 == 0 || a12 + a23 == 0
          || a13 + a22 == 0 || a21 + a12 == 0) (true, 2)
        else if (a11 + a21 == 0 || a13 + a23 == 0) (true, 4)
        else (true, 3)
      else {
        if (a12 == 0 || a22 == 0) (true, 3)
        else (true, 4)
      }
    }
    if (right - left == 1 && bottom - top == 2) {
      //2x3
      val a11 = matrix(top)(left).toInt - 48
      val a12 = matrix(top + 1)(left).toInt - 48
      val a13 = matrix(top + 2)(left).toInt - 48
      val a21 = matrix(top)(left + 1).toInt - 48
      val a22 = matrix(top + 1)(left + 1).toInt - 48
      val a23 = matrix(top + 2)(left + 1).toInt - 48
      val s = a11 + a12 + a13 + a21 + a22 + a23
      if (s == 0 || s == 1 || s == 6) (true, s)
      else if (s == 2)
        if (a11 + a12 == 2 || a12 + a13 == 2 || a13 + a23 == 2
          || a21 + a22 == 2 || a22 + a23 == 2 || a11 + a21 == 2) (true, 2)
        else (true, 1)
      else if (s == 3)
        if (a11 + a12 + a13 == 3 || a21 + a22 + a23 == 3)
          (true, 3)
        else if (a21 + a12 + a23 == 0 || a11 + a22 + a13 == 0) (true, 1)
        else (true, 2)
      else if (s == 4)
        if (a12 + a22 == 0 || a11 + a22 == 0 || a12 + a23 == 0
          || a13 + a22 == 0 || a21 + a12 == 0) (true, 2)
        else if (a11 + a21 == 0 || a13 + a23 == 0) (true, 4)
        else (true, 3)
      else {
        if (a12 == 0 || a22 == 0) (true, 3)
        else (true, 4)
      }
    }
    else if (right - left == 3 && bottom == top) {
      //1x4
      val a11 = matrix(top)(left).toInt - 48
      val a12 = matrix(top)(left + 1).toInt - 48
      val a13 = matrix(top)(left + 2).toInt - 48
      val a14 = matrix(top)(left + 3).toInt - 48
      val s = a11 + a12 + a13 + a14
      if (s == 0 || s == 1 || s == 4) (true, s)
      else if (s == 2)
        if (a11 + a12 == 2 || a12 + a13 == 2 || a13 + a14 == 2) (true, 2)
        else (true, 1)
      else if (a11 == 0 || a14 == 0) (true, 3)
      else (true, 2)
    } else if (right == left && bottom - top == 3) {
      //4x1
      val a11 = matrix(top)(left).toInt - 48
      val a12 = matrix(top + 1)(left).toInt - 48
      val a13 = matrix(top + 2)(left).toInt - 48
      val a14 = matrix(top + 3)(left).toInt - 48
      val s = a11 + a12 + a13 + a14
      if (s == 0 || s == 1 || s == 4) (true, s)
      else if (s == 2)
        if (a11 + a12 == 2 || a12 + a13 == 2 || a13 + a14 == 2) (true, 2)
        else (true, 1)
      else if (a11 == 0 || a14 == 0) (true, 3)
      else (true, 2)
    }
    else if (right - left == 1 && bottom - top == 1) {
      //2x2
      val a11 = matrix(top)(left).toInt - 48
      val a12 = matrix(top)(left + 1).toInt - 48
      val a21 = matrix(top + 1)(left).toInt - 48
      val a22 = matrix(top + 1)(left + 1).toInt - 48
      val s = a11 + a12 + a21 + a22
      if (s == 3) (true, 2)
      else if (s == 2) {
        if ((a11 == 0 && a22 == 0) || (a21 == 0 && a12 == 0)) (true, 1)
        else (true, 2)
      } else (true, s)
    } else if (right == left && bottom - top == 2) { //1x3
      val a11 = matrix(top)(left).toInt - 48
      val a21 = matrix(top + 1)(left).toInt - 48
      val a31 = matrix(top + 2)(left).toInt - 48
      val s = a11 + a21 + a31
      if (s == 2 && a21 == 0) (true, 1)
      else (true, s)
    } else if (right - left == 2 && bottom == top) {
      //3x1
      val a11 = matrix(top)(left).toInt - 48
      val a12 = matrix(top)(left + 1).toInt - 48
      val a13 = matrix(top)(left + 2).toInt - 48
      val s = a11 + a12 + a13
      if (s == 2 && a12 == 0) (true, 1)
      else (true, s)
    } else if (right == left && bottom - top == 1) {
      //1x2
      val a11 = matrix(top)(left).toInt - 48
      val a21 = matrix(top + 1)(left).toInt - 48
      (true, a11 + a21)
    } else if (right - left == 1 && bottom == top) { //2x1
      val a11 = matrix(top)(left).toInt - 48
      val a12 = matrix(top)(left + 1).toInt - 48
      (true, a11 + a12)
    } else if (right == left && top == bottom)
      (true, matrix(top)(left).toInt - 48)
    else (false, 0)
  }

  case class Task(matrix: Array[Array[Char]], left: Int, top: Int,
                  right: Int, bottom: Int)

  def maximalRectangle(matrix: Array[Array[Char]]): Int = {
    //    reverseSpiralOrderSubMCond2(matrix, 0, 0, matrix(0).length - 1, matrix.length - 1, {
    //      _ == '0'
    //    })
    if (matrix.length == 0) return 0
    var max = 0
    val queue = Queue[Task]()
    queue += Task(matrix, 0, 0, matrix(0).length - 1, matrix.length - 1)
    while (!queue.isEmpty) {
      val t = queue.dequeue()
      val current = reverseSpiralOrderSubMCond2(t.matrix, t.left, t.top, t.right, t.bottom, {
        _ == '0'
      }, queue)
      if (current > max) max = current
    }
    max
  }

  def main(args: Array[String]): Unit = {
    //    println(
    //      spiralOrderSubM(Array(
    //        Array(1, 2, 3, 4, 5),
    //        Array(6, 7, 8, 9, 10),
    //        Array(11, 12, 13, 14, 15),
    //        Array(16, 17, 18, 19, 20),
    //        Array(21, 22, 23, 24, 25)
    //      ), 0, 0, 3, 1).toList)
    //    println(reverseSpiralOrderSubMCond2(Array(
    //      Array('1', '0', '0', '0', '0'),
    //      Array('0', '1', '1', '1', '0'),
    //      Array('1', '1', '1', '1', '1'),
    //      Array('1', '1', '1', '1', '1')),
    //      0, 0, 4, 3, {
    //        _ == '0'
    //      }
    //    ))
    //    println(reverseSpiralOrderSubMCond2(Array(
    //      Array('0', '1', '1', '1', '1')),
    //      0, 0, 4, 0, {
    //        _ == '0'
    //      }
    //    ))
    //    println(reverseSpiralOrderSubMCond2(Array(
    //      Array('0', '1', '0', '1', '1')),
    //      0, 0, 4, 0, {
    //        _ == '0'
    //      }
    //    ))
    //    println(reverseSpiralOrderSubMCond2(Array(
    //      Array('0', '1', '0', '1', '0')),
    //      0, 0, 4, 0, {
    //        _ == '0'
    //      }
    //    ))
    println(maximalRectangle(Array(
      Array('0', '1', '1', '1'),
      Array('0', '1', '1', '0'))
    ))
    println(maximalRectangle(Array(
      Array('1'),
      Array('0'),
      Array('1'),
      Array('1'))
    ))
    println(maximalRectangle(Array(
      Array('1', '1', '1', '1', '0'),
      Array('1', '1', '0', '1', '1'),
      Array('1', '1', '1', '1', '1'),
      Array('1', '1', '1', '1', '1'))
    ))
    import Array._
    val array = ofDim[Char](200, 200)
    for (i <- 0 to 199) {
      for (j <- 0 to 199)
        array(i)(j) = {
          val c: Integer = (48 + (i + j) % 2)
          print(c.toChar)
          print(' ')
          c.toChar
        }
      println()
    }
    println(maximalRectangle(array))


    //    println(
    //      reverseSpiralOrderSubM(Array(
    //        Array(1, 2, 3, 4, 5, 55, 555),
    //        Array(6, 7, 8, 9, 10, 100, 1000),
    //        Array(11, 12, 13, 14, 15, 155, 1555),
    //        Array(16, 17, 18, 19, 20, 200, 2000),
    //        Array(21, 22, 23, 24, 25, 250, 2500),
    //        Array(31, 32, 33, 34, 35, 350, 3500)
    //      ), 0, 0, 1, 5).toList)
    //    println(
    //      spiralOrderSubM(Array(
    //        Array(1, 2, 3, 4, 5, 55, 555),
    //        Array(6, 7, 8, 9, 10, 100, 1000),
    //        Array(11, 12, 13, 14, 15, 155, 1555),
    //        Array(16, 17, 18, 19, 20, 200, 2000),
    //        Array(21, 22, 23, 24, 25, 250, 2500)
    //      ), 0, 0, 6, 2).toList)
  }
}
