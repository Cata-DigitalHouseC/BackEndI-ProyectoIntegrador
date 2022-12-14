
window.addEventListener('load', function () {

    //Buscamos y obtenemos el formulario donde están
    //los datos que el usuario pudo haber modificado del odontólogo
    const formulario = document.querySelector('#update_odontologo_form');
    formulario.addEventListener('submit', function (event) {

        let odontologoId = document.querySelector('#odontologo_id').value;

        //creamos un JSON que tendrá los datos del odontologo
        //a diferencia de un odontologo nuevo en este caso enviamos el ID
        //para poder identificarla y modificarla para no cargarlo como nuevo
        const formData = {
            id: document.querySelector('#odontologo_id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value,
        };

        //invocamos utilizando la función fetch la API odontologos con el método PUT
        //que modificará al odontologo que enviaremos en formato JSON
        const url = '/odontologos'+"/"+odontologoId;
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
//sobre el ID de un odontologo del listado
//se encarga de llenar el formulario con los datos del odontologo
//que se desea modificar
function findBy(id) {
    const url = '/odontologos'+"/"+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            let odontologo = data;
            document.querySelector('#odontologo_id').value = odontologo.id;
            document.querySelector('#nombre').value = odontologo.nombre;
            document.querySelector('#apellido').value = odontologo.apellido;
            document.querySelector('#matricula').value = odontologo.matricula;
            //el formulario por default está oculto y al editar se habilita
            document.querySelector('#div_odontologo_updating').style.display = "block";
        }).catch(error => {
        alert("Error: " + error);
    })
}