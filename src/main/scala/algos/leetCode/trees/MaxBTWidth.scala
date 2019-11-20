package algos.leetCode.trees
import scala.collection.mutable.ArrayBuffer

/**
  * Created by Ilya Volynin on 20.11.2019 at 16:33.
  */
object MaxBTWidth {

  case class TreeNode(var _value: Int,
                      var left: TreeNode = null,
                      var right: TreeNode = null
                     )

  def widthOfBinaryTree(root: TreeNode): Int = {
    val ranges = new ArrayBuffer[(Int, Int)]
    search(root, 1, 0, ranges) + 1
  }

  def search(root: TreeNode, horIndex: Int, vertIndex: Int,
             horIndexRanges: ArrayBuffer[(Int, Int)]): Int = {
    var max = 0
    if (horIndexRanges.length < vertIndex + 1)
      horIndexRanges.append((horIndex, horIndex))
    else {
      if (horIndexRanges(vertIndex)._1 > horIndex) {
        horIndexRanges(vertIndex) = horIndexRanges(vertIndex).copy(_1 = horIndex)
        max = Math.max(max, horIndexRanges(vertIndex)._2 - horIndexRanges(vertIndex)._1)
      }
      else if (horIndexRanges(vertIndex)._2 < horIndex) {
        horIndexRanges(vertIndex) = horIndexRanges(vertIndex).copy(_2 = horIndex)
        max = Math.max(max, horIndexRanges(vertIndex)._2 - horIndexRanges(vertIndex)._1)
      }
    }
    val lmax = if (root.left != null)
      search(root.left, horIndex * 2 - 1, vertIndex + 1, horIndexRanges) else 0
    val rmax = if (root.right != null)
      search(root.right, horIndex * 2, vertIndex + 1, horIndexRanges) else 0
    Math.max(max, Math.max(lmax, rmax))
  }

  def main(args: Array[String]): Unit = {
    val l3lll = TreeNode(21)
    val l3rrr = TreeNode(28)
    val l2ll = TreeNode(11, l3lll)
    val l2lr = TreeNode(13)
    val l2rl = TreeNode(15)
    val l2rr = TreeNode(19, l3rrr, null)
    val l1l = TreeNode(1, l2ll, l2lr)
    val l1r = TreeNode(5, l2rl, l2rr)
    val root = TreeNode(0, l1l, l1r)
    println(widthOfBinaryTree(root))
  }
}
