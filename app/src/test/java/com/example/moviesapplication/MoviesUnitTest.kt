package com.example.moviesapplication

import com.example.moviesapplication.z_testcase.TestHelper
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

class MoviesUnitTest {

    @Test
    fun isEven(){
        val helper = TestHelper()
        val result = helper.checkEvenOrOdd(0)
        assertEquals(true, result)
    }

}

@RunWith(value = Parameterized::class)
class ParameterizedClass(
    private val inputInt: Int?,
    private val expected: Boolean
) {
    @Test
    fun isEven(){
        val helper = TestHelper()
        val result = helper.checkEvenOrOdd(inputInt)
        assertEquals(expected, result)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} : {0} isEven - {1}")
        fun data() = listOf(
            arrayOf(1,false),
            arrayOf(2,true),
            arrayOf(0,true),
            arrayOf(13,false),
            arrayOf(-4,true)
        )
    }

}