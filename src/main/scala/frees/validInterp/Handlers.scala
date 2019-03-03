package frees.validInterp

/**
  * Created by Ilya Volynin on 03.03.2019 at 15:25.
  */
object Handlers {
  import scala.util.Try
  // import scala.util.Try

  implicit val validationHandler = new Validation.Handler[Try] {
    override def minSize(s: String, n: Int): Try[Boolean] = Try(s.size >= n)
    override def hasNumber(s: String): Try[Boolean] = Try(s.exists(c => "0123456789".contains(c)))
  }
  // validationHandler: Validation.Handler[scala.util.Try] = $anon$1@33a88219

  implicit val interactionHandler = new Interaction.Handler[Try] {
    override def tell(s: String): Try[Unit] = Try(println(s))
    override def ask(s: String): Try[String] = Try("This could have been user input 1")
  }
}
