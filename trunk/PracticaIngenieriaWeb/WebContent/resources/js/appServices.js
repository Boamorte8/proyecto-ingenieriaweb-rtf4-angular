/**
 * servicios de angular
 */
/**
 * url de los servicios web empleados
 */
var URL_SERVICIO_PRESTAMO_SOLICITUDES = 'http://localhost:8080/PracticaIngenieriaWeb/rest/Prestamo/Solicitudes';
var URL_SERVICIO_PRESTAMO_SOLICITAR = 'http://localhost:8080/PracticaIngenieriaWeb/rest/Prestamo/Solicitar';
var URL_SERVICIO_PRESTAMO_APROBAR = 'http://localhost:8080/PracticaIngenieriaWeb/rest/Prestamo/Aprobar';
var URL_SERVICIO_PRESTAMO_RECHAZAR = 'http://localhost:8080/PracticaIngenieriaWeb/rest/Prestamo/Rechazar';
var URL_SERVICIO_PRESTAMO_USUARIO = 'http://localhost:8080/PracticaIngenieriaWeb/rest/Prestamo/Usuario';

var URL_SERVICIO_USUARIO_AUTENTICAR = 'http://localhost:8080/PracticaIngenieriaWeb/rest/Usuario/Autenticar';

var URL_SERVICIO_DISPOSITIVO_DISPONIBLES = 'http://localhost:8080/PracticaIngenieriaWeb/rest/Dispositivo/Disponibles';

var URL_SERVICIO_TIPODISPOSITIVO_LISTAR = 'http://localhost:8080/PracticaIngenieriaWeb/rest/TipoDispositivo';
/**
 * servicio web Prestamo con sus operaciones
 */
appPrestamos.service('Prestamo', function($http) {
	/**
	 * funcion de enlace al servicio web listarSolicitudes
	 */
	this.listarSolicitudes = function() {
		return $http({
			method : 'GET',
			url : URL_SERVICIO_PRESTAMO_SOLICITUDES
		});
	};
	/**
	 * funcion de enlace al servicio web listar prestamos de un usuario 
	 * identificado con tipo y numero de documento
	 */
	this.listarPrestamosUsuario = function(tipoDoc, numDoc) {
		return $http({
			method : 'GET',
			url : URL_SERVICIO_PRESTAMO_USUARIO,
			params : {
				tipoDocumento : tipoDoc,
				numeroDocumento : numDoc
			}
		});
	};
	/**
	 * funcion de enlace al servicio solicitar prestamo con los parametros necesarios
	 */
	this.solicitarPrestamo = function(tipoDoc, numDoc, disp, fecha, horaI, horaF) { 
		return $http({ 
			method : 'POST',
			url : URL_SERVICIO_PRESTAMO_SOLICITAR,
			params : {
				tipoDocumento : tipoDoc,
				numeroDocumento : numDoc,
				dispositivo : disp,
				fecha : fecha,
				horaI : horaI,
				horaF : horaF
			}
		}); 
	};
	/**
	 * funcion de enlace al servicio aprobar prestamo 
	 * con los parametros prestamos para identificar el prestamo
	 * , tipo y numero de documento para saber quien aprobo el prestamo
	 */
	this.aprobarPrestamo = function(tipoDoc, numDoc, prestamo) {
		return $http({
			method : 'PUT',
			url : URL_SERVICIO_PRESTAMO_APROBAR,
			params : {
				tipoDocumento : tipoDoc,
				numeroDocumento : numDoc,
				prestamo : prestamo
			}
		});
	};
	/**
	 * funcion de enlace al servicio rechazar prestamo 
	 * con los parametros prestamos para identificar el prestamo
	 * , tipo y numero de documento para saber quien aprobo el prestamo
	 */
	this.rechazarPrestamo = function(tipoDoc, numDoc, prestamo) {
		return $http({
			method : 'PUT',
			url : URL_SERVICIO_PRESTAMO_RECHAZAR,
			params : {
				tipoDocumento : tipoDoc,
				numeroDocumento : numDoc,
				prestamo : prestamo
			}
		});
	};
});
/**
 * servicio web Usuario con sus funciones
 */
appPrestamos.service('Usuario', function($http) {
	/**
	 * funcion de enlace al servicio web autenticar para validar
	 * los datos de acceso de un usuario
	 */
	this.autenticar = function(tipoDoc, numDoc, password) {
		if (tipoDoc == '' | numDoc == '' | password == '') {
			return null;
		}
		return $http({
			method : 'POST',
			url : URL_SERVICIO_USUARIO_AUTENTICAR,
			params : {
				tipoDocumento : tipoDoc,
				numeroDocumento : numDoc,
				password : password
			}
		});
	};

});
/**
 * servicio web Dispositivo y sus funciones
 */
appPrestamos.service('Dispositivo', function($http) {
	/**
	 * funcion de enlace al servicio listar disponibles con los 
	 * parametros fecha rango de horas y tipo de dispositivo
	 */
	this.listarDisponibles = function(fecha, horaI, horaF, tipo) {
		return $http({
			method : 'GET',
			url : URL_SERVICIO_DISPOSITIVO_DISPONIBLES,
			params : {
				fecha : fecha,
				horaInicial : horaI,
				horaFinal : horaF,
				tipo : tipo
			}
		});
	};

});
/**
 * servicio tipo dispositivo 
 */
appPrestamos.service('TipoDispositivo', function($http) {
	/**
	 * funcion de enlace al servicio listar
	 */
	this.listar = function() {
		return $http({
			method : 'GET',
			url : URL_SERVICIO_TIPODISPOSITIVO_LISTAR
		});
	};

});