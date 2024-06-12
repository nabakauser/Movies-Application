package com.example.moviesapplication.view.overview

import org.jsoup.Jsoup

interface ImageUrlFetcher {
    suspend fun fetchImageUrlFromImdb(imdbUrl: String): String?
}

class DefaultImageUrlFetcher : ImageUrlFetcher {
    override suspend fun fetchImageUrlFromImdb(imdbUrl: String): String? {
        var url: String? = null
        try {
            val document = Jsoup.connect(imdbUrl).get()
            val element = document.select("meta[property=og:image]").first()
            url = element?.attr("content")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return url
    }
}
