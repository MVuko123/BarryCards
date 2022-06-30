package com.example.barrycards.javaPitanja

import android.app.Application
import androidx.lifecycle.LiveData

class JavaRepozitoijOR(
    application: Application,
    javaBaza: JavaBaza
): JavaRepozitorij(application, javaBaza) {
    override val javaList: LiveData<List<JavaPitanja>>
        get() {
            return this.getJavaList
        }
}