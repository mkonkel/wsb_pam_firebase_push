package pl.mkonkel.wsb.firebasepush

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


// TODO: Implement your custom Messaging service.
class MyFirebaseMessagingService : FirebaseMessagingService() {

//    TODO: Create the NotificationManager here

    //    TODO: Override the "onMessageReceived" where we will be handling the PUSH message
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val title = remoteMessage.notification?.title //użyj tylko gdy nie jest null
        val message = remoteMessage.notification?.body //też jak nie null

        Log.d("MESSAGE", "title: $title / message: $message")
    }
    //    TODO: Add a helper method for creating the Notification Channel
//    You will need to provide:
//    - Channel Id
//    - Channel Name
//    - Channel Importance
//
//    You can also add some extra configuration like "lights", "vibration" etc...
//    Remember to register created channel in the notificationManager

//    TODO: Add a helper method for building the received notification
//    You need to use "NotificationCompat.Builder", and provide some basic configuration like:
//    - Small Icon
//    - Content Title
//    - Content Text
//    - etc...

//    TODO: Add a helper method for creating the Pending Intent that will allow us to run some activity
//    If you want to pass the notification to the Activity you must use the Extras

    companion object {
        const val NOTIFICATION_MESSAGE_TITLE = "message_title"
        const val NOTIFICATION_MESSAGE_BODY = "message_body"
    }
}