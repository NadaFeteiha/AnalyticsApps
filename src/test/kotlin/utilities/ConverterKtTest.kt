package utilities

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

/*
   * Points That Will Be Tested:
   * 1- The extension function "calculatePercentage" .
   * 2- The extension function "convertToDouble" .
   * 3- The extension function "convertToByte"
   * 4- The extension function"stringToDate"
*/

internal class ConverterKtTest{

    @Test // Test case: 1-1
    fun should_ReturnDoubleNumberOfThePercentage_When_TheDivisorValid() {
       // given a valid value for div & divisor number.
         val divValue=6
         val divisorValue=5000
       // when the divisor is valid calculate the percentage of it.
         val result =divValue.calculatePercentage(divisorValue)
       // then check the number of percentage.
         assertEquals(0.1,result)
    }

  @Test // Test case: 1-2
  fun should_ReturnNull_When_TheDivisorNotValid() {
     // given a value zero for divisor number.
      val divValue=5000
      val divisorValue=0
     // when the divisor is valid calculate the percentage of it.
      val result =divValue.calculatePercentage(divisorValue)
     // then check the number of percentage.
      assertNull(result)
  }

   @Test // Test case: 2-1
   fun should_ReturnDoubleNumber_When_TheStringIsValid() {
    // given a valid value of requires android.
      val stringValue="4.4"
    // when the string is valid try convert to double type.
      val result =stringValue.convertToDouble()
    // then check the double number.
      assertEquals(4.4,result)
   }

    @Test // Test case: 2-2
    fun should_ReturnNull_When_TheStringIsNotValid() {
        // given a invalid value of requires android.
          val stringValue="androidVersion"
        // when the string is not try valid convert to double type.
          val result =stringValue.convertToDouble()
        // then check the double number.
          assertNull(result)
    }

    @Test// Test case: 3-1
    fun should_ReturnNull_When_ConvertInValidSize() {
        // given invalid size
        val size = "1.5.3 k"
        // when try to convert the size to Byte
        val test = size.converterToByte()
        // then
        assertNull(test)
    }

    @Test// Test case: 3-2
    fun should_ReturnByt_When_ConvertValidSize() {
        // given valid size
        val size = "1.2 m"
        // when try to convert the size to Byte
        val test = size.converterToByte()
        // then
        val expected:Long = 1258291
        assertEquals(expected, test)
    }


    @Test// Test case: 3-3
    fun should_ReturnCorrectSize_When_UnitIsCapital() {
        // given valid size with capital unit
        val size = "1.2 M"
        // when
        val test = size.converterToByte()
        // then
        val expected:Long = 1258291
        assertEquals(expected, test)
    }

    @Test// Test case: 4-1
    fun should_ReturnDate_When_FormatAsExpected(){
        // given valid string formatted date.
        val input = "april 20 2022"
        // when convert to Date
        val result = input.stringToDate()
        // then
        val expected = Date("april 20 2022")
        assertEquals(expected, result)
    }

    @Test// Test case: 4-2
    fun should_ReturnDate_When_ShortFormatForMonth(){
        // given
        val input = "apr 25 2022 "
        // when
        val result = input.stringToDate()
        // then
        val expected = Date("april 25 2022")
        assertEquals(expected, result)
    }

    @Test// Test case: 4-3
    fun should_ReturnNull_When_InputIsEmptyString(){
        // given empty string
        val input = " "
        // when try to convert it to Data
        val result = input.stringToDate()
        // then
        assertNull(result)
    }
}