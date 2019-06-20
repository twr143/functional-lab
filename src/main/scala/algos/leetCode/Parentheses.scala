package algos.leetCode
import scala.collection.mutable.ArrayBuffer

/**
  * Created by Ilya Volynin on 16.06.2019 at 16:59.
  */
object Parentheses {
  def bGen(notCoupled: Int, unused: Int, current: String, f: String => Unit): Unit = {
    if (notCoupled == 0 && unused > 0) bGen(1, unused - 1, current + "(", f)
    else if (notCoupled > 0 && unused > 0) {
      bGen(notCoupled + 1, unused - 1, current + "(", f)
      bGen(notCoupled - 1, unused, current + ")", f)
    } else if (notCoupled > 0 && unused == 0)
      bGen(notCoupled - 1, 0, current + ")", f)
    else f(current)
  }

  def generateParenthesis(n: Int): List[String] = {
    val list = ArrayBuffer[String]()
    val f: String => Unit = x => {
      list+=x
    }
    bGen(0, n, "", f)
    list.toList
  }

  def main(args: Array[String]): Unit = {
    println(generateParenthesis(5))
  }

}
