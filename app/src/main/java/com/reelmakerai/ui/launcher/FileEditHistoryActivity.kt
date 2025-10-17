package com.reelmakerai.ui.launcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.reelmakerai.R

class FileEditHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_edit_history)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, FileEditHistoryFragment())
            .commit()
    }
}
