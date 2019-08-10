package frees.config

/**
  * Created by Ilya Volynin on 10.08.2019 at 17:14.
  */
import freestyle.free.module
import freestyle.free.config._

import freestyle.free.config.implicits._

@module trait App {
  val issuesService: IssuesService
  val config: ConfigM
}





