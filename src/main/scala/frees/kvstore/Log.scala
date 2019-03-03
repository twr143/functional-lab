package frees.kvstore
import freestyle.free.free
import freestyle.free.implicits._

/**
  * Created by Ilya Volynin on 03.03.2019 at 13:13.
  */
@free trait Log {
  def info(msg: String): FS[Unit]
  def warn(msg: String): FS[Unit]
}