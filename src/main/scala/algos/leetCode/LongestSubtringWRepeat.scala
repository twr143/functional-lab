package algos.leetCode
import scala.collection.immutable.{HashMap, HashSet}

/**
  * Created by Ilya Volynin on 03.06.2019 at 16:55.
  */
object LongestSubtringWRepeat {

  def lengthOfLongestSubstring(src: String): String = {
    var s = src
    var currentLongest = ""
    var repeatCharIndex = 0
    var i = 0;
    var j = 0
    while (i < src.length) {
      var counters = HashMap.empty[Char, Int]
      j = i
      var bContinue = true
      while (j < src.length && bContinue) {
        if (counters.contains(src.charAt(j))) {
          //          println(s"counters.contains ${src.charAt(j)}")
          if (currentLongest.length < j - i)
            currentLongest = src.substring(i, j)
          repeatCharIndex = counters(src.charAt(j))
          //          println(s"repeatCharIndex ${repeatCharIndex}")
          //          println(s"currentLongest ${currentLongest}")
          bContinue = false
        } else if (j == src.length - 1) {
          repeatCharIndex = s.length - 1
          if (currentLongest.length < j - i + 1)
            currentLongest = src.substring(i, j + 1)
          bContinue = false
        }
        else {
          counters += (src.charAt(j) -> j)
          if (j < src.length - 1) {
            j = j + 1
            bContinue = true
          }
          else
            bContinue = false
        }
      }
      i = repeatCharIndex + 1
      //      println(s"i= ${i}, j= $j")
      //      println(s"current s: $s")
    }
    currentLongest
  }

  def main(args: Array[String]): Unit = {
    var s = "abcadceh"
    var s2 = "bbbbb"
    var s3 = "abcabcbb"
    println(lengthOfLongestSubstring(s))
    println(lengthOfLongestSubstring(s2))
    println(lengthOfLongestSubstring(s3))
  }
}
