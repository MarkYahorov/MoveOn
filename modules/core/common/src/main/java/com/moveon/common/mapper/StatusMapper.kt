package com.moveon.common.mapper

import com.moveon.common.data.StatusResponse
import com.moveon.ui_core.data.StatusPresentation
import javax.inject.Inject

class StatusMapper @Inject constructor() {

    fun map(status: StatusResponse): StatusPresentation {
        return when (status) {
            StatusResponse.Ended -> StatusPresentation.Ended
            StatusResponse.Pilot -> StatusPresentation.Pilot
            StatusResponse.Planned -> StatusPresentation.Planned
            StatusResponse.Cancelled -> StatusPresentation.Cancelled
            StatusResponse.InProduction -> StatusPresentation.InProduction
            StatusResponse.Returning -> StatusPresentation.Returning
        }
    }
}