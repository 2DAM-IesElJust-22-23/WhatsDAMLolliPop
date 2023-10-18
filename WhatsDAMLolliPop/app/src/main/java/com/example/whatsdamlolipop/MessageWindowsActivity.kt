package com.example.whatsdamlolipop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whatsdamlolipop.databinding.ActivityMessageWindowsBinding

/**
 * Clase MessageWindowsActivity
 *
 * Esta clase representa la actividad principal de la aplicación, donde se muestra
 * la conversación y se permite a los usuarios enviar mensajes.
 */
class MessageWindowsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMessageWindowsBinding
    private lateinit var adapter: AdaptadorRecycler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el diseño de la actividad y configurar la vista.
        binding = ActivityMessageWindowsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Referencias a vistas en el diseño de la actividad.
        val message = binding.MessageText
        val textV = binding.connectionInfoTextView
        val sendM = binding.sendMessage
        val recyclerView = binding.MessagesRecyclerView

        // Configurar el administrador de diseño del RecyclerView.
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Crear y configurar el adaptador para el RecyclerView.
        adapter = AdaptadorRecycler(llistaMissatges)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        // Obtener el nombre de usuario y la dirección del servidor desde la actividad anterior.
        val nickname = intent.getStringExtra("NICKNAME_KEY")
        val server = intent.getStringExtra("IPSERVER")

        // Actualizar el texto que muestra la información de conexión.
        textV.text = "Conectado a $server como $nickname"

        // Manejar el clic en el botón de enviar mensaje.
        sendM.setOnClickListener {
            // Agregar el mensaje a la lista de mensajes.
            llistaMissatges.add(Missatge(nickname.toString(), message.text.toString()))

            // Notificar al adaptador que se ha insertado un nuevo elemento.
            binding.MessagesRecyclerView.adapter?.notifyItemInserted(llistaMissatges.size - 1)

            // Desplazar el RecyclerView al último mensaje.
            recyclerView.scrollToPosition(llistaMissatges.size - 1)

            // Limpiar el campo de texto del mensaje.
            message.text.clear()
        }
    }
}
