package exercises

// -A it is read 'type A which is contravariant'
trait MyTransformer[-A, B] {
  def transform(elem: A): B
}
