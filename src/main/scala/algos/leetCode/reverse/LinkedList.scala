package algos.leetCode.reverse
/**
  * Created by Ilya Volynin on 09.11.2019 at 16:30.
  */
object LinkedList {

  case class ListNode(x: Int,
                      var next: ListNode = null
                     )

  def add(head: ListNode, ln: ListNode): Unit = {
    head.next match {
      case nn if nn != null => add(nn, ln)
      case _ => head.next = ln
    }
  }

  def print(head: ListNode): String = head.next match {
    case nn if nn != null => s"${head.x} -> " + print(nn)
    case _ => s"${head.x}"
  }

  def reverseKGroup(head: ListNode, k: Int): ListNode = {
    if (head == null) return null
    if (k == 1) return head
    var newHead = head
    var indexHead = head
    var indexTail = head
    var index = 1
    var h = head
    var hnext = h.next
    while (indexHead != null) {
      if (index % k == 0) {
        if (index > k) {
          indexTail.next = indexHead
//          println(s"indexTail.next=${indexTail.next.x}")
          indexTail = h
//          println(s"indexTail=${indexTail.x}")
        }
        while (h != indexHead) {
          val loch = h
          h = hnext
          hnext = h.next
          h.next = loch
        }
        if (newHead == head) {
          newHead = h
        }
        indexHead = hnext
        if (hnext != null) {
          h = hnext
          hnext = h.next
        }
      } else if (index % k == 1 && index > k) {
//        println(s"indexTail because k +1 =${indexTail.x}")
        indexTail.next = indexHead
        indexHead = indexHead.next
      } else
        indexHead = indexHead.next
      index += 1
      //      println(s"index=$index, indexHead = ${if (indexHead != null) indexHead.x else "[null]"}")
    }
    if (index % k == 1)
      indexTail.next = null
    newHead
  }

  def main(args: Array[String]): Unit = {
    val head = ListNode(1)
    add(head, ListNode(2))
    add(head, ListNode(3))
        add(head, ListNode(4))
        add(head, ListNode(5))
        add(head, ListNode(6))
        add(head, ListNode(7))
        add(head, ListNode(8))
        add(head, ListNode(9))
        add(head, ListNode(10))
        add(head, ListNode(11))
    println(print(head))
    println(print(reverseKGroup(head, 3)))
  }
}
