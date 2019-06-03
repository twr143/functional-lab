package algos
/**
  * Created by Ilya Volynin on 03.06.2019 at 14:48.
  */
object BinaryGen {

  def toBinary(i: Int, digits: Int = 8) =
    String.format("%" + digits + "s", i.toBinaryString).replace(' ', '0')

  def main(args: Array[String]): Unit = {
    List.range(0, 16).foreach(n => println(multiplyByBinString("abcd",toBinary(n, 4))))
  }

  def multiplyByBinString(src: String, multBinS: String): String = {
    src.zipWithIndex.map { case (c, i) => {
      if (multBinS.charAt(i) == '1') c else ' '
    }
    }.foldLeft("")(_+_).replaceAll("\\s","")
  }
}
