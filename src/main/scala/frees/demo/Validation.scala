package frees.demo

import freestyle.free._
// import freestyle.free._
trait Validation[FF$9390[_]] extends _root_.freestyle.free.internal.EffectLike[FF$9390] {

  def minSize(s: String, n: Int): FS[Boolean]

  def hasNumber(s: String): FS[Boolean]
}

@_root_.java.lang.SuppressWarnings(_root_.scala.Array("org.wartremover.warts.Any", "org.wartremover.warts.AsInstanceOf", "org.wartremover.warts.Throw")) object Validation {

  sealed trait Op[_] extends _root_.scala.Product with _root_.java.io.Serializable {

    val FSAlgebraIndex9405: _root_.scala.Int
  }

  final case class MinSizeOp(val s: String, val n: Int) extends _root_.scala.AnyRef with Op[Boolean] {

    override val FSAlgebraIndex9405: _root_.scala.Int = 0
  }

  final case class HasNumberOp(val s: String) extends _root_.scala.AnyRef with Op[Boolean] {

    override val FSAlgebraIndex9405: _root_.scala.Int = 1
  }

  type OpTypes = _root_.iota.TConsK[Op, _root_.iota.TNilK]

  trait Handler[MM$9409[_]] extends _root_.freestyle.free.FSHandler[Op, MM$9409] {

    protected[this] def minSize(s: String, n: Int): MM$9409[Boolean]

    protected[this] def hasNumber(s: String): MM$9409[Boolean]

    override def apply[AA$9410](fa$9411: Op[AA$9410]): MM$9409[AA$9410] = ((fa$9411.FSAlgebraIndex9405: @_root_.scala.annotation.switch) match {
      case 0 =>
        val fresh9412: MinSizeOp = fa$9411.asInstanceOf[MinSizeOp]
        minSize(fresh9412.s, fresh9412.n)
      case 1 =>
        val fresh9413: HasNumberOp = fa$9411.asInstanceOf[HasNumberOp]
        hasNumber(fresh9413.s)
      case i =>
        throw new _root_.java.lang.Exception("freestyle internal error: index " + i.toString() + " out of bounds for " + this.toString())
    }).asInstanceOf[MM$9409[AA$9410]]
  }

  class To[LL$9406[_]](implicit ii$9407: _root_.freestyle.free.InjK[Op, LL$9406]) extends Validation[LL$9406] {

    private[this] val toInj9408 = _root_.freestyle.free.FreeS.inject[Op, LL$9406](ii$9407)

    override def minSize(s: String, n: Int): FS[Boolean] = toInj9408(MinSizeOp(s, n))

    override def hasNumber(s: String): FS[Boolean] = toInj9408(HasNumberOp(s))
  }

  implicit def to[LL$9406[_]](implicit ii$9407: _root_.freestyle.free.InjK[Op, LL$9406]): To[LL$9406] = new To[LL$9406]

  def apply[FF$9390[_]](implicit ev$9414: Validation[FF$9390]): Validation[FF$9390] = ev$9414

  def instance(implicit ev: Validation[Op]): Validation[Op] = ev
}