package utilities

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

internal class ConverterKtTest{

    @Test
    fun should_ReturnNull_When_ConvertInValidSize() {
        // given wrong size
        val size = "1.5.3 k"

        // when
        val test = size.megaByteConverter()

        // then
        assertNull(test)
    }

    @Test
    fun should_ReturnByt_When_ConvertValidSize() {
        // given
        val size = "1.2 m"

        // when
        val test = size.megaByteConverter()

        // then
        val expected:Long = 1258291
        assertEquals(expected, test)
    }

    //
    @Test
    fun should_ReturnCorrectSize_When_UnitIsCapital() {
        // given
        val size = "1.2 M"

        // when
        val test = size.megaByteConverter()

        // then
        val expected:Long = 1258291
        assertEquals(expected, test)
    }



    @Test
    fun should_ReturnDate_When_FormatAsExpected(){
        // given
        val input = "april 20 2022"

        // when
        val result = input.stringToDate()

        // then
        val expected = Date("april 20 2022")
        assertEquals(expected, result)
    }

    @Test
    fun should_ReturnDate_When_ShortFormatForMonth(){
        // given
        val input = "apr 25 2022 "

        // when
        val result = input.stringToDate()

        // then
        val expected =Date("april 25 2022")
        assertEquals(expected, result)
    }

//    @Test
//    fun should_ReturnNull_When_InputIsInValid(){
//        // given
//        val input = "1 25 2022 "
//
//        // when
//        val result = input.stringToDate()
//
//        // then
//        assertNull(result)
//    }


    @Test
    fun should_ReturnNull_When_InputIsEmptyString(){
        // given
        val input = " "

        // when
        val result = input.stringToDate()

        // then
        assertNull(result)
    }
}