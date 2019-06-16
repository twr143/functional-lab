package algos.leetCode
/**
  * Created by Ilya Volynin on 16.06.2019 at 12:42.
  */
object Jump {

  def jump(nums: Array[Int]): Int = {
    var i = 0
    var jumps = 0
    while (i < nums.length - 1) {
      var maxVal = nums(i + 1)
      if (nums(i) < nums.length - 1 - i) {
        var maxIndex = i + 1
        for (j <- 2 to nums(i))
          if (nums(i + j) + j - 1 > maxVal) {
            maxVal = nums(i + j) + j - 1
            maxIndex = i + j
          }
        i = maxIndex
      } else {
        i = nums.length - 1
      }
      jumps += 1
    }
    jumps
  }

  def main(args: Array[String]): Unit = {
    println(jump(Array(2, 1, 2, 1, 1, 2)))
    println(jump(Array(12, 1, 2, 1, 1, 2)))
    println(jump(Array(2, 2, 2, 1, 1, 2)))
    println(jump(Array(4, 1, 1, 3, 1, 1, 1, 1)))
  }
}
