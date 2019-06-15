package algos.leetCode
import scala.collection.mutable.ListBuffer

/**
  * Created by Ilya Volynin on 15.06.2019 at 17:12.
  */
object Candy {

  def candy(ratings: Array[Int]): Int = {
    val listOfMins = ListBuffer[Int]()
//        val candies = new Array[Int](ratings.length)
    var i = 0
    var j = 0
    if (ratings.length == 0) return 0
    if (ratings.length == 1) return 1
    while (i < ratings.length) {
      if ((i == 0 && ratings(1) >= ratings(0)) ||
        (i > 0 && i < ratings.length - 1 && ratings(i) <= ratings(i - 1) && ratings(i) <= ratings(i + 1)) ||
        (i == ratings.length - 1 && ratings(i) <= ratings(i - 1))
      ) listOfMins += i
      i = i + 1
    }
    var total = listOfMins.size
    var temp = 1
    //left border
    if (listOfMins(0) != 0) {
      j = listOfMins(0)
//            candies(j) = 1
      while (j > 0) {
        temp = temp + 1
        total += temp
//                candies(j - 1) = candies(j) + 1
        j = j - 1
      }
    }
    //right border
    temp = 1
    if (listOfMins.last != ratings.length - 1) {
      j = listOfMins.last

      while (j < ratings.length - 1) {
//        candies(j + 1) = candies(j) + 1
        temp = temp + 1
        total += temp
        j = j + 1
      }
    }
    if (listOfMins.size > 1)
      listOfMins.sliding(2).foreach { case Seq(x, y) =>
//        candies(x) = 1
//        candies(y) = 1
        var temp1 = 1
        var temp2 = 1
        if (y - x > 1) {
          i = x
          j = y
          println(s"x=$x,y=$y")
          while (j > i && (i + 1 != j || ratings(i) != ratings(j))) {
            if (ratings(i + 1) > ratings(i) && j > i) {
//              candies(i + 1) = candies(i) + 1
              temp1 = temp1 + 1
              total += temp1
              i = i + 1
            }
            if (ratings(j - 1) > ratings(j) && j > i) {
//              candies(j - 1) = candies(j) + 1
              temp2 = temp2 + 1
              total += temp2
              j = j - 1
            }
          }
          if (i == j) {
            println(s"i=j,$i")
//            candies(i) = Math.max(candies(i - 1) + 1, candies(i + 1) + 1)
            total-=Math.min(temp1,temp2)
          }
        }
      }
//    println(s"candies:${candies.toList}")
//    candies.sum
      total
  }

  def main(args: Array[String]): Unit = {
    println(candy(Array(1, 0,2)))
  }
}
