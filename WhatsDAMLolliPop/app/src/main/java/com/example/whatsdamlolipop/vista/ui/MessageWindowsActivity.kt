package com.example.whatsdamlolipop.vista.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.whatsdamlolipop.databinding.ActivityMessageWindowsBinding
import com.example.whatsdamlolipop.model.Missatge
import com.example.whatsdamlolipop.model.Missatges
import com.example.whatsdamlolipop.viewModel.AdaptadorMissatges
import com.example.whatsdamlolipop.viewModel.MissatgesViewModel

/**
 * Clase MessageWindowsActivity
 *
 * Esta clase representa la actividad principal de la aplicación, donde se muestra
 * la conversación y se permite a los usuarios enviar mensajes.
 */
class MessageWindowsActivity : AppCompatActivity() {

    // Vista de enlace para la actividad.
    private lateinit var binding: ActivityMessageWindowsBinding

    // Adaptación a MVVM: Instancia definida del ViewModel com a lateinit
    private lateinit var viewModel : MissatgesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el diseño de la actividad y configurar la vista.
        binding = ActivityMessageWindowsBinding.inflate(layoutInflater)

        //Establecemos el contenido de la vista
        setContentView(binding.root)

        // Instanciamos el ViewModel mediante ViewModelProvider
        viewModel= ViewModelProvider(this)[MissatgesViewModel::class.java]

        // Creamos un observador y lo subscrivimos al LiveD<ta adaptador
        // definido al ViewModel. Vuando se produzcan canvio al ViewModel
        // ser reflejarana en el adaptador del RecyclerView
        viewModel.adaptador.observe(this){
            binding.MessagesRecyclerView.adapter = it
        }

        // Referencias a vistas en el diseño de la actividad.
        val message = binding.MessageText
        val textV = binding.connectionInfoTextView
        val sendM = binding.sendMessage
        val listMissatges = Missatges.llistaMissatges

        // Configurar el administrador de diseño del RecyclerView.
        binding.MessagesRecyclerView.layoutManager = LinearLayoutManager(this)

        // Establecer el adaptador para el RecyclerView y proporcionar la lista de mensajes.
        binding.MessagesRecyclerView.adapter = AdaptadorMissatges(listMissatges)

        // Obtener el nombre de usuario y la dirección del servidor desde la actividad anterior.
        val nickname = intent.getStringExtra("NICKNAME_KEY")
        val server = intent.getStringExtra("IPSERVER")

        // Actualizar el texto que muestra la información de conexión.
        textV.text = "Conectado a $server como $nickname"

        // Manejar el clic en el botón de enviar mensaje.
        sendM.setOnClickListener {
            if (message.text.isNotEmpty()){

                // Listener para añadir un mensaje a la l sita
                viewModel.addMissatge(Missatge(nickname.toString(), message.text.toString()))

                Log.d(TAG, "Afegix missatge: ")


                // Limpiar el campo de texto del mensaje.
                message.text.clear()

            }

        }
    }
}
