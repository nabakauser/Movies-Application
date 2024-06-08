package com.example.moviesapplication.utils

class Response<out T>(
    val msg : String?,
    val data: T?,
)
{
    companion object {
        fun <T> success(data: T?): Response<T> {
            return Response("SUCCESS", data)
        }

        fun <T> error (msg: String): Response<T> {
            return Response("ERROR - $msg",null)
        }
    }
}