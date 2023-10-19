
package com.example.whatsdamlolipop

import android.content.Intent
import android.net.InetAddresses
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.whatsdamlolipop.databinding.ActivityMainBinding

/**
 * Esta clase representa la actividad principal de la aplicación. Permite al usuario ingresar un nombre de usuario
 * y una dirección IP del servidor antes de iniciar la actividad de ventanas de mensajes.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var nom = ""      // Variable para almacenar el nombre de usuario.
    private var server = ""   // Variable para almacenar la dirección IP del servidor.

    /**
     * Este método se llama cuando la actividad se crea por primera vez. Se encarga de la inicialización de la actividad.
     * @param savedInstanceState Bundle que contiene datos guardados previamente.
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Restaurar el estado de la actividad si hay datos guardados.
        if (savedInstanceState != null) {
            nom = savedInstanceState.getString("nom", "")
            server = savedInstanceState.getString("server", "")
        }

        // Configurar un listener para el botón de conexión.
        binding.buttonConnect.setOnClickListener {
            val intent = Intent(this, MessageWindowsActivity::class.java)

            // Obtener el nombre de usuario y la dirección IP del servidor desde la interfaz de usuario.
            nom = binding.nickNameText.text.toString()
            server = binding.serverAddressText.text.toString()

            // Comprobar si el nombre de usuario no está vacío y si la dirección IP es válida.
            if (nom.isNotEmpty() && InetAddresses.isNumericAddress(server)) {
                intent.putExtra("NICKNAME_KEY", nom)
                intent.putExtra("IPSERVER", server)
                startActivity(intent)
            }
        }
    }

    /**
     * Este método se llama antes de que la actividad sea destruida y se utiliza para guardar datos importantes.
     * @param outState Bundle donde se almacenan los datos a preservar.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("nom", nom)
        outState.putString("server", server)
    }
}
