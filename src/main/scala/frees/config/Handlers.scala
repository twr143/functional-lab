package frees.config
import scala.util.{Success, Try}
import freestyle.free.implicits._


/**
  * Created by Ilya Volynin on 10.08.2019 at 17:32.
  */
object Handlers {
  implicit val issuesServiceHandler: IssuesService.Handler[Try] = new IssuesService.Handler[Try] {
    def states: Try[List[String]] = Success(List("open", "reverted", "in progress", "closed"))
  }

}
