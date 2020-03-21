package pl.mkonkel.wsb.firebasepush

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


// TODO: Implement your custom Messaging service.
@SuppressLint("Registered")
class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val notyficationMenager: NotificationManager by lazy {
        applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val title = remoteMessage.notification?.title ?: "empty title"   //? - uzyj tylko gdy nie jest null

        val message = remoteMessage.notification?.body ?: "empty message" // ?: - elwis cośtam - jak po lewej jest null uzy j wartości z prawej

        Log.d("MESSAGE", "title: $title / message: $message ")

        createNotificationChanel()
        val notification = buildNotification(message)

        notyficationMenager.notify(1 ,notification)
    }


    private fun createNotificationChanel(){
        val notyficationChanel = NotificationChannel(
            CHANEL_ID,
            "Default notyfication chanell",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notyficationChanel.enableLights(true)
        notyficationChanel.enableVibration(true)
        notyficationChanel.vibrationPattern = longArrayOf(100, 200, 300, 500, 300, 200, 100)
        notyficationChanel.lightColor = Color.BLUE
        notyficationChanel.description = "Default notyfication chanell for test aplication"

        notyficationMenager.createNotificationChannel(notyficationChanel)
    }


    private fun buildNotification(notificationTitle: String) : Notification{
        return Notification.Builder(applicationContext, CHANEL_ID)
            .setSmallIcon(R.drawable.ic_add_alert_black_24dp)
            .setContentTitle("UWAGA!")
            .setContentText(notificationTitle)
            .setAutoCancel(true)
            .build()
    }

//    TODO: Add a helper method for creating the Pending Intent that will allow us to run some activity
//    If you want to pass the notification to the Activity you must use the Extras

    companion object {
        const val NOTIFICATION_MESSAGE_TITLE = "message_title"
        const val NOTIFICATION_MESSAGE_BODY = "message_body"
        const val CHANEL_ID = "1234123"
    }
}