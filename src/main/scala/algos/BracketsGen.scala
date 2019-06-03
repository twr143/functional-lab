package algos
/**
  * Created by Ilya Volynin on 15.05.2019 at 23:09.
  */
object BracketsGen {

  def bGen(notCoupled: Int, unused: Int, current: String, f: String => Unit): Unit = {
    if (notCoupled == 0 && unused > 0) bGen(1, unused - 1, current + "(", f)
    else if (notCoupled > 0 && unused > 0) {
      bGen(notCoupled + 1, unused - 1, current + "(", f)
      bGen(notCoupled - 1, unused, current + ")", f)
    } else if (notCoupled > 0 && unused == 0)
      bGen(notCoupled - 1, 0, current + ")", f)
    else f(current)
  }

  def main(args: Array[String]): Unit = {
    var counter: Int = 1
    val f: String => Unit = x => {
      println(s"$counter:$x"); counter += 1
    }
    bGen(0, 3, "", f)
    println()
    //    bGen(0,4,"",f)
  }
}
