package com.kush.serversidecaretakers

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_send_notification.*
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class SendNotification : AppCompatActivity() {

    private val privateKeyObject = PrivateKeys()
    val privateServerKey: String? = privateKeyObject.applicationServerKey

    private lateinit var messageID: EditText
    private lateinit var titleMessage: EditText
    private lateinit var message: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_notification)

        val sendNotification = sendPushNotification
        sendNotification.setOnClickListener { view: View? -> sendUsingVolley() }

        messageID = MessageIDET as EditText
        titleMessage = titleET
        message = MessageET
    }

    private fun sendUsingVolley() {
        val mRequestQue = Volley.newRequestQueue(this@SendNotification)
        val json = JSONObject()
        try {
            json.put("to", "/topics/" + "Kush")
            val dataObject = JSONObject()
            dataObject.put("Title", titleMessage.text.toString())
            dataObject.put("Message", message.text.toString())
            dataObject.put("NotificationID", messageID.text.toString())
            json.put("data", dataObject)
            val URL = "https://fcm.googleapis.com/fcm/send"
            val request: JsonObjectRequest = object : JsonObjectRequest(Method.POST, URL,
                    json,
                    Response.Listener { response: JSONObject -> Log.d("MUR", "onResponse: $response") },
                    Response.ErrorListener { error: VolleyError -> Log.d("MUR", "onError: " + error.networkResponse) }
            ) {
                override fun getHeaders(): Map<String, String> {
                    val header: MutableMap<String, String> = HashMap()
                    header["content-type"] = "application/json"
                    header["authorization"] = "key=$privateServerKey"
                    return header
                }
            }
            mRequestQue.add(request)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}