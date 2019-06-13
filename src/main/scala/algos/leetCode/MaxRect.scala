package algos.leetCode
import scala.collection.immutable.Stack

/**
  * Created by Ilya Volynin on 13.06.2019 at 15:44.
  */
object MaxRect {

  case class MaxRectInfo(sq: Int, right: Int, width: Int, height: Int)

  def largestRectangleArea(height: Array[Int], description: String): MaxRectInfo = {
    if (height == null || height.length == 0) return null
    val startTime = System.currentTimeMillis();


    var stack = Stack[Integer]()
    var max = 0;
    var i: Integer = 0;
    var mrinfo: MaxRectInfo = MaxRectInfo(height(0), 0, 1, height(0))
    while (i < height.length) {
      //push index to stack when the current height is larger than the previous one
      if (stack.isEmpty || height(i) >= height(stack.top)) {
        stack = stack.push(i)
        i += 1
      }
      else {
        //calculate max value when the current height is less than the previous one
        val p = stack.pop2
        stack = p._2
        val h: Int = height(p._1)
        val w: Int = if (stack.isEmpty) i else i - stack.top - 1
        if (Math.max(h * w, max) > max) {
          max = Math.max(h * w, max);
          mrinfo = MaxRectInfo(max, i - 1, w, h)
        }
      }
    }
    while (!stack.isEmpty) {
      val p = stack.pop2
      stack = p._2
      val h: Int = height(p._1);
      val w: Int = if (stack.isEmpty) i else i - stack.top - 1
      if (Math.max(h * w, max) > max) {
        max = Math.max(h * w, max);
        mrinfo = MaxRectInfo(max, i - 1, w, h)
      }
    }
    val endTime = System.currentTimeMillis();
    System.out.println(description + " time ms:" + (endTime - startTime));

    mrinfo
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(2, 1, 5, 6, 2, 3)
    println(largestRectangleArea(arr, "stack version"))
  }
}