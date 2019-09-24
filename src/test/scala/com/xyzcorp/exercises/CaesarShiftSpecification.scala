package com.xyzcorp.exercises

import org.scalacheck.Prop.forAll
import org.scalacheck.Properties

object CaesarShiftSpecification extends Properties("CaesarShift") {
  property("encode and decode should be the same") = forAll {
    (s: String, shift: Int) => CaesarShift.decode(CaesarShift.encode(s, shift), shift) == s
  }
}
