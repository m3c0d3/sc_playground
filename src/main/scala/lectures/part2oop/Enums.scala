package lectures.part2oop

object Enums extends App {
  // it is sealed by default
  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    // they can have fields or methods
    def openDocument: Unit =
      if (this == READ) println("opening document")
      else println("not allowed")
  }

  val somePermissions = Permissions.WRITE
  somePermissions.openDocument

  // enums can take constructor arguments
  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4) // 100
    case WRITE extends PermissionsWithBits(2) // 010
    case EXECUTE extends PermissionsWithBits(1) // 001
    case NONE extends PermissionsWithBits(0) // 000
  }

  // you can also can have companion objects for enums
  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits =
      PermissionsWithBits.NONE
  }

  // standard API
  // inspecting the ordinal, according to where that permissions where defined, the index where the enum value is defined
  println(PermissionsWithBits.NONE.ordinal)

  // convert from string to permission for permissions that don't need arguments
  println(Permissions.valueOf("WRITE"))


  // API to see all permission
  private val values: Array[Permissions] = Permissions.values
  println(values)
}
