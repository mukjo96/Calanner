package com.example.calanner

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration




class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config =
            RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(2)
                .build()

        Realm.setDefaultConfiguration(config)
    }
}