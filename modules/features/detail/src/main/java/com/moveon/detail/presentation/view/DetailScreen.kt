package com.moveon.detail.presentation.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moveon.detail.presentation.viewmodel.DetailViewModel
import com.moveon.ui_core.BoxWithText
import com.moveon.ui_core.ProgressIndicator
import com.moveon.ui_core.data.ShowType
import com.moveon.ui_core.modifierext.bottomPadding12Dp
import com.moveon.ui_core.modifierext.bottomPadding2Dp
import com.moveon.ui_core.modifierext.bottomPadding6Dp
import com.moveon.ui_core.modifierext.endPadding16Dp
import com.moveon.ui_core.modifierext.endPadding4Dp

@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel = hiltViewModel(),
    imbdId: String?,
    tmdbId: Int?
) {
    if (imbdId != detailViewModel.imdbId || tmdbId != detailViewModel.tmdbId) {
        detailViewModel.updateRequestIds(imbdId, tmdbId)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        detailViewModel.detail?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(text = it.title, modifier = Modifier.align(Alignment.CenterHorizontally))

                Spacer(modifier = Modifier.bottomPadding12Dp())

                Text(text = it.description)

                Spacer(modifier = Modifier.bottomPadding6Dp())

                Text(text = it.year)

                if (it.type == ShowType.Series) {
                    AnimatedVisibility(visible = !detailViewModel.isSeasonsVisible) {
                        Row {
                            BoxWithText(text = "${it.seasonsCount} seasons") {
                                detailViewModel.showSeasons()
                            }

                            Spacer(modifier = Modifier.endPadding4Dp())


                            BoxWithText(text = "${it.episodesCount} episodes") {
                                detailViewModel.showSeasons()
                            }
                        }
                    }

                    Spacer(modifier = Modifier.bottomPadding2Dp())

                    AnimatedVisibility(visible = detailViewModel.isSeasonsVisible) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Column {
                                it.seasons.forEach { season ->
                                    DetailSeasonView(seasonPresentation = season)
                                    if (season != it.seasons.last()) {
                                        Spacer(modifier = Modifier.bottomPadding6Dp())
                                    }
                                }
                            }

                            Icon(
                                painter = painterResource(id = com.moveon.ui_core.R.drawable.arrow_up),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(35.dp)
                                    .align(Alignment.TopEnd)
                                    .endPadding16Dp()
                                    .clickable { detailViewModel.hideSeasons() },
                                tint = Color.Unspecified
                            )
                        }
                    }

                    Spacer(modifier = Modifier.bottomPadding6Dp())

                    Text(text = "Creators")

                    Spacer(modifier = Modifier.bottomPadding2Dp())

                    it.creators?.forEach {
                        Text(text = it)
                    }

                    Spacer(modifier = Modifier.bottomPadding6Dp())

                    Text(text = "Cast")

                    Spacer(modifier = Modifier.bottomPadding2Dp())

                    it.cast.forEach {
                        Text(text = it)
                    }
                }
            }
        }
        if (detailViewModel.isErrorVisible) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = detailViewModel.error, modifier = Modifier.align(Alignment.Center))
            }
        }
        if (detailViewModel.isProgressVisible) {
            ProgressIndicator()
        }
    }
}