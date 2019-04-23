package frees.valid2
import freestyle.tagless._

trait Validation[FF$19979[_]] extends _root_.freestyle.tagless.internal.TaglessEffectLike[FF$19979] {
  self$20072 =>

  def rules: FS[List[String => String]]

  def mapK[MM$20073[_]](fk$20074: _root_.cats.arrow.FunctionK[FF$19979, MM$20073]): Validation[MM$20073] = new Validation[MM$20073] {
    def rules: MM$20073[List[String => String]] = fk$20074(self$20072.rules)
  }
}

object Validation {

  def apply[FF$19979[_]](implicit ev$20082: Validation[FF$19979]): Validation[FF$19979] = ev$20082

  implicit val functorKInstance$20081: _root_.mainecoon.FunctorK[Validation] = new _root_.mainecoon.FunctorK[Validation] {
    def mapK[MM$20075[_], NN$20076[_]](hh$20080: Validation[MM$20075])(fk: _root_.cats.arrow.FunctionK[MM$20075, NN$20076]): Validation[NN$20076] = hh$20080.mapK(fk)
  }

  implicit def derive[MM$20075[_], NN$20076[_]](implicit h: Validation[MM$20075], fk: _root_.cats.arrow.FunctionK[MM$20075, NN$20076]): Validation[NN$20076] = h.mapK[NN$20076](fk)

  trait Handler[FF$19979[_]] extends Validation[FF$19979] with StackSafe.Handler[FF$19979] {
    self$20077 =>

    override def rules: FS[List[String => String]]

    override def mapK[MM$20078[_]](fk$20079: _root_.cats.arrow.FunctionK[FF$19979, MM$20078]): Handler[MM$20078] = new Handler[MM$20078] {
      def rules: MM$20078[List[String => String]] = fk$20079(self$20077.rules)
    }
  }

  trait StackSafe[FF$19979[_]] extends _root_.freestyle.free.internal.EffectLike[FF$19979] {

    def rules: FS[List[String => String]]
  }

  @_root_.java.lang.SuppressWarnings(_root_.scala.Array("org.wartremover.warts.Any", "org.wartremover.warts.AsInstanceOf", "org.wartremover.warts.Throw")) object StackSafe {

    sealed trait Op[_] extends _root_.scala.Product with _root_.java.io.Serializable {

      val FSAlgebraIndex20083: _root_.scala.Int
    }

    final case class RulesOp() extends _root_.scala.AnyRef with Op[List[String => String]] {

      override val FSAlgebraIndex20083: _root_.scala.Int = 0
    }

    type OpTypes = _root_.iota.TConsK[Op, _root_.iota.TNilK]

    trait Handler[MM$20087[_]] extends _root_.freestyle.free.FSHandler[Op, MM$20087] {

      protected[this] def rules: MM$20087[List[String => String]]

      override def apply[AA$20088](fa$20089: Op[AA$20088]): MM$20087[AA$20088] = ((fa$20089.FSAlgebraIndex20083: @_root_.scala.annotation.switch) match {
        case 0 =>
          rules
        case i =>
          throw new _root_.java.lang.Exception("freestyle internal error: index " + i.toString() + " out of bounds for " + this.toString())
      }).asInstanceOf[MM$20087[AA$20088]]
    }

    class To[LL$20084[_]](implicit ii$20085: _root_.freestyle.free.InjK[Op, LL$20084]) extends StackSafe[LL$20084] {

      private[this] val toInj20086 = _root_.freestyle.free.FreeS.inject[Op, LL$20084](ii$20085)

      override def rules: FS[List[String => String]] = toInj20086(RulesOp())
    }

    implicit def to[LL$20084[_]](implicit ii$20085: _root_.freestyle.free.InjK[Op, LL$20084]): To[LL$20084] = new To[LL$20084]

    def apply[FF$19979[_]](implicit ev$20090: StackSafe[FF$19979]): StackSafe[FF$19979] = ev$20090

    def instance(implicit ev: StackSafe[Op]): StackSafe[Op] = ev
  }

}
