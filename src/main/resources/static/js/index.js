	
	// Funcion sacada de la libreria de database para realizar paginaciones y filtrado de datos
	$(document).ready(function () {
		$('#userList').dataTable( {
  "lengthMenu": [ [5, 10, 25, 50, -1], [5, 10, 25, 50, "All"] ],
  "language":idioma_espanol
} );
        });

               //funciones para mostar los enlaces del silder
          $("#demo").mouseenter(function () {   
         $("#e1").fadeIn(1000);
           $("#d1").fadeIn(1000);
    })

                 //funciones para ocultar los enlaces del slider
          $("#demo").mouseleave(function () {
       $("#e1").fadeOut(1000);
           $("#d1").fadeOut(1000);
    })

             $("#demo").mouseenter(function () {
       $("#e2").fadeIn(1000);
           $("#d2").fadeIn(1000);
    })

            $("#demo").mouseleave(function () {
      ("#e2").fadeOut(1000);
           $("#d2").fadeOut(1000);
    })

             $("#demo").mouseenter(function () {
       $("#e3").fadeIn(1000);
           $("#d3").fadeIn(1000);
    })

            $("#demo").mouseleave(function () {
       ("#e3").fadeOut(1000);
           $("#d3").fadeOut(1000);
    })

  
   

        //funciones para cerrar el modal
        
          $("#modalmio").click(function () {
        $("#springSecurity").modal('hide');
    })
            $("#modalmio2").click(function () {
        $("#myGitHub").modal('hide');
    })

        //funciones para mostrar los modales
            function modalSpringSecurity() {
      $('#springSecurity').modal('show');
    }

      function gitHub() {
      $('#myGitHub').modal('show');
    }


//funcion para modal sobre mi

function modalSobreMi(){
    $("#sobreMi").modal('show');
}


//esta variable es para cambiar el idima al español	de la libreria de datatable
		var idioma_espanol = {
    "sProcessing":     "Procesando...",
                "sLengthMenu":     "Mostrar _MENU_ registros",
                "sZeroRecords":    "No se encontraron resultados",
                "sEmptyTable":     "Ningún dato disponible en esta tabla =(",
                "sInfo":           " registros del _START_ al _END_ de _TOTAL_ ",
                "sInfoEmpty":      "registros del 0 al 0 de un total de 0 ",
                "sInfoFiltered":   "(filtrado de  de _MAX_ registros)",
                "sInfoPostFix":    "",
                "sSearch":         "Buscar:",
                "sUrl":            "",
                "sInfoThousands":  ",",
                "sLoadingRecords": "Cargando...",
                "oPaginate": {
                    "sFirst":    "Primero",
                    "sLast":     "Último",
                    "sNext":     "Siguiente",
                    "sPrevious": "Anterior"
                },
                "oAria": {
                    "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
                    "sSortDescending": ": Activar para ordenar la columna de manera descendente"
                },
                "buttons": {
                    "copy": "Copiar",
                    "colvis": "Visibilidad"
                }
}