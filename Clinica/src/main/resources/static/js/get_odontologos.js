window.addEventListener("load", (event) => {
  console.log("page is fully loaded");


  const url = "/odontologos";
  const settings = { method: 'GET' }

       fetch(url,settings)
      .then(response => response.json())
      .then(data => {
         for (o of data) {
         var tabla = document.getElementById("tabla_odontologos")
         var row = tabla.insertRow();
         row.innerHTML = `<td> ${o.nombre} </td>`
         row.innerHTML += `<td> ${o.apellido} </td>`
         row.innerHTML += `<td> ${o.matricula} </td>`
         //console.log(o);
         }

      })

});

