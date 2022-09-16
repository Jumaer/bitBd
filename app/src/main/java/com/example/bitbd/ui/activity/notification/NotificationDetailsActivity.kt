package com.example.bitbd.ui.activity.notification

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.RequiresApi
import com.example.bitbd.R
import com.example.bitbd.constant.MESSAGE
import com.example.bitbd.constant.TIME_CREATED
import com.example.bitbd.constant.TIME_UPDATED
import com.example.bitbd.databinding.ActivityNotificationBinding
import com.example.bitbd.databinding.ActivityNotificationDetailsBinding
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class NotificationDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationDetailsBinding
    var message = ""
    var createdTime = ""
    var updatedTime = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = getString(R.string.noticeDetails)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        if (intent != null) {
            message = intent.getStringExtra(MESSAGE).toString()
            createdTime = intent.getStringExtra(TIME_CREATED).toString()
            updatedTime = intent.getStringExtra(TIME_UPDATED).toString()
        }

        loadViewOfNotice(message, createdTime, updatedTime)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadViewOfNotice(message: String, createdTime: String, updatedTime: String) {
        binding.messageNotification.text = message
        binding.dateNotification.text ="Date : " + ZonedDateTime.parse(createdTime)
            .format(
                DateTimeFormatter.ofPattern(
                    "dd MMMM yyyy",
                    Locale.ENGLISH
                )
            )

        binding.timeNotification.text ="Time : " + ZonedDateTime.parse(createdTime)
            .format(
                DateTimeFormatter.ofPattern(
                    "HH:mm a",
                    Locale.ENGLISH
                )
            )

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}