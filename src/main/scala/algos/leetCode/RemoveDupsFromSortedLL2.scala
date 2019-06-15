package algos.leetCode
/**
  * Created by Ilya Volynin on 14.06.2019 at 12:45.
  */
object RemoveDupsFromSortedLL2 {

  case class ListNode(val _x: Int = 0) {

    var next: ListNode = null

    val x: Int = _x
  }

  def deleteDuplicates(head: ListNode): ListNode = {
    if (head == null || head.next == null) return head
    var headResult: ListNode = null
    var lastResult: ListNode = null
    var first: ListNode = head
    var last: ListNode = head.next
    while (last != null || first != null) {
      while (last != null && first.x == last.x) last = last.next
      if (first.next == last || first.next == null) {
        if (headResult == null) {
          headResult = ListNode(first.x)
          lastResult = headResult
        } else {
          lastResult.next = ListNode(first.x)
          lastResult = lastResult.next
        }
      }
      first = last
      last = if (last != null) last.next else null
    }
    headResult
  }

  def printList(head: ListNode) = {
    var h = head
    while (h != null) {
      print(h.x)
      h = h.next
    }
    println()
  }

  def main(args: Array[String]): Unit = {
    val l0 = ListNode(0)
    val l01 = ListNode(0)
    val l1 = ListNode(1)
    val l2 = ListNode(1)
    val l3 = ListNode(2)
    val l4 = ListNode(2)
    val l5 = ListNode(2)
    val l6 = ListNode(3)
    l01.next = l0
    l0.next = l1
    l1.next = l2
    l2.next = l3
    l3.next = l4
    l4.next = l5
    l5.next = l6
    printList(deleteDuplicates(l01))
  }
}
