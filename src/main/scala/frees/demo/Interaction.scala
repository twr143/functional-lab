package frees.demo

import freestyle.free._
// import freestyle.free._


import freestyle.free.implicits._

trait Interaction[FF$9369[_]] extends _root_.freestyle.free.internal.EffectLike[FF$9369] {

  def tell(msg: String): FS[Unit]

  def ask(prompt: String): FS[String]
}

@_root_.java.lang.SuppressWarnings(_root_.scala.Array("org.wartremover.warts.Any", "org.wartremover.warts.AsInstanceOf", "org.wartremover.warts.Throw")) object Interaction {

  sealed trait Op[_] extends _root_.scala.Product with _root_.java.io.Serializable {

    val FSAlgebraIndex9380: _root_.scala.Int
  }

  final case class TellOp(val msg: String) extends _root_.scala.AnyRef with Op[Unit] {

    override val FSAlgebraIndex9380: _root_.scala.Int = 0
  }

  final case class AskOp(val prompt: String) extends _root_.scala.AnyRef with Op[String] {

    override val FSAlgebraIndex9380: _root_.scala.Int = 1
  }

  type OpTypes = _root_.iota.TConsK[Op, _root_.iota.TNilK]

  trait Handler[MM$9384[_]] extends _root_.freestyle.free.FSHandler[Op, MM$9384] {

    protected[this] def tell(msg: String): MM$9384[Unit]

    protected[this] def ask(prompt: String): MM$9384[String]

    override def apply[AA$9385](fa$9386: Op[AA$9385]): MM$9384[AA$9385] = ((fa$9386.FSAlgebraIndex9380: @_root_.scala.annotation.switch) match {
      case 0 =>
        val fresh9387: TellOp = fa$9386.asInstanceOf[TellOp]
        tell(fresh9387.msg)
      case 1 =>
        val fresh9388: AskOp = fa$9386.asInstanceOf[AskOp]
        ask(fresh9388.prompt)
      case i =>
        throw new _root_.java.lang.Exception("freestyle internal error: index " + i.toString() + " out of bounds for " + this.toString())
    }).asInstanceOf[MM$9384[AA$9385]]
  }

  class To[LL$9381[_]](implicit ii$9382: _root_.freestyle.free.InjK[Op, LL$9381]) extends Interaction[LL$9381] {

    private[this] val toInj9383 = _root_.freestyle.free.FreeS.inject[Op, LL$9381](ii$9382)

    override def tell(msg: String): FS[Unit] = toInj9383(TellOp(msg))

    override def ask(prompt: String): FS[String] = toInj9383(AskOp(prompt))
  }

  implicit def to[LL$9381[_]](implicit ii$9382: _root_.freestyle.free.InjK[Op, LL$9381]): To[LL$9381] = new To[LL$9381]

  def apply[FF$9369[_]](implicit ev$9389: Interaction[FF$9369]): Interaction[FF$9369] = ev$9389

  def instance(implicit ev: Interaction[Op]): Interaction[Op] = ev
}