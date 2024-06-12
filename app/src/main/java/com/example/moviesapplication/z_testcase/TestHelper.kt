package com.example.moviesapplication.z_testcase

class TestHelper {
    fun checkEvenOrOdd(value: Int?): Boolean {
        return when {
            value == null -> false
            value % 2 == 0 -> true
            else -> false
        }
    }
}