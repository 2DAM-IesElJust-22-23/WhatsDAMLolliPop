# README - Aplicación Android What's Damm Lolipop

## Descripción

What's Damm Lolipop es una aplicación Android diseñada para conectar a los usuarios a un servidor utilizando un apodo (nickname) y una dirección IP de servidor proporcionados. Este README proporciona una descripción general de los componentes principales y la funcionalidad del código de la aplicación.

## MainActivity

La MainActivity es el punto de entrada de la aplicación. Es responsable de manejar la entrada del usuario, validar la información proporcionada y navegar a MessageWindowsActivity.

## Características Clave

- Los usuarios pueden ingresar su apodo y la dirección IP del servidor.
- Se realizan comprobaciones de validación para asegurarse de que tanto el apodo no esté vacío como la dirección IP del servidor sea válida.
- El estado de instancia guardado se utiliza para conservar los datos de entrada del usuario durante cambios de configuración (por ejemplo, rotación de pantalla).
- Al hacer clic en el botón "Conectar", el usuario se dirige a MessageWindowsActivity si la entrada es válida.

## MessageWindowsActivity

MessageWindowsActivity es responsable de mostrar una ventana de mensajes e información de conexión al usuario.

## Características Clave

- Obtiene el apodo y la dirección IP del servidor del usuario a partir del intent de MainActivity.
- Muestra información de conexión, incluyendo el apodo y la dirección IP del servidor conectado.
- Permite al usuario enviar mensajes haciendo clic en el botón "Enviar", lo que borra el campo de entrada de mensajes.

# README para el proyecto WhatsDAM - Sprint 2: Lista de Mensajes

## Descripción del Proyecto

El proyecto "WhatsDAM" es una aplicación de mensajería en grupo que se encuentra en su segundo sprint de desarrollo. En este sprint, nos centraremos en la creación de la lista de mensajes, que incluirá los mensajes enviados desde nuestro dispositivo y se actualizará en tiempo real a medida que se añaden nuevos mensajes.

## 1. Diseño de la Interfaz de Usuario

Para la vista de mensajes, utilizaremos un componente RecyclerView junto con los siguientes diseños de mensajes proporcionados:

### 1.1 Diseño de Mensajes

- Mensaje Enviado por el Usuario (my_msg_viewholder.xml): Este diseño se utilizará para los mensajes enviados por el usuario. Contiene un CardView que envuelve un LinearLayout, que a su vez contiene un TextView para mostrar el contenido del mensaje. Además, hay un TextView que muestra la hora en la que se envió el mensaje.

## 2. Procedimiento

A continuación, se describe el procedimiento para implementar la lista de mensajes en el proyecto:

- Definición de la clase de datos para los mensajes, que contendrá el nombre de usuario que envió el mensaje y el texto del mensaje. Se implementará un método add para agregar mensajes a la lista.
- Creación de la clase ViewHolder, que se instanciará a partir de la vista de mensaje y establecerá las referencias a los elementos de la vista (nombre de usuario y texto del mensaje). Además, se implementará un método bind para asignar los valores correspondientes a los elementos de la vista.
- Creación del adaptador para el RecyclerView, que gestionará la vista de cada elemento de la lista de mensajes.
- Configuración del RecyclerView en la actividad de mensajes para mostrar la lista de mensajes.
- Modificación del comportamiento del botón de envío de mensajes para agregar el mensaje a la lista de mensajes junto con el nombre de usuario y el texto.

### 2.1 Consideraciones

#### 2.1.1 Consideración 1: Hora de los Mensajes en el Método bind

Cuando se implemente el método bind en el ViewHolder, es importante asignar valores tanto al texto del mensaje como a la hora.

#### 2.1.2 Consideración 2: Desplazamiento Automático en el RecyclerView

Conforme se agregan elementos al RecyclerView, es importante realizar un desplazamiento automático para que los usuarios vean los últimos mensajes enviados. Para esto, se utilizará el método smoothScrollToPosition(index) del RecyclerView, que realiza un desplazamiento para mostrar el elemento en el índice proporcionado (normalmente el último mensaje).
