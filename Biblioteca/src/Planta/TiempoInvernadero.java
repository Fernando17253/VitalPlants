package Planta;

import java.util.Vector;

public class TiempoInvernadero {
	private TiendaPlanta gl=new TiendaPlanta();
	private RegistroPlanta gr=new RegistroPlanta();
	private GestionUsuarios gu=new GestionUsuarios();
	
	private Vector <Prestamo> prestamos=new Vector<Prestamo>();
	
	public void menu() {
		int opcion=0;
		do {
			System.out.println("\n****Invernadero UP_Chiapas****");
                        System.out.println("****************************************");
			System.out.println("**--------------UP--------------------**");
			System.out.println("\n 1. Agregar Ubicacion."); //agregar libros
			System.out.println("2. Registrar Planta ."); //agregar revistas
			System.out.println("4. Agregar Usuarios."); //agregar usuario
			System.out.println("5. Realizar Compra.");  // realizar prestamo
			System.out.println("6. Devolver compra."); // devolver prestqamo
			System.out.println("7. Mostrar Plantas Vendidas."); // mostrar prestamos
			System.out.println("0. Salir.");
			opcion=PedirDatos.leerEntero("Elija Una Opcion Por Favor Familia Upchiapas");
			switch (opcion) {
			case 1:
				gestionLibros();
				break;
			case 2:
				gestionRevistas();
				break;
			case 4:
				gestionUsuarios();
				break;
			case 5:
				realizarPrestamo();
				break;
			case 6:
				devolverPrestamo();
				break;
			case 7:
				mostrarPrestamos();
				break;
			case 0:
				System.out.println("Adios, Bye Familia UpChiapas!");
				break;
			default:
				System.out.println("Ingrese Una Opción Entre 0 y 7.");
				break;
			}
		} while (opcion!=0);
	}

	private void gestionLibros() {
		gl.menu();
	}

	private void gestionRevistas() {
		gr.menu();
	}

	private void gestionUsuarios() {
		gu.menu();
	}
	
	public void realizarPrestamo() {
		long codusuario=PedirDatos.leerLong("Ingrese El COdigo Del Usuario Que Desea realizar la compra.");
		int pos=gu.buscarUsuario(codusuario);
		if (pos==-1) {
			System.out.println("El Usuario Con El COdigo "+codusuario+" No Existe.");
			return;
		}
		char tipomaterial=PedirDatos.leerCaracter("Introduzca LO Que Desea Hacer. (C = Comprar | D = Devolver");
		while (tipomaterial!='C'&&tipomaterial!='D'&&tipomaterial!='F') {
			tipomaterial=PedirDatos.leerCaracter("Valor Incorrecto. Ingrese lo Que desea hacer. (C = Comprar | D = Devolver ");			
		}
		long codmaterial=0;
		switch (tipomaterial) {
		case 'L':
			codmaterial=PedirDatos.leerLong("Ingrese El Codigo De la planta Que Desea comprar.");
			pos=gl.buscarUbicacion(codmaterial);
			if (pos==-1) {
				System.out.println("La planta Con El Codigo "+codmaterial+" NO existe.");
				return;
			}
			break;
		case 'R':
			codmaterial=PedirDatos.leerLong("Ingrese El COdigo De La planta Que Desea Comprar.");
			pos=gr.buscarRevista(codmaterial);
			if (pos==-1) {
				System.out.println("La Planta Con El COdigo "+codmaterial+" NO Existe.");
				return;
			}
			break;
		}
		if (buscarPrestamo(tipomaterial, codmaterial)!=-1) {
			System.out.println("La planta Que Desea comprar Se Encuentra vendido ");
			return;
		}
		String fechaprestamo=PedirDatos.leerCadena("Ingrese la fecha del venta (DD/MM/AAAA).");
		Prestamo p=new Prestamo(codusuario, tipomaterial, codmaterial, fechaprestamo);
		this.prestamos.addElement(p);
		System.out.println("La Compra Realizado Correctamente.");
	}
	
	public void devolverPrestamo() {
		if (this.prestamos.isEmpty()) {
			System.out.println("No Se Pueden devolver Compras, porque Aun No Se Ha Realizado Ninguno.");
			return;
		}
		long codusuario=PedirDatos.leerLong("Ingrese El Codigo De Usuario Que Realizo la compra.");
		char tipomaterial=PedirDatos.leerCaracter("Introduzca la planta Que Se compro.");
		long codmaterial=PedirDatos.leerLong("**Ingrese El Codigo Del Material Que Se Presto.**");
		int pos=buscarPrestamo(codusuario, tipomaterial, codmaterial);
		if (pos==-1) {
			System.out.println("NO Existe Ninguna compra Con Los Datos Ingresados.");
			return;
		}
		String fechadevolucion=PedirDatos.leerCadena("Ingrese La Fecha De Compra (DD/MM/AAAA).");
		this.prestamos.elementAt(pos).setFechadevolucion(fechadevolucion);
		System.out.println("Se Devolvio El Siguiente compra:\nCodigo De la planta: "+(pos+1)+"\n"+this.prestamos.elementAt(pos));
	}
	
	public void mostrarPrestamos() {
		for (int i = 0; i < this.prestamos.size(); i++) {
			System.out.println(this.prestamos.elementAt(i));
			System.out.println("**------------UP-------------**");
		}
	}
	
	//Método de búsqueda para los préstamos
	private int buscarPrestamo(char tipomaterial, long codmaterial) {
		for (int i = 0; i < this.prestamos.size(); i++) {
			if (tipomaterial==this.prestamos.elementAt(i).getCodmaterial()&&codmaterial==this.prestamos.elementAt(i).getCodmaterial()&&
					this.prestamos.elementAt(i).getFechadevolucion()==null) {
				return i;
			}
		}
		return -1;
	}
	
	//Método de búsqueda para las devoluciones
	private int buscarPrestamo(long codusuario, char tipomaterial, long codmaterial) {
		for (int i = 0; i < this.prestamos.size(); i++) {
			if (codusuario==this.prestamos.elementAt(i).getCodusuario()&&tipomaterial==this.prestamos.elementAt(i).getCodmaterial()&&
					codmaterial==this.prestamos.elementAt(i).getCodmaterial()&&this.prestamos.elementAt(i).getFechadevolucion()==null) {
				return i;
			}
		}
		return -1;
	}
}
