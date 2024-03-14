package com.moveon.changes.presentation.mappers

import android.util.Log
import com.moveon.changes.data.data.response.ChangesStreamingTypeResponse
import com.moveon.changes.data.data.response.StreamingChangesResponse
import com.moveon.ui_core.data.ChangesStreamingTypePresentation
import com.moveon.ui_core.data.StreamingChangesPresentation
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class StreamingChangesMapper @Inject constructor() {
    fun map(response: StreamingChangesResponse): StreamingChangesPresentation {
        val lastUpdateDate = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(response.time.toLong() * 1000),
            ZoneId.systemDefault()
        )
        val currentDate = LocalDateTime.now()
        val lastUpdateText =
            if (lastUpdateDate.toLocalDate().year == currentDate.toLocalDate().year) {
                SimpleDateFormat("dd MMM").format(Date(response.time.toLong() * 1000))
            } else {
                SimpleDateFormat("dd MM yyyy").format(Date(response.time.toLong() * 1000))
            }
        return with(response) {
            StreamingChangesPresentation(
                service = service,
                time = lastUpdateText,
                streamingType = mapStreamingType(streamingType)
            )
        }
    }

    private fun mapStreamingType(
        streamingType: ChangesStreamingTypeResponse
    ): ChangesStreamingTypePresentation {
        return when (streamingType) {
            ChangesStreamingTypeResponse.Addon -> ChangesStreamingTypePresentation.Addon
            ChangesStreamingTypeResponse.Buy -> ChangesStreamingTypePresentation.Buy
            ChangesStreamingTypeResponse.Free -> ChangesStreamingTypePresentation.Free
            ChangesStreamingTypeResponse.Rent -> ChangesStreamingTypePresentation.Rent
            ChangesStreamingTypeResponse.Subscription -> ChangesStreamingTypePresentation.Subscription
        }
    }

}