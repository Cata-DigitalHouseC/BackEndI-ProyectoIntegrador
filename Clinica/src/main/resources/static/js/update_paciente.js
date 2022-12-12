
window.addEventListener('load', function () {

    //Buscamos y obtenemos el formulario donde están
    //los datos que el usuario pudo haber modificado del paciente
    const formulario = document.querySelector('#update_paciente_form');
    formulario.addEventListener('submit', function (event) {

        let pacienteId = document.querySelector('#paciente_id').value;

        //creamos un JSON que tendrá los datos del paciente
        //a diferencia de un paciente nuevo en este caso enviamos el ID
        //para poder identificarla y modificarla para no cargarlo como nuevo
        const formData = {
            id: document.querySelector('#paciente_id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            domicilio: document.querySelector('#domicilio').value,
            dni: document.querySelector('#dni').value,
            fechaAlta: document.querySelector('#fechaAlta').value,
        };

        //invocamos utilizando la función fetch la API pacientes con el método PUT
        //que modificará al paciente que enviaremos en formato JSON
        const url = '/pacientes'+"/"+pacienteId;
        const settings = {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url,settings)
            .then(response => response.json())
    })
})

//Es la función que se invoca cuando se hace clic
//sobre el ID de un paciente del listado
//se encarga de llenar el formulario con los datos del paciente
//que se desea modificar
function findBy(id) {
    const url = '/pacientes'+"/"+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            let paciente = data;
            document.querySelector('#paciente_id').value = paciente.id;
            document.querySelector('#nombre').value = paciente.nombre;
            document.querySelector('#apellido').value = paciente.apellido;
            document.querySelector('#domicilio').value = paciente.domicilio;
            document.querySelector('#dni').value = paciente.dni;
            document.querySelector('#fechaAlta').value = paciente.fechaAlta;

            //el formulario por default está oculto y al editar se habilita
            document.querySelector('#div_paciente_updating').style.display = "block";
        }).catch(error => {
        alert("Error: " + error);
    })
}