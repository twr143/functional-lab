package algos.leetCode.coinChange
/**
  * Created by Ilya Volynin on 17.11.2019 at 11:56.
  */
object E1 {

  def coinChange(coins: Array[Int], amount: Int): Int = {
    val ar = coins.sorted
    find(ar, amount, ar.length - 1)
  }

  def find(coins: Array[Int], rest: Int, curIndex: Int): Int = {
    if (curIndex == 0) {
      (if (rest % coins(0) == 0) rest / coins(0) else -1)
    } else {
      if (rest % coins(curIndex) == 0) rest / coins(curIndex)
      else {
        val maxCoins = rest / coins(curIndex)
        var minimal = -1
        for (curCoins <- 0 to maxCoins) {
          val smallerCoinsAmt = find(coins, rest - curCoins * coins(curIndex), curIndex - 1)
          if (minimal == -1 && smallerCoinsAmt > -1) minimal = smallerCoinsAmt + curCoins
          else if (minimal > -1 && smallerCoinsAmt > -1) {
            minimal = Math.min(minimal, smallerCoinsAmt + curCoins)
          }
        }
        minimal
      }
    }
  }

  def main(args: Array[String]): Unit = {
    println(coinChange(Array(2, 3, 5, 9
    ), 17))
  }
}
