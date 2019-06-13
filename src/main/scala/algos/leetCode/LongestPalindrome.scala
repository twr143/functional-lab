package algos.leetCode
/**
  * Created by Ilya Volynin on 04.06.2019 at 11:14.
  */
object LongestPalindrome {

  def longestPalindrome(s: String): String = {
    if (s.isEmpty) return ""
    val period = checkPeriodicity(s,0)
    if (period._1) return period._2
    var index = 0;
    var result = s.charAt(0) + ""
    while (index < s.length - 1) {
      if (s.charAt(index) == s.charAt(index + 1)) {
        var current = "" + s.charAt(index) + s.charAt(index + 1)
        var j = 1
        while (index - j >= 0 && index + 1 + j < s.length
          && s.charAt(index - j) == s.charAt(index + 1 + j)) {
          current = s.charAt(index - j) + current + s.charAt(index - j)
          j += 1
        }
        if (current.length > result.length)
          result = current
      }
      index += 1
    }
    index = 0
    while (index < s.length - 2) {
      if (s.charAt(index) == s.charAt(index + 2)) {
        var current = "" + s.charAt(index) + s.charAt(index + 1) + s.charAt(index + 2)
        var j = 1
        while (index - j >= 0 && index + 2 + j < s.length
          && s.charAt(index - j) == s.charAt(index + 2 + j)) {
          current = s.charAt(index - j) + current + s.charAt(index - j)
          j += 1
        }
        if (current.length > result.length)
          result = current
      }
      index += 1
    }
    result
  }

  def checkPeriodicity(s: String, period: Int): (Boolean,String) = {
   if (s.matches(".+")) (true,s)
   else if (s.matches("(..)+")) (true,s.substring(s.length/2))
   else (false,"")
  }

  def main(args: Array[String]): Unit = {
    val s1 = "abccba"
    val s2 = "abccda"
    val s3 = "abcdcba"
    val s4 = "abcdecba"
    val s5 = "aaaaaaaa"
    val s6 = "ababababab"
    println(longestPalindrome(s1))
    println(longestPalindrome(s2))
    println(longestPalindrome(s3))
    println(longestPalindrome(s4))
    println(longestPalindrome(s5))
    println(longestPalindrome(s6))
  }
}
