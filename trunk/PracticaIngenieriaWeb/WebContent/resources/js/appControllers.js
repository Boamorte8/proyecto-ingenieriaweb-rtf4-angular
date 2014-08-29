/**
 * Controladores de angular
 */
/**
 * Controlador de angular para las solicitudes
 */
appPrestamos.controller('solicitudesController', function($cookies, $scope, $location, Prestamo) {
	/**
	 * llama el servicio web listar solicitudes
	 */
	Prestamo.listarSolicitudes().success(function(data) {
		$scope.solicitudes = data;
	});
	/**
	 * llama el servicio aprobar prestamo pasando los parametros necesarios
	 * tipoDoc y numDoc para identificar el encargado
	 * codigoPrestamo para identificar el prestamo a aprobar
	 */
	$scope.aprobarPrestamo = function(){
		Prestamo.aprobarPrestamo($cookies.tipoDoc, $cookies.numDoc, $scope.codigoPrestamo).success(function(data) {
			alert(data);
			location.reload();
		});
	};
	/**
	 * llama el servicio aprobar prestamo pasando los parametros necesarios
	 * tipoDoc y numDoc para identificar el encargado
	 * codigoPrestamo para identificar el prestamo a rechazar
	 */
	$scope.rechazarPrestamo = function(){
		Prestamo.rechazarPrestamo($cookies.tipoDoc, $cookies.numDoc, $scope.codigoPrestamo).success(function(data) {
			alert(data);
			location.reload();
		});
	};
	
});

/**
 * Controlador de angular para el logueo
 */
appPrestamos.controller('loginController', function($scope, autenticar, Usuario) {
	/**
	 * funcion que cifra el password y llama al servicio web autenticar
	 * parando los parametros que identifican un usuario
	 */
	$scope.login = function() {
		var password = CryptoJS.SHA256($scope.password);
		Usuario.autenticar($scope.tipoDoc, $scope.numDoc, password.toString()).success(
						function(data) {
							if (data == 'false') {
								alert("Datos de Acceso Incorrectos");
							}
							if (data == 'inac') {
								alert("Usuario Inactivo");
							}
							if (data != 'admin' && data != 'user') {
								$scope.tipoDoc = '';
								$scope.numDoc = '';
								$scope.password = '';
								return;
							}else{
								autenticar.login($scope.tipoDoc, $scope.numDoc, data);
							}
						});
	};
});

/**
 * Controlador de angular para los dispositivos disponibles
 */
appPrestamos.controller('disponiblesController', function($scope, $location, Dispositivo, TipoDispositivo) {
	/**
	 * llamada al servicio web listar tipos de dispositivo
	 * para mostrarlos en un select y asi buscar dispositivos disponibles
	 * por tipo
	 */
	TipoDispositivo.listar().success(function(data) {
		$scope.tipos = data;
		$location.url('/disponibles');
	});
	/**
	 * funcion que llama el servicio web para consultar los dispositivos
	 * disponibles para una fecha en un rango de horas.
	 */
	$scope.listarDisponibles = function(){
		Dispositivo.listarDisponibles($scope.fecha, $scope.horaI, $scope.horaF, $scope.tipo).success(function(data) {
			$scope.dispositivosDisponibles = data;
			if(data[0] == 'undefined'){
				alert("no hay dispositivos disponibles para seleccionar");
			}
			$location.url('/disponibles');
		});
	};
	
});

/**
 * Controlador de angular para la solicitud de un prestamo
 */
appPrestamos.controller('solicitudController', function($cookies, $scope, $location, Dispositivo, TipoDispositivo, Prestamo) {
	/**
	 * llamada al servicio web listar tipos de dispositivo
	 * para mostrarlos en un select y asi buscar dispositivos disponibles
	 * por tipo
	 */
	TipoDispositivo.listar().success(function(data) {
		$scope.tipos = data;
		$location.url('/solicitud');
	});
	/**
	 * funcion que llama el servicio web para consultar los dispositivos
	 * disponibles para una fecha en un rango de horas.
	 */
	$scope.listarDisponibles = function(){
		Dispositivo.listarDisponibles($scope.fecha, $scope.horaI, $scope.horaF, $scope.tipo).success(function(data) {
			$scope.dispositivosDisponibles = data;
			if(data[0] == 'undefined'){
				alert("no hay dispositivos disponibles para seleccionar");
			}
			$location.url('/solicitud');
		});
	};
	/**
	 * funcion que llama el servicio web para realizar la solicitud de prestamo
	 */
	$scope.solicitarPrestamo = function(){
		Prestamo.solicitarPrestamo($cookies.tipoDoc, $cookies.numDoc, $scope.disposit, $scope.fecha, $scope.horaI, $scope.horaF).success(function(data) {
			alert(data);
			$location.url('/prestamos');
		});
	};
	/**
	 * metodo para recargar la pagina al responder una solicitud
	 */
	$scope.recargar = function(){
		location.reload(true);
	};
	
});

/**
 * Controlador de angular para la lista de prestamos de un usuario
 */
appPrestamos.controller('prestamosController', function($cookies, $scope, Prestamo) {
	/**
	 * llamada al servicio web listar prestamos por usuario para asi
	 * mostrarlos
	 */
	Prestamo.listarPrestamosUsuario($cookies.tipoDoc, $cookies.numDoc).success(function(data) {
		$scope.prestamos = data;
	});

});

/**
 * Controlador de angular para la cabecera de las paginas segun el tipo de usuario
 */
appPrestamos.controller('headerController', function($location, $cookies, $scope, autenticar) {
	$scope.tipoUsuario = function(){
		return $cookies.tipoUsuario;
	};
	/**
	 * funcion para cerrar sesion
	 * se eliminan las cookies
	 */
	$scope.cerrarSesion = function(){
		autenticar.cerrarSesion();
	};
	/**
	 * metodo que retorna la clase de la barra del header 
	 * para mejorar la interfaz grafica
	 */
	$scope.getClass = function(quien) {
		if(quien == $location.path()){
			return 'active';
		}
		return '';
	};
});
