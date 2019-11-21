package algos.leetCode.trees
/**
  * Created by Ilya Volynin on 21.11.2019 at 14:30.
  */
object BTDiameter {

  case class TreeNode(var _value: Int,
                      var left: TreeNode = null,
                      var right: TreeNode = null
                     )

  def diameterOfBinaryTree(root: TreeNode): Int = {
    if ((root == null) || (root.left == null && root.right == null)) return 0
    root._value=0
    diameterOfBinaryTree(root, null, rootCopy = root)
    root._value
  }

  def diameterOfBinaryTree(root: TreeNode, parent: TreeNode, rootCopy: TreeNode): Int = {
    if (root.left == null && root.right == null) {
      return 0
    }
    val lmax = if (root.left != null)
      diameterOfBinaryTree(root.left, root, rootCopy) else 0
    val rmax = if (root.right != null)
      diameterOfBinaryTree(root.right, root, rootCopy) else 0
    if (root.left != null && root.right != null) {
      rootCopy._value = Math.max(rootCopy._value, lmax + rmax + 2)
//            println(s"p len in +2 = ${rootCopy._value}, lmax= $lmax, rmax = $rmax, val = ${root._value}")
    }
    else if (parent == null) {
      rootCopy._value = Math.max(rootCopy._value, lmax + rmax + 1)
//            println(s"p len in +1 = ${rootCopy._value}")
    }
    Math.max(lmax, rmax) + 1
  }

  def main(args: Array[String]): Unit = {
    val ll = TreeNode(5)
    val lr = TreeNode(4)
    val l = TreeNode(2, ll, lr)
    val r = TreeNode(3)
    val root = TreeNode(1, l, r)
    println(diameterOfBinaryTree(root))
  }
}
