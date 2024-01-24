package app.test.trading_app.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import app.test.trading_app.R
import app.test.trading_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding:  ActivityMainBinding= ActivityMainBinding.inflate(layoutInflater);
        setContentView(R.layout.activity_main)

        binding.toolwarIc.setOnClickListener {
            Toast.makeText(this, "message", Toast.LENGTH_SHORT).show()
        }

    }


}