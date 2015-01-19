package vm
object Bytecode {
  val IADD:     Short = 1  // Integer add
  val ISUB:     Short = 2  // Integer subtract
  val IMUL:     Short = 3  // Integer multiply
  val ILT:      Short = 4
  val IEQ:      Short = 5
  val BR:       Short = 6
  val BRT:      Short = 7
  val BRF:      Short = 8
  val ICONST:   Short = 9
  val LOAD:     Short = 10
  val GLOAD:    Short = 11
  val STORE:    Short = 12
  val GSTORE:   Short = 13
  val PRINT:    Short = 14
  val POP:      Short = 15
  val HALT:     Short = 16
}