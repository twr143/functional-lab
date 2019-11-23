package algos.leetCode.twitter

/**
  * Created by Ilya Volynin on 21.11.2019 at 18:40.
  */
object BasicOps {

  def main(args: Array[String]): Unit = {
    val tw = new Twitter()
//    tw.postTweet(1,1)
//    println(tw.getNewsFeed(1))
//    tw.follow(1,2)
//    tw.postTweet(2,6)
//    println(tw.getNewsFeed(1))

    tw.postTweet(1,5)
    tw.postTweet(1,3)
//    tw.postTweet(1,7)
//    tw.postTweet(1,9)
//    tw.postTweet(1,11)
//    tw.postTweet(2,2)
//    tw.follow(2,1)
//    tw.postTweet(2,4)
//    tw.postTweet(2,6)
//    tw.postTweet(2,8)
//    tw.postTweet(2,10)
//    tw.postTweet(2,12)
//    tw.follow(2,1)
//    println(tw.tweets)
    println(tw.getNewsFeed(1))
//    tw.unfollow(2,1)
    println(tw.getNewsFeed(1))
  }
}
