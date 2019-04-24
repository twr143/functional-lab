package parser
/**
  * Created by Ilya Volynin on 24.04.2019 at 19:46.
  */
object E1 {

  def eval(code: String,
           variables: Set[Map[String, Double]] = Set.empty,
           functions: String => Double => Double = Map.empty) = {
    FormulaParser(code).fold(
      error => println(s"\'$code\' parsing error: $error"),
      expr =>
        variables.map { v =>
          val d = Evaluator(expr, v, functions)
          println(s"\'$code\'($v) = $d")
        })
    println()
  }

  def main(args: Array[String]): Unit = {
    eval("(x+1)*x*x - 5*x + 6", Set(Map("x" -> 1D), Map("x" -> 2D), Map("x" -> 3D)))
    eval("x*y - 5*x + 6*y", Set(Map("x" -> 1D, "y" -> 0D), Map("x" -> 2D, "y" -> 1D)))
  }
}
