package com.sun.broadcastreceiverb

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat

object NotificationUtils {
    private const val notificationID = 1

    private fun getNotificationManager(context: Context): NotificationManager {
        return context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    fun showNotification(context: Context, title: String, body: String, message: String) {
        val channelId = "channel-01"
        val channelName = "Channel Name"
        val descriptionText = "Notification android O"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(
                channelId, channelName, importance
            ).apply {
                description = descriptionText
            }
            getNotificationManager(context).createNotificationChannel(mChannel)
        }
        val intent = Intent(context, MainActivity::class.java).apply {
            putExtra(Keys.NAME, message)
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val mBuilder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setSound(sound)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
            // Add the intent, which inflates the back stack
            addNextIntentWithParentStack(intent)
            // Get the PendingIntent containing the entire back stack
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        mBuilder.setContentIntent(resultPendingIntent)
        getNotificationManager(context).notify(notificationID, mBuilder.build())
    }

    fun cancelNotification(context: Context){
        getNotificationManager(context).cancel(notificationID)
    }

}