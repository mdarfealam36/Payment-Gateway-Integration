package com.mdarfealam.paymentgatewayintegration

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mdarfealam.paymentgatewayintegration.databinding.ActivityMainBinding
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity(), PaymentResultListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // on below line adding click listener for pay button
        binding.MakePaymentBtn.setOnClickListener {

            // on below line getting amount from edit text
            val amt = binding.EdtAmount.text.toString()

            binding.EdtAmount.setText("")


            // rounding off the amount.
            val amount = Math.round(amt.toFloat() * 100).toInt()

            // on below line we are initializing razorpay account
            val checkout = Checkout()

            // on the below line we have to see our id.
            checkout.setKeyID("rzp_test_3MQV34KkWyUVx2")

            // set image
            checkout.setImage(R.drawable.profile)

            // initialize json object
            val obj = JSONObject()
            try {
                // to put name
                obj.put("name", "Md Arfe Alam")

                // put description
                obj.put("description", "Test payment")

                // to set theme color
                obj.put("theme.color", "#fff")

                // put the currency
                obj.put("currency", "INR")

                // put amount
                obj.put("amount", amount)

                // put mobile number
                obj.put("prefill.contact", "7667815236")

                // put email
                obj.put("prefill.email", "mdarfealam7667@gmail.com")

                // open razorpay to checkout activity
                checkout.open(this@MainActivity, obj)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }

    override fun onPaymentSuccess(s: String?) {
        // this method is called on payment success.
        Toast.makeText(this, "Payment is successful : " + s, Toast.LENGTH_SHORT).show();
    }

    override fun onPaymentError(p0: Int, s: String?) {
        // on payment failed.
        Toast.makeText(this, "Payment Failed due to error : " + s, Toast.LENGTH_SHORT).show();
    }
}
