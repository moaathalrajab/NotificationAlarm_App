package edu.farmingdale.alarm_app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class MainActivity : AppCompatActivity() {
    private val CHANNEL_ID = "channel_id"
    private val NOTIFICATION_ID = 1
    private val CHANNEL_NAME = "Notification name"
    var NOTIFICIATION_IMPORTANCE = NotificationManager.IMPORTANCE_DEFAULT
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //create notification channel for android 8 and above
    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NOTIFICIATION_IMPORTANCE)
            notificationChannel.description = "This is a notification channel"

            //register it
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    fun createNotification() {
        val intent = Intent(this, MessagingMainActivity::class.java)
        intent.putExtra("MSG", "You see")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.yellow)
            .setContentTitle("Simple notification")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setStyle(
                NotificationCompat.BigTextStyle().bigText("Study hard for your capstone project")
            )
            .setContentIntent(pendingIntent)
        val notificationManagerCompat = NotificationManagerCompat.from(this)
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build())
    }

    fun sendNote(view: View?) {
        createNotificationChannel()
        createNotification()
    }
}