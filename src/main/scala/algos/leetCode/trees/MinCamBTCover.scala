package algos.leetCode.trees
/**
  * Created by Ilya Volynin on 20.11.2019 at 18:44.
  */
object MinCamBTCover {

  case class TreeNode(var _value: Int,
                      var left: TreeNode = null,
                      var right: TreeNode = null
                     )

  def minCameraCover(root: TreeNode): Int = {
    minCameraCover(root, null)
  }

  def minCameraCover(root: TreeNode, parent: TreeNode): Int = {
    if (root.left == null && root.right == null) {
      if (parent != null && (parent._value == 0 || parent._value == 1)) {
        parent._value = 1
        if (root._value == 0)
          root._value = -1
        return 0
      }
      if (parent != null && (parent._value == -1)) {
        if (root._value == 0)
          root._value = 1
        return 1
      }
      if (parent == null) {
        root._value = 1
        return 1
      }
    }
    // -1 should be lightened
    // -2 already lightened
    val leftCam = if (root.left != null) minCameraCover(root.left, root) else 0
    val rightCam = if (root.right != null) minCameraCover(root.right, root) else 0
    if ((root.left != null && root.left._value == -1) ||
      (root.right != null && root.right._value == -1)) {
      root._value = 1
      return leftCam + rightCam + 1
    }
    else if ((root.left != null && root.left._value == 1) ||
      (root.right != null && root.right._value == 1)) {
      root._value = -2
      return leftCam + rightCam
    }
      //in case of the root
    else if ((parent == null) && ((root.left != null && root.left._value == -2) ||
      (root.right != null && root.right._value == -2))) {
      root._value = 1
      return leftCam + rightCam + 1
    }
    else {
      root._value = -1
      return leftCam + rightCam
    }
  }

  def main(args: Array[String]): Unit = {
  }
}
