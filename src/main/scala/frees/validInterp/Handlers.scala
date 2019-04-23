package frees.validInterp
import scala.util.Success

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

  implicit val interactionHandler = new Interaction.Handler[Try] {
    override def tell(s: String): Try[Unit] = Try(println(s))

    override def ask(s: String): Try[String] = {
      println("please enter some input")
      Success(scala.io.StdIn.readLine())
    }
  }

  implicit val validationHandlerOpt = new Validation.Handler[Option] {
    override def minSize(s: String, n: Int): Option[Boolean] = if (s.size >= n) Some(true) else Some(false)

    override def hasNumber(s: String): Option[Boolean] = if (s.matches(".*\\d.*")) Some(true) else Some(false)
  }

  implicit val interactionHandlerOpt = new Interaction.Handler[Option] {
    override def tell(s: String): Option[Unit] = Some(println(s))

    override def ask(s: String): Option[String] = {
      println("please enter some input")
      Some(scala.io.StdIn.readLine())
    }
  }

  type Target[A] = Either[Exception, A]

  implicit val validationHandlerEither = new Validation.Handler[Target] {
    override def minSize(s: String, n: Int): Target[Boolean] = if (s.size >= n) Right(true)
    else Left(throw new Exception(s"the size ${s.size} is less than $n"))

    override def hasNumber(s: String): Target[Boolean] = if (s.matches(".*\\d.*")) Right(true)
    else Left(throw new Exception(s" $s doesn't contain numbers"))
  }

  implicit val interactionHandlerEither = new Interaction.Handler[Target] {
    override def tell(s: String): Target[Unit] = Right(println(s))

    override def ask(s: String): Target[String] = {
      println("please enter some input")
      Right(scala.io.StdIn.readLine())
    }
  }
}
