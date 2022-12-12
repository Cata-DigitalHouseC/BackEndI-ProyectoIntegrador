
window.addEventListener('load', function () {

    //Al cargar la página buscamos y obtenemos el formulario donde estarán
    //los datos que el usuario cargará del nuevo paciente

    const formulario = document.querySelector('#add_new_paciente');

    //Ante un submit del formulario
    //se ejecutará la siguiente función

    formulario.addEventListener('submit', function (event) {

        //creamos un JSON que tendrá los datos
        //del nuevo paciente

        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            domicilio: document.querySelector('#domicilio').value,
            dni: document.querySelector('#dni').value,
            fechaAlta: document.querySelector('#fechaAlta').value,
        }


        //invocamos la API pacientes utilizando la función fetch de JavaScript
        //con el método POST que guardará
        //el paciente que enviaremos en formato JSON

        const url = '/pacientes';
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
                //se muestra un mensaje diciendo que el paciente
                //fue agregado
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" ' +
                    'data-dismiss="alert">&times;</button>' +
                    '<strong></strong> Paciente agregado </div>'

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            })
            .catch(error => {
                //Si hay algún error,
                //se muestra un mensaje diciendo que el paciente
                //no se pudo guardar y se intente nuevamente
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close"' +
                    'data-dismiss="alert">&times;</button>' +
                    '<strong> Error intente nuevamente</strong> </div>'

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";

                //se dejan todos los campos vacíos
                //por si se quiere ingresar otro odntologo
                resetUploadForm();})
    });

    function resetUploadForm(){
        document.querySelector('#nombre').value = '';
        document.querySelector('#apellido').value = '';
        document.querySelector('#domicilio').value = '';
        document.querySelector('#dni').value = '';
        document.querySelector('#fechaAlta').value = '';
    }
});