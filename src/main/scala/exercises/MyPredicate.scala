package exercises

// -A it is read 'type A which is contravariant'
trait MyPredicate[-A] {
  def test(elem: A): Boolean
}
