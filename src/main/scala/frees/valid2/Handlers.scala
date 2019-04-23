package frees.valid2
import scala.util.{Success, Try}

/**
  * Created by Ilya Volynin on 23.04.2019 at 20:23.
  */
object Handlers {

  implicit val interactionHandler = new Interaction.Handler[Try] {
    override def tell(s: String): Try[Unit] = Try(println(s))

    override def ask(s: String): Try[String] = {
      println("please enter some input")
      Success(scala.io.StdIn.readLine())
    }
  }

  implicit val validationHandler = new Validation.Handler[Try] {
    override def rules: Try[List[String => String]] = Success(
      List(
        s => if (s.isEmpty) s"$s shouldn't be empty" else "",
        s => if (!s.exists(c => "0123456789".contains(c))) s"$s should contain a digit" else "",
        s => if (s.size < 3) s"$s should contain atleast 3 chars" else ""
      )
    )
  }
}
