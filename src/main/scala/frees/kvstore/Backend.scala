package frees.kvstore
import freestyle.free.module

/**
  * Created by Ilya Volynin on 03.03.2019 at 13:16.
  */
@module trait Backend {
  val store: KVStore
  val log: Log
}