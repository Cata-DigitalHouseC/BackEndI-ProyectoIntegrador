window.addEventListener('load', function () {

    //Al cargar la página buscamos y obtenemos el formulario donde estarán
    //los datos que el usuario cargará del nuevo  turno

    const formulario = document.querySelector('#add_new_turno');

    //Ante un submit del formulario
    //se ejecutará la siguiente función

    formulario.addEventListener('submit', function (event) {

        //creamos un JSON que tendrá los datos
        //del nuevo turno

        const formData = {
            paciente_id: document.querySelector('#idPaciente').value,
            odontologo_id: document.querySelector('#idOdontologo').value,
            date: document.querySelector('#date').value,
        }


        //invocamos la API odontólogos utilizando la función fetch de JavaScript
        //con el método POST que guardará
        //el turno que enviaremos en formato JSON

        const url = '/turnos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                //Si no hay ningún error,
                //se muestra un mensaje diciendo que el turno
                //fue agregado
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" ' +
                    'data-dismiss="alert">&times;</button>' +
                    '<strong></strong> Turno agregado </div>'

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            })
            .catch(error => {
                //Si hay algún error,
                //se muestra un mensaje diciendo que el turno
                //no se pudo guardar y se intente nuevamente
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close"' +
                    'data-dismiss="alert">&times;</button>' +
                    '<strong> Error intente nuevamente</strong> </div>'

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";

                //se dejan todos los campos vacíos
                //por si se quiere ingresar otro turno

                resetUploadForm();})
    });

    function resetUploadForm(){
        document.querySelector('#idPaciente').value = '';
        document.querySelector('#idOdontologo').value = '';
        document.querySelector('#date').value = '';
    }
});