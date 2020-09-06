package com.example.stage2task6.mvp.model

import com.example.stage2task6.data.DataSource
import com.example.stage2task6.data.local.model.Film
import com.example.stage2task6.mvp.view.fragments.ListFragment.Companion.REPO
import com.example.stage2task6.mvp.view.fragments.ListFragment.Companion.RSS

class FilmModel {

    suspend fun getData(source: String): Result {
        return when (source) {
            RSS -> {
                val response = DataSource.getFilmsFromXmlAsync().await()
                if (response.isSuccessful) {
                    val items = response.body()
                        ?.channel
                        ?.itemsList?.map { item ->
                            Film(
                                item.title,
                                item.image?.url,
                                item.duration,
                                item.media?.content?.map { it.mediaUrl },
                                item.description
                            )
                        }!!
                    Result.ResultSuccess(items.reversed())
                } else {
                    Result.ResultFailed("Some problems happen during downloading")
                }
            }
            REPO -> {
                val response = DataSource.getFilmsFromJsonAsync().await()
                if (response.isSuccessful) {
                    val items = response.body()
                        ?.channel
                        ?.items
                        ?.map { item ->
                            Film(
                                item.title,
                                item.image.url,
                                item.duration.time,
                                item.media.mediaContent
                                    .map { mediaData ->
                                        mediaData.mediaUrl
                                    },
                                item.description
                            )
                        }!!
                    Result.ResultSuccess(items)
                } else
                    Result.ResultFailed("Some problems happen during downloading")
            }

            else -> Result.ResultFailed("Some problems happen during downloading")
        }
    }
}
