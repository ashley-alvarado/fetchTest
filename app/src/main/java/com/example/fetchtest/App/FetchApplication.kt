package com.example.fetchtest.App

import android.app.Application
import com.example.fetchtest.data.AppContainer

class FetchApplication : Application() {
    var container = AppContainer()
}