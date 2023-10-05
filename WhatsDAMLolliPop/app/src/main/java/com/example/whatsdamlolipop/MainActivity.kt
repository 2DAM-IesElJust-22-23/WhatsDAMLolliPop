package com.example.whatsdamlolipop

import android.content.Intent
import android.net.InetAddresses
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.whatsdamlolipop.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var nom = ""
    private var server = ""
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null){

            nom = savedInstanceState.getString("nom","")
            server = savedInstanceState.getString("server","")

        }

        binding.buttonConnect.setOnClickListener  {

            val intent = Intent(this, MessageWindowsActivity::class.java)

            nom = binding.nickNameText.text.toString()
            server = binding.serverAddressText.text.toString()

            if(nom.isNotEmpty() && InetAddresses.isNumericAddress(server)){
                
                intent.putExtra("NICKNAME_KEY", nom)
                intent.putExtra("IPSERVER", server)
                startActivity(intent)

            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle){
        super.onSaveInstanceState(outState)
        outState.putString("nom", nom)
        outState.putString("server", server)
    }
}