/**
 * Modulos de angular
 */
/**
 * modulo principal Prestamos
 */
var appPrestamos = angular.module('Prestamos', [ 'ngRoute', 'ngCookies' ]);
/**
 * control de sesiones 
 * login
 * validar estado
 * cerrar sesion
 */
appPrestamos.factory('autenticar', function($cookies, $location) {
	return {
		login : function(tipoDoc, numDoc, tipoUsuario) {
			$cookies.tipoDoc = tipoDoc,
			$cookies.numDoc = numDoc,
			$cookies.tipoUsuario = tipoUsuario;
			$location.url('/');
		},
		validarEstado : function() {
			if (typeof ($cookies.tipoUsuario) == 'undefined') {
				$location.url('/login');
			}
			if (typeof ($cookies.tipoUsuario) != 'undefined' && $location.url() == '/login') {
				$location.url('/');
			}
		},
		cerrarSesion : function() {
			delete $cookies['tipoDoc'],
			delete $cookies['numDoc'],
			delete $cookies['tipoUsuario'],
			this.validarEstado();
		}
	};
});
/**
 * configuracion de los templates de la aplicacion
 */
appPrestamos.config([ '$routeProvider', function($routeProvider) {
	
	$routeProvider.when('/solicitudes', {
		templateUrl : 'solicitudes.html',
		controller : 'solicitudesController'
	});

	$routeProvider.when('/', {
		templateUrl : 'inicio.html'
	});

	$routeProvider.when('/solicitud', {
		templateUrl : 'solicitud.html',
		controller : 'solicitudController'
	});
	
	$routeProvider.when('/disponibles', {
		templateUrl : 'disponibles.html',
		controller : 'disponiblesController'
	});
	
	$routeProvider.when('/prestamos', {
		templateUrl : 'prestamos.html',
		controller : 'prestamosController'
	});
	
	$routeProvider.when('/login', {
		templateUrl : 'login.html',
		controller : 'loginController'
	});
	
} ]);
/**
 * al iniciar la aplicacion se valida el estado del usuario
 */
appPrestamos.run(function($rootScope, autenticar) {
	$rootScope.$on('$routeChangeStart', function() {
		autenticar.validarEstado();
	});
});
/**
 * filtro para convertir las fechas de numero long producido por JSON a formato de usuario
 */
appPrestamos.filter("fecha",function(){
	return function(fecha) {
		fecha = new Date(fecha);
		var date = fecha.getFullYear()+'/';
		if(fecha.getMonth() < 9){
			date += '0';
		}
		date += (fecha.getMonth()+1)+'/';
		if(fecha.getDate() < 10){
			date += '0';
		}
		date += fecha.getDate()+' ';
		if((fecha.getHours() % 12) == 0){
			date += '12:';
		}else {
			if(0 < (fecha.getHours() % 12) && (fecha.getHours() % 12) < 10){
				date += '0';
			}
			date += (fecha.getHours() % 12)+':';
		}
		if(fecha.getMinutes() < 10){
			date += '0';
		}
		date += fecha.getMinutes()+':';
		if(fecha.getSeconds() < 10){
			date += '0';
		}
		date += fecha.getSeconds()+' ';
		if(fecha.getHours() < 12){
			date += 'a.m';
		}else{
			date += 'p.m';
		}
		return date;
	};
});
