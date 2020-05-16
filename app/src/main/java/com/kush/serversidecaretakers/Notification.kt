package com.kush.serversidecaretakers

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingException
import com.google.firebase.messaging.Message

class Notification {
    fun sendNotification() {
        // The topic name can be optionally prefixed with "/topics/".
        val topic = "/topics/Kush"

        // See documentation on defining a message payload.
        val message = Message.builder()
                .putData("Title", "850")
                .putData("Message", "2:45")
                .setTopic(topic)
                .build()

        // Send a message to the devices subscribed to the provided topic.
        var response = ""
        try {
            response = FirebaseMessaging.getInstance().send(message)
        } catch (e: FirebaseMessagingException) {
            e.printStackTrace()
        }
        // Response is a message ID string.
        println("Successfully sent message: $response")
    }
}