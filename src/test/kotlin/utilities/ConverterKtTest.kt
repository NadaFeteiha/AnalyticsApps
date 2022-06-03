package utilities

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/*
   * Points That Will Be Tested:
   * 1- The extension function "calculatePercentage" .
   * 2- The extension function "convertToDouble" .
*/

internal class ConverterKtTest{

   /******************** Test The Extension Function "calculatePercentage" ********************/
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

   /******************** Test The Extension Function "convertToDouble" ********************/
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

}