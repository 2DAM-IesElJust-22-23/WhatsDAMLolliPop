#  README - Aplicación Android What's Damm Lolipop

#  Descripción

What's Damm Lolipop es una aplicación Android diseñada para conectar a los usuarios a un servidor utilizando un apodo (nickname) y una dirección IP de servidor proporcionados. Este README proporciona una descripción general de los componentes principales y la funcionalidad del código de la aplicación.

# MainActivity

La MainActivity es el punto de entrada de la aplicación. Es responsable de manejar la entrada del usuario, validar la información proporcionada y navegar a MessageWindowsActivity.

# Características Clave

Los usuarios pueden ingresar su apodo y la dirección IP del servidor.

Se realizan comprobaciones de validación para asegurarse de que tanto el apodo no esté vacío como la dirección IP del servidor sea válida.

El estado de instancia guardado se utiliza para conservar los datos de entrada del usuario durante cambios de configuración (por ejemplo, rotación de pantalla).

Al hacer clic en el botón "Conectar", el usuario se dirige a MessageWindowsActivity si la entrada es válida.

#  MessageWindowsActivity

MessageWindowsActivity es responsable de mostrar una ventana de mensajes e información de conexión al usuario.

#  Características Clave

Obtiene el apodo y la dirección IP del servidor del usuario a partir del intent de MainActivity.

Muestra información de conexión, incluyendo el apodo y la dirección IP del servidor conectado.

Permite al usuario enviar mensajes haciendo clic en el botón "Enviar", lo que borra el campo de entrada de mensajes.
