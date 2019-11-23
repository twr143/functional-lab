package algos.leetCode.twitter
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by Ilya Volynin on 21.11.2019 at 18:44.
  */
class Twitter() {

  /** Initialize your data structure here. */
  case class Tweet(userId: Int, tweetId: Int, index: Int)

  val tweets = mutable.Map.empty[Int, mutable.TreeSet[Tweet]]

  val followers = mutable.Map.empty[Int, mutable.Set[Int]]

  val orderingtweets = new Ordering[Tweet] {
    def compare(o1: Tweet, o2: Tweet): Int = o2.index - o1.index
  }

  var index: Int = 0

  /** Compose a new tweet. */
  def postTweet(userId: Int, tweetId: Int) {
    if (!tweets.contains(userId)) tweets += userId -> mutable.TreeSet(Tweet(userId, tweetId, index))(orderingtweets)
    else tweets(userId) += Tweet(userId, tweetId, index)
    index += 1
  }

  /** Retrieve the 10 most recent tweet ids in the user's news feed.
    * Each item in the news feed must be posted by users who the user
    * followed or by the user herself.
    * Tweets must be ordered from most recent to least recent. */
  def getNewsFeed(userId: Int): List[Int] = {
    val arr = new ArrayBuffer[mutable.TreeSet[Tweet]]()
    if (followers.contains(userId)) followers(userId).foreach(f => {
      if (tweets.contains(f)) arr += tweets(f)
    })
    if (tweets.contains(userId)) arr += tweets(userId)
    mergeKSortedSetsTakeN(arr, 10)(orderingtweets).toList.map(_.tweetId)
  }

  def mergeKSortedSetsTakeN(sets: ArrayBuffer[mutable.TreeSet[Tweet]], limit: Int)
                           (implicit o: Ordering[Tweet]): mutable.TreeSet[Tweet] = {
    var resultSet = new mutable.TreeSet[Tweet]()
    val iters = new Array[Iterator[Tweet]](sets.size)
    for (i <- 0 to sets.size - 1)
      iters(i) = sets(i).iterator
    var current = mutable.Set.empty[(Int, Tweet)] //(index,value)
    for (i <- 0 to sets.size - 1)
      if (iters(i).hasNext)
        current += ((i, iters(i).next()))
    while (resultSet.size < limit && current.nonEmpty) {
      val headPair = findMax2(current)
      resultSet += headPair._2
      current.remove(headPair)
      if (iters(headPair._1).hasNext)
        current += ((headPair._1, iters(headPair._1).next()))
    }
    resultSet
  }

  def findMax2(set: mutable.Set[(Int, Tweet)]): (Int, Tweet) = {
    val orderingPairsByVals = new Ordering[(Int, Tweet)] {
      def compare(o1: (Int, Tweet), o2: (Int, Tweet)): Int = o1._2.index - o2._2.index
    }
    set.max(orderingPairsByVals)
  }

  /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
  def follow(followerId: Int, followeeId: Int): Unit = {
    if (!followers.contains(followerId)) followers += followerId -> mutable.Set(followeeId)
    else followers(followerId) += followeeId
  }

  /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
  def unfollow(followerId: Int, followeeId: Int) {
    if (followers.contains(followerId)) followers(followerId) -= followeeId
  }
}
