package frees.valid2

/**
  * Created by Ilya Volynin on 23.04.2019 at 20:19.
  */
import freestyle.tagless._

trait Interaction[FF$19765[_]] extends _root_.freestyle.tagless.internal.TaglessEffectLike[FF$19765] {
  self$19958 =>

  def tell(msg: String): FS[Unit]

  def ask(prompt: String): FS[String]

  def mapK[MM$19959[_]](fk$19960: _root_.cats.arrow.FunctionK[FF$19765, MM$19959]): Interaction[MM$19959] = new Interaction[MM$19959] {
    def tell(msg: String): MM$19959[Unit] = fk$19960(self$19958.tell(msg))

    def ask(prompt: String): MM$19959[String] = fk$19960(self$19958.ask(prompt))
  }
}

object Interaction {

  def apply[FF$19765[_]](implicit ev$19968: Interaction[FF$19765]): Interaction[FF$19765] = ev$19968

  implicit val functorKInstance$19967: _root_.mainecoon.FunctorK[Interaction] = new _root_.mainecoon.FunctorK[Interaction] {
    def mapK[MM$19961[_], NN$19962[_]](hh$19966: Interaction[MM$19961])(fk: _root_.cats.arrow.FunctionK[MM$19961, NN$19962]): Interaction[NN$19962] = hh$19966.mapK(fk)
  }

  implicit def derive[MM$19961[_], NN$19962[_]](implicit h: Interaction[MM$19961], fk: _root_.cats.arrow.FunctionK[MM$19961, NN$19962]): Interaction[NN$19962] = h.mapK[NN$19962](fk)

  trait Handler[FF$19765[_]] extends Interaction[FF$19765] with StackSafe.Handler[FF$19765] {
    self$19963 =>

    override def tell(msg: String): FS[Unit]

    override def ask(prompt: String): FS[String]

    override def mapK[MM$19964[_]](fk$19965: _root_.cats.arrow.FunctionK[FF$19765, MM$19964]): Handler[MM$19964] = new Handler[MM$19964] {
      def tell(msg: String): MM$19964[Unit] = fk$19965(self$19963.tell(msg))

      def ask(prompt: String): MM$19964[String] = fk$19965(self$19963.ask(prompt))
    }
  }

  trait StackSafe[FF$19765[_]] extends _root_.freestyle.free.internal.EffectLike[FF$19765] {

    def tell(msg: String): FS[Unit]

    def ask(prompt: String): FS[String]
  }

  @_root_.java.lang.SuppressWarnings(_root_.scala.Array("org.wartremover.warts.Any", "org.wartremover.warts.AsInstanceOf", "org.wartremover.warts.Throw")) object StackSafe {

    sealed trait Op[_] extends _root_.scala.Product with _root_.java.io.Serializable {

      val FSAlgebraIndex19969: _root_.scala.Int
    }

    final case class TellOp(val msg: String) extends _root_.scala.AnyRef with Op[Unit] {

      override val FSAlgebraIndex19969: _root_.scala.Int = 0
    }

    final case class AskOp(val prompt: String) extends _root_.scala.AnyRef with Op[String] {

      override val FSAlgebraIndex19969: _root_.scala.Int = 1
    }

    type OpTypes = _root_.iota.TConsK[Op, _root_.iota.TNilK]

    trait Handler[MM$19973[_]] extends _root_.freestyle.free.FSHandler[Op, MM$19973] {

      protected[this] def tell(msg: String): MM$19973[Unit]

      protected[this] def ask(prompt: String): MM$19973[String]

      override def apply[AA$19974](fa$19975: Op[AA$19974]): MM$19973[AA$19974] = ((fa$19975.FSAlgebraIndex19969: @_root_.scala.annotation.switch) match {
        case 0 =>
          val fresh19976: TellOp = fa$19975.asInstanceOf[TellOp]
          tell(fresh19976.msg)
        case 1 =>
          val fresh19977: AskOp = fa$19975.asInstanceOf[AskOp]
          ask(fresh19977.prompt)
        case i =>
          throw new _root_.java.lang.Exception("freestyle internal error: index " + i.toString() + " out of bounds for " + this.toString())
      }).asInstanceOf[MM$19973[AA$19974]]
    }

    class To[LL$19970[_]](implicit ii$19971: _root_.freestyle.free.InjK[Op, LL$19970]) extends StackSafe[LL$19970] {

      private[this] val toInj19972 = _root_.freestyle.free.FreeS.inject[Op, LL$19970](ii$19971)

      override def tell(msg: String): FS[Unit] = toInj19972(TellOp(msg))

      override def ask(prompt: String): FS[String] = toInj19972(AskOp(prompt))
    }

    implicit def to[LL$19970[_]](implicit ii$19971: _root_.freestyle.free.InjK[Op, LL$19970]): To[LL$19970] = new To[LL$19970]

    def apply[FF$19765[_]](implicit ev$19978: StackSafe[FF$19765]): StackSafe[FF$19765] = ev$19978

    def instance(implicit ev: StackSafe[Op]): StackSafe[Op] = ev
  }

}
