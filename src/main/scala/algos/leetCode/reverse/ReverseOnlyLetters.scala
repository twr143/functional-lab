package algos.leetCode.reverse
/**
  * Created by Ilya Volynin on 09.11.2019 at 11:40.
  */
object ReverseOnlyLetters {

  def main(args: Array[String]): Unit = {
    val s = "Test1ng-Leet=code-Q!"
    println(reverseOnlyL(s))
  }

  def reverseOnlyL(s: String): String = {
    var lettersInd = s.length
    val sCopy = new String(s)
    s.map(c => if (isLetter(c)) {
      lettersInd -= 1
      while (!isLetter(sCopy(lettersInd)))
        lettersInd -= 1
      sCopy(lettersInd)
    } else c)
  }

  def isLetter: Char => Boolean = {
    x => if ((x.toInt >= 97 && x.toInt <= 122) || (x.toInt >= 65 && x.toInt <= 90)) true else false
  }
}
