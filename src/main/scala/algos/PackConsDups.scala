package algos
import scala.collection.mutable.ListBuffer

/**
  * Created by Ilya Volynin on 04.05.2019 at 20:21.
  */
object PackConsDups {

  def pack : ListBuffer[Symbol] => ListBuffer[ListBuffer[Symbol]] = {
      l => l.foldLeft(ListBuffer[ListBuffer[Symbol]]())((total, current) =>{
        if (total.isEmpty) total += ListBuffer(current)
        else if (total.last.last == current) {total.last += current}
        else total += ListBuffer(current)
        total
      })
  }

  def main(args: Array[String]): Unit = {
     println(pack(ListBuffer('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
  }
}
