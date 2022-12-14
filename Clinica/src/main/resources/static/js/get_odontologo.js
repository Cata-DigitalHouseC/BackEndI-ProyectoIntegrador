//El evento load se ejecuta al cargar la página que muestra la lista de odontólogos
window.addEventListener('load', function () {
    (function(){
        //con fetch invocamos a la API de odontólogos con el método GET
        //nos devolverá un JSON con una colección de odontólogos
        const url = '/odontologos';
        const settings = {
            method: 'GET'
        }

        fetch(url,settings)
            .then(response => response.json())
            .then(data => {
                //recorremos la colección de odontólogos del JSON
                for(odontologo of data){
                    //por cada odontólogo armaremos una fila de la tabla
                    //cada fila tendrá un ID que luego nos permitirá
                    //borrar la fila si eliminamos el odontólogo
                    var table = document.getElementById("odontologoTable");
                    var odontologoRow =table.insertRow();
                    let tr_id = 'tr_' + odontologo.id;
                    odontologoRow.id = tr_id;

                    //por cada odontólogo creamos un botón delete que
                    //agregaremos en cada fila para poder eliminar el mismo
                    //dicho botón invocará a la función de JavaScript deleteByKey que se encargará
                    //de llamar a la API para eliminar un odontólogo
                    let deleteButton = '<button' +
                        ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                        ' type="button" onclick="deleteBy('+odontologo.id+')"' +
                        'class="btn btn-danger btn_delete">' +
                        '&times' +
                        '</button>';

                    //por cada odontólogo creamos un botón que muestra el ID
                    //y que al hacerle clic invocará a la función de JavaScript findBy
                    //que se encargará de buscar el odontólogo que queremos modificar
                    //y mostrar los datos del mismo en un formulario.
                    let updateButton = '<button' +
                        ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                        ' type="button" onclick="findBy('+odontologo.id+')"' +
                        ' class="btn btn-info btn_id">' +
                        odontologo.id +
                        '</button>';

                    //armamos cada columna de la fila
                    //como primera columna pondremos el botón modificar
                    //luego los datos de cada odontólogo
                    //como última columna, el botón eliminar
                    odontologoRow.innerHTML = '<td>' + updateButton + '</td>' +
                        '<td class=\"td_id\">' + odontologo.id + '</td>' +
                        '<td class=\"td_nombre\">' + odontologo.nombre.toUpperCase() + '</td>' +
                        '<td class=\"td_apellido\">' + odontologo.apellido.toUpperCase() + '</td>' +
                        '<td class=\"td_matricula\">' + odontologo.matricula + '</td>' +
                        '<td>' + deleteButton + '</td>';
                };
            }).catch(error => alert("No hay odontologos"));
    })

    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/ListaOdontologos.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })

})