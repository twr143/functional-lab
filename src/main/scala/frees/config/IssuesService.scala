package frees.config
import freestyle.free.free

/**
  * Created by Ilya Volynin on 10.08.2019 at 17:06.
  */
@free trait IssuesService {
  def states: FS[Set[String]]
}