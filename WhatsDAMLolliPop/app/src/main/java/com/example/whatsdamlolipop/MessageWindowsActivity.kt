package com.example.whatsdamlolipop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.whatsdamlolipop.databinding.ActivityMessageWindowsBinding

class MessageWindowsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMessageWindowsBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMessageWindowsBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val message = binding.MessageText
            val textV = binding.connectionInfoTextView
            val sendM = binding.sendMessage


            val nickname = intent.getStringExtra("NICKNAME_KEY")
            val server = intent.getStringExtra("IPSERVER")

            textV.text = "Conectat a $nickname com a $server"

            sendM.setOnClickListener(){
                message.text.clear()
            }

        }

    }

