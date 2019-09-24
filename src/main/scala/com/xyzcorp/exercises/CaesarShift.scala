package com.xyzcorp.exercises

object CaesarShift {
  val offsetUpper: Char = 'A'
  val offsetLower: Char = 'a'
  val range: Int = 'Z' - 'A' + 1


  def encode(s: String, shift: Int): String = {
    def shiftChar(c: Char, offset: Char): Char = {
      val c2 = (offset + (c.toInt + shift - offset) % range).toChar
      if(c2 < offset) (c2 + range).toChar
      else c2
    }

    s.map{
      case c if c.isLower => shiftChar(c, offsetLower)
      case c if c.isUpper => shiftChar(c, offsetUpper)
      case c => c
    }
  }


  def decode(s: String, shift: Int): String = encode(s, -shift)
}
