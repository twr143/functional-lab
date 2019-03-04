package frees.validInterp


import freestyle.tagless._

trait Validation[FF$27996[_]] extends _root_.freestyle.tagless.internal.TaglessEffectLike[FF$27996] {
  self$28083 =>

  def minSize(s: String, n: Int): FS[Boolean]

  def hasNumber(s: String): FS[Boolean]

  def mapK[MM$28084[_]](fk$28085: _root_.cats.arrow.FunctionK[FF$27996, MM$28084]): Validation[MM$28084] = new Validation[MM$28084] {
    def minSize(s: String, n: Int): MM$28084[Boolean] = fk$28085(self$28083.minSize(s, n))

    def hasNumber(s: String): MM$28084[Boolean] = fk$28085(self$28083.hasNumber(s))
  }
}

object Validation {

  def apply[FF$27996[_]](implicit ev$28093: Validation[FF$27996]): Validation[FF$27996] = ev$28093

  implicit val functorKInstance$28092: _root_.mainecoon.FunctorK[Validation] = new _root_.mainecoon.FunctorK[Validation] {
    def mapK[MM$28086[_], NN$28087[_]](hh$28091: Validation[MM$28086])(fk: _root_.cats.arrow.FunctionK[MM$28086, NN$28087]): Validation[NN$28087] = hh$28091.mapK(fk)
  }

  implicit def derive[MM$28086[_], NN$28087[_]](implicit h: Validation[MM$28086], fk: _root_.cats.arrow.FunctionK[MM$28086, NN$28087]): Validation[NN$28087] = h.mapK[NN$28087](fk)

  trait Handler[FF$27996[_]] extends Validation[FF$27996] with StackSafe.Handler[FF$27996] {
    self$28088 =>

    override def minSize(s: String, n: Int): FS[Boolean]

    override def hasNumber(s: String): FS[Boolean]

    override def mapK[MM$28089[_]](fk$28090: _root_.cats.arrow.FunctionK[FF$27996, MM$28089]): Handler[MM$28089] = new Handler[MM$28089] {
      def minSize(s: String, n: Int): MM$28089[Boolean] = fk$28090(self$28088.minSize(s, n))

      def hasNumber(s: String): MM$28089[Boolean] = fk$28090(self$28088.hasNumber(s))
    }
  }

  trait StackSafe[FF$27996[_]] extends _root_.freestyle.free.internal.EffectLike[FF$27996] {

    def minSize(s: String, n: Int): FS[Boolean]

    def hasNumber(s: String): FS[Boolean]
  }

  @_root_.java.lang.SuppressWarnings(_root_.scala.Array("org.wartremover.warts.Any", "org.wartremover.warts.AsInstanceOf", "org.wartremover.warts.Throw")) object StackSafe {

    sealed trait Op[_] extends _root_.scala.Product with _root_.java.io.Serializable {

      val FSAlgebraIndex28094: _root_.scala.Int
    }

    final case class MinSizeOp(val s: String, val n: Int) extends _root_.scala.AnyRef with Op[Boolean] {

      override val FSAlgebraIndex28094: _root_.scala.Int = 0
    }

    final case class HasNumberOp(val s: String) extends _root_.scala.AnyRef with Op[Boolean] {

      override val FSAlgebraIndex28094: _root_.scala.Int = 1
    }

    type OpTypes = _root_.iota.TConsK[Op, _root_.iota.TNilK]

    trait Handler[MM$28098[_]] extends _root_.freestyle.free.FSHandler[Op, MM$28098] {

      protected[this] def minSize(s: String, n: Int): MM$28098[Boolean]

      protected[this] def hasNumber(s: String): MM$28098[Boolean]

      override def apply[AA$28099](fa$28100: Op[AA$28099]): MM$28098[AA$28099] = ((fa$28100.FSAlgebraIndex28094: @_root_.scala.annotation.switch) match {
        case 0 =>
          val fresh28101: MinSizeOp = fa$28100.asInstanceOf[MinSizeOp]
          minSize(fresh28101.s, fresh28101.n)
        case 1 =>
          val fresh28102: HasNumberOp = fa$28100.asInstanceOf[HasNumberOp]
          hasNumber(fresh28102.s)
        case i =>
          throw new _root_.java.lang.Exception("freestyle internal error: index " + i.toString() + " out of bounds for " + this.toString())
      }).asInstanceOf[MM$28098[AA$28099]]
    }

    class To[LL$28095[_]](implicit ii$28096: _root_.freestyle.free.InjK[Op, LL$28095]) extends StackSafe[LL$28095] {

      private[this] val toInj28097 = _root_.freestyle.free.FreeS.inject[Op, LL$28095](ii$28096)

      override def minSize(s: String, n: Int): FS[Boolean] = toInj28097(MinSizeOp(s, n))

      override def hasNumber(s: String): FS[Boolean] = toInj28097(HasNumberOp(s))
    }

    implicit def to[LL$28095[_]](implicit ii$28096: _root_.freestyle.free.InjK[Op, LL$28095]): To[LL$28095] = new To[LL$28095]

    def apply[FF$27996[_]](implicit ev$28103: StackSafe[FF$27996]): StackSafe[FF$27996] = ev$28103

    def instance(implicit ev: StackSafe[Op]): StackSafe[Op] = ev
  }

}