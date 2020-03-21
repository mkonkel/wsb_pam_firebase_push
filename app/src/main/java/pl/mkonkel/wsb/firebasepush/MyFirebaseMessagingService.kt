package pl.mkonkel.wsb.firebasepush

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val notificationManager: NotificationManager by lazy {
        applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }


        override fun onMessageReceived(remoteMessage: RemoteMessage) {
            super.onMessageReceived(remoteMessage)

            val title = remoteMessage.notification?.title ?: "empty title" //użyj tylko gdy nie jest null
            val message = remoteMessage.notification?.body ?: "empty message" //też jak nie null

            Log.d("MESSAGE", "title: $title / message: $message")

            createNotificationChannel()

            val pendingIntent = createPendingIntent(title, message)
            val notification = pendingIntent?.let { buildNotification(title, it) }

            notificationManager.notify(1, notification)
        }

        private fun createNotificationChannel() {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                "Default notification channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            notificationChannel.enableLights(true)
            notificationChannel.enableVibration(true)
            notificationChannel.lightColor = Color.BLUE
            notificationChannel.vibrationPattern = longArrayOf(100, 200, 300, 500, 100, 1, 4)
            notificationChannel.description = "Default notification channel for our Test Application"

            //register channel
            notificationManager.createNotificationChannel(notificationChannel)
        }

        private fun buildNotification(notificationTitle: String, pendingIntent: PendingIntent) : Notification {
            return Notification.Builder(applicationContext, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_pregnant_woman_black_24dp)
                .setContentTitle("UWAGA!")
                .setContentText(notificationTitle)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build()
        }

        private fun createPendingIntent(title: String, message: String?): PendingIntent? {
            val resultIntent = Intent(this, DetailActivity::class.java)

            resultIntent.putExtra(NOTIFICATION_MESSAGE_TITLE, title)
            resultIntent.putExtra(NOTIFICATION_MESSAGE_BODY, message)
            resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)

            return PendingIntent.getActivity(this, 1, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        companion object {
        const val NOTIFICATION_MESSAGE_TITLE = "message_title"
        const val NOTIFICATION_MESSAGE_BODY = "message_body"

        const val CHANNEL_ID = "12345"
    }
    }