//El evento load se ejecuta al cargar la página que muestra la lista de turnos
window.addEventListener('load', function () {
    (function(){
        //con fetch invocamos a la API de turnos con el método GET
        //nos devolverá un JSON con una colección de turnos
        const url = '/turnos';
        const settings = {
            method: 'GET'
        }

        fetch(url,settings)
            .then(response => response.json())
            .then(data => {
                //recorremos la colección de turnos del JSON
                for(turno of data){
                    //por cada turno armaremos una fila de la tabla
                    //cada fila tendrá un ID que luego nos permitirá
                    //borrar la fila si eliminamos el turno
                    var table = document.getElementById("turnoTable");
                    var turnoRow =table.insertRow();
                    let tr_id = 'tr_' + turno.id;
                    odontologoRow.id = tr_id;

                    //por cada turnoo creamos un botón delete que
                    //agregaremos en cada fila para poder eliminar el mismo
                    //dicho botón invocará a la función de JavaScript deleteByKey que se encargará
                    //de llamar a la API para eliminar un turno
                    let deleteButton = '<button' +
                        ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                        ' type="button" onclick="deleteBy('+turno.id+')"' +
                        'class="btn btn-danger btn_delete">' +
                        '&times' +
                        '</button>';

                    //por cada turno creamos un botón que muestra el ID
                    //y que al hacerle clic invocará a la función de JavaScript findBy
                    //que se encargará de buscar el turno que queremos modificar
                    //y mostrar los datos del mismo en un formulario.
                    let updateButton = '<button' +
                        ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                        ' type="button" onclick="findBy('+turno.id+')"' +
                        ' class="btn btn-info btn_id">' +
                        turno.id +
                        '</button>';

                    //armamos cada columna de la fila
                    //como primera columna pondremos el botón modificar
                    //luego los datos de cada turno
                    //como última columna, el botón eliminar
                    turnoRow.innerHTML = '<td>' + updateButton + '</td>' +
                        '<td class=\"td_idpaciente\">' + turno.idPaciente.toUpperCase() + '</td>' +
                        '<td class=\"td_idodontologo\">' + turno.idOdontologo.toUpperCase() + '</td>' +
                        '<td class=\"td_fecha\">' + turno.date.toUpperCase() + '</td>' +
                        '<td>' + deleteButton + '</td>';
                };
            })
    })

    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/ListaTurnos.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })

})