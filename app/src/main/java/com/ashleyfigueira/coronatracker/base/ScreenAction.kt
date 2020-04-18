package com.ashleyfigueira.coronatracker.base

sealed class ScreenAction {
    object PullToRefreshAction : ScreenAction()
    object LoadMoreAction : ScreenAction()
}