package exercises

trait MyPredicate[-A] {
  def test(a: A): Boolean
}
