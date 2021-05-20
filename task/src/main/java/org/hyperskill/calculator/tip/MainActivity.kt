package org.hyperskill.calculator.tip

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.google.android.material.slider.Slider
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun setMessage(bill: String, tip: String) : Unit {
            if (bill != "") {
                var result = bill.toDouble()*tip.toDouble()/100
                val n = result.toBigDecimal().setScale(2, RoundingMode.DOWN)
                val message = "Tip amount: $n"
                text_view.text = message
            }
            else {
                text_view.text = ""
            }

        }

            var bill = ""
            var tip = "0"

            findViewById<EditText>(R.id.edit_text).addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
                override fun afterTextChanged(s: Editable?) {
                    bill = s?.toString() ?: ""
                    setMessage(bill, tip)
                }
            })

            slider.addOnChangeListener { _, value, _ ->
                tip = value.toLong().toString()
                setMessage(bill, tip)
            }
    }
}