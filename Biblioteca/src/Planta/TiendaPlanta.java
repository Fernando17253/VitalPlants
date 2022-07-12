package Planta;

import java.util.Vector;

public class TiendaPlanta {

	private Vector <Libro> gestionlibros=new Vector<Libro>();
	
	public void menu() {
		int opcion=0;
		do {
			System.out.println("**GESTIoN DE UBICACION**");
			System.out.println("--------UP---------");
			System.out.println("1. Donde Se Encuentra.");
			System.out.println("2. Eliminar Ubicacion.");
			System.out.println("3. Modificar libro.");
			System.out.println("4. Mostrar Donde sE Encuentra.");
			System.out.println("0. Volver al menu principal.");
			opcion=PedirDatos.leerEntero("Elija Una Opcion");
			switch (opcion) {
			case 1:
				addUbicacion();
				break;
			case 2:
				delUbicacion01();
				break;
			case 3:
				setUbicacion02();
				break;
			case 4:
				mostrarUbicacion();
				break;
			case 0:
				System.out.println("menu principal...");
				System.out.println();
				break;
			default:
				System.out.println("Ingresar una opcion entre 0 y 4.");
				break;
			}			
		} while (opcion!=0);
	}

	private void addUbicacion() {
		
		long isbn=PedirDatos.leerLong("Ingrese El Kilometro en que se encuetra.");
		if (buscarUbicacion(isbn)!=-1) {
			System.out.println("NO Se Puede agregar El kilometro "+isbn+" Porque ya Existe.");
			return;
		}
		//String signatura=PedirDatos.leerCadena("¿Desea modificar la signatura?");
		
		String signatura=PedirDatos.leerCadena("Ingrese su Ubicacion.");
		String titulo=PedirDatos.leerCadena("Ingrese a donde quiere llegar.");
		String autor=PedirDatos.leerCadena("Ingrese su nombre.");
		String materia=PedirDatos.leerCadena("Ingrese la ingrese el la CD donde se encuentra ahora.");
		String editorial=PedirDatos.leerCadena("Ingrese el estado.");
		Libro l=new Libro(isbn, signatura, titulo, autor, materia, editorial);
		this.gestionlibros.addElement(l);
		System.out.println("la ubicacion donde se encuentra "+isbn+" Se ha Creado Correctamente.");
	}

	private void delUbicacion01() {
		if (this.gestionlibros.isEmpty()) {
			System.out.println("No puede eliminar su ubicacion, porque no existe ninguno.");
			return;
		}
		long isbn=PedirDatos.leerLong("......."); //Ingrese el Codigo del libro que desea eliminar
		int pos=buscarUbicacion(isbn);
		if (pos==-1) {
			System.out.println("No se puede eliminar el lugar indicado"+pos+" porque no existe.");
			return;
		}
		this.gestionlibros.remove(pos);
		System.out.println("la ubicacion  "+isbn+" Ha Sido Eliminado correctamente.");
	}

	private void setUbicacion02() {
		if (this.gestionlibros.isEmpty()) {
			System.out.println("No puede eliminar la ubicacion porque no existe ninguno.");
			return;
		}
		long isbn=PedirDatos.leerLong("Ingrese el Codigo de la planta que Desea modificar.");
		int pos=buscarUbicacion(isbn);
		if (pos==-1) {
			System.out.println("NO se puede modificar la planta con el codigo "+isbn+" porque NO existe.");
			return;
		}
		System.out.println("Los Datos de la planta con el codigo "+isbn+" son:");
		System.out.println(this.gestionlibros.elementAt(pos));
		String signatura=PedirDatos.leerCadena("Ingrese su ubicacion.");
		String titulo=PedirDatos.leerCadena("Ingrese a donde quieres llegar.");
		String autor=PedirDatos.leerCadena("Ingrese su nombre.");
		String materia=PedirDatos.leerCadena("Ingrese la ciudad donde se encuentra.");
		String editorial=PedirDatos.leerCadena("Ingrese el estado.");
		Libro l=new Libro(isbn, signatura, titulo, autor, materia, editorial);
		this.gestionlibros.add(pos, l);
		System.out.println("La ubicacion "+isbn+" Ha sido modificado correctamente.");
	}

	private void mostrarUbicacion() {
		for (int i = 0; i < this.gestionlibros.size(); i++) {
			System.out.println(this.gestionlibros.elementAt(i));
			System.out.println("------------UP-------------");
		}
	}
	
	public int buscarUbicacion(long isbn) {
		for (int i = 0; i < this.gestionlibros.size(); i++) {
			if (this.gestionlibros.elementAt(i).getISBN()==isbn) {
				return i;
			}
		}
		return -1;
	}
}