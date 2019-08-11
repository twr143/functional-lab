package frees.config
/**
  * Created by Ilya Volynin on 10.08.2019 at 17:02.
  */
import freestyle.free.config.ConfigM
import freestyle.free.module
import freestyle.free._
import freestyle.free.implicits._
import scala.util.{Success, Try}

object Entry {

  case class AppConfig(disallowedStates: List[String])
  import classy.config._

  implicit val configDecoder: ConfigDecoder[AppConfig] =
    readConfig[List[String]]("disallowedStates").map(AppConfig.apply)

  def filteredStates[F[_]](implicit app: App[F]): FreeS[F, Set[String]] =
    for {
      currentStates <- app.issuesService.states
      config <- app.config.loadAs[AppConfig]
    } yield currentStates.diff(config.disallowedStates.foldLeft(Set.empty[String])(_ + _))

  def main(args: Array[String]): Unit = {
    import cats.implicits._
    import freestyle.free._
    import frees.config.Handlers._
    import freestyle.free.config._
    import freestyle.free.config.implicits._
    filteredStates[App.Op].interpret[Try].foreach(println)
  }
}
