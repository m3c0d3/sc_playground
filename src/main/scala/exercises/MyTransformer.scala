package exercises

trait MyTransformer[-A, B] {
  def convert(a: A): B
}
