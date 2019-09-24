package com.xyzcorp.exercises

import org.scalacheck.Gen
import org.scalacheck.Prop.forAll
import org.scalatest.{FunSpec, Matchers}

class CaesarShiftTest extends FunSpec with Matchers {

  describe("encoding a string") {
    info("Start with the simplest case")
    info("Input - String, Int") // describe api first
    info("Output - String")

    it("should accept an empty string and a shift of zero and return an empty string") {
      CaesarShift.encode("", 0) shouldBe ""
    }

    it("should accept a single letter with a shift of zero and return the same letter") {
      CaesarShift.encode("a", 0) shouldBe "a"
    }

    it("should shift a single letter by 1") {
      CaesarShift.encode("a", 1) shouldBe "b"
    }

    it("should shift multiple characters by 1") {
      CaesarShift.encode("ab", 1) shouldBe "bc"
    }

    it("should wrap from z to a") {
      CaesarShift.encode("z", 1) shouldBe "a"
    }

    it("should work for upper case") {
      CaesarShift.encode("A", 1) shouldBe "B"
    }

    it("should only shift letters") {
      CaesarShift.encode(" ", 2) shouldBe " "
    }

    it("should accept a negative shift") {
      CaesarShift.encode("b", -1) shouldBe "a"
    }

    it("should wrap around a negative shift") {
      CaesarShift.encode("a", -1) shouldBe "z"
    }

    it("should handle large shifts") {
      CaesarShift.encode("a", 26 * 1000) shouldBe "a"
    }
  }

  describe("decoding a string") {
    it("should accept an empty string and a shift of zero and return an empty string") {
      CaesarShift.decode("", 0) shouldBe ""
    }

    it("should shift a single letter by 1") {
      CaesarShift.decode("b", 1) shouldBe "a"
    }

    it("should shift multiple characters by 1") {
      CaesarShift.decode("bc", 1) shouldBe "ab"
    }

    describe("commutative property") {
      it("should return same value after encoding") {
        val s = "Hello World!"
        val shift = 3
        CaesarShift.decode(CaesarShift.encode(s, shift), shift) shouldBe s
      }

      it("should return same value after encoding for all") {
        forAll { (s: String, shift: Int) => CaesarShift.decode(CaesarShift.encode(s, shift), shift) == s}
      }

      it("should return same value after encoding for all with generators") {
        val sGen = Gen.alphaNumStr
        val shiftGen = Gen.oneOf(Int.MinValue, Int.MaxValue)
        forAll(sGen, shiftGen) {
          (s: String, shift: Int) => CaesarShift.decode(CaesarShift.encode(s, shift), shift) == s
        }
      }
    }
  }
}
