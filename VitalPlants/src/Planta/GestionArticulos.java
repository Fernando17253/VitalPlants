package Planta;

import java.util.Vector;

public class GestionArticulos {
	private Vector <Articulo> gestionarticulos=new Vector <Articulo>();
	
	public void menu(String nombre) {
		int opcion=0;
		do {
			System.out.println("ARTiCULOS DE LA REVISTA "+nombre.toUpperCase());
			System.out.println("-------------------UP-------------------");
			System.out.println("1. Añadir Articulo.");
			System.out.println("2. Eliminar Artículo.");
			System.out.println("3. Modificar Artículo.");
			System.out.println("4. Mostrar Artículos.");
			System.out.println("0. Volver Al menú Principal.");
			opcion=PedirDatos.leerEntero("¿Qué desea hacer?");
			switch (opcion) {
			case 1:
				addArticulo();
				break;
			case 2:
				delArticulo();
				break;
			case 3:
				setArticulo();
				break;
			case 4:
				mostrarArticulo();
				break;
			case 0:
				System.out.println("MenU Principal...");
				System.out.println();
				break;
			default:
				System.out.println("Debe Introducir Una Opción Entre 0 y 4.");
				break;
			}
		} while (opcion!=0);
	}

	private void addArticulo() {
		long codarticulo=PedirDatos.leerLong("Ingrese El Codigo Del Artículo Que Desea Añadir.");
		if (buscarArticulo(codarticulo)!=-1) {
			System.out.println("NO Se Puede Añadir El Artículo Con El Codigo "+codarticulo+" Porque Ya Existe.");
			return;			
		}
		String titulo=PedirDatos.leerCadena("Ingrese El Título Del Artículo");
		String autor=PedirDatos.leerCadena("Ingrese El Autor Del Artículo");
		int numpaginas=PedirDatos.leerEntero("Ingrese El Numero De Paginas Del Artículo");
		Articulo a=new Articulo(codarticulo, titulo, autor, numpaginas);
		this.gestionarticulos.addElement(a);
		System.out.println("El Articulo Con El Codigo "+codarticulo+" Se Integro Correctamente.");
	}

	private void delArticulo() {
		if (this.gestionarticulos.isEmpty()) {
			System.out.println("NO Puede Eliminarse El Artículos Porque NO Existe Ninguno.");
			return;
		}
		long codarticulo=PedirDatos.leerLong("Ingrese El Codigo Del Artículo Que Desea Eliminar.");
		int pos=buscarArticulo(codarticulo);
		if (pos==-1) {
			System.out.println("No Puede Eliminarse El Artículo Con El CodigoS "+codarticulo+" Porque NO Existe.");
			return;
		}
		this.gestionarticulos.remove(pos);
		System.out.println("El Articulo Con El Codigo "+codarticulo+" Ha Sido Eliminado Correctamente");
	}

	private void setArticulo() {
		if (this.gestionarticulos.isEmpty()) {
			System.out.println("No Puede Modificarse El Artículos porque No Existe Ninguno.");
			return;
		}
		long codarticulo=PedirDatos.leerLong("Introduzca el código del artículo que desea modificar.");
		int pos=buscarArticulo(codarticulo);
		if (pos==-1) {
			System.out.println("No se puede modificar el artículo con el código "+codarticulo+" porque no existe.");
			return;
		}
		System.out.println("Los Datos Del Articulo Con El Codigo "+codarticulo+" son:");
		System.out.println(this.gestionarticulos.elementAt(pos));
		String titulo=PedirDatos.leerCadena("Ingrese El Título Del Artículo");
		String autor=PedirDatos.leerCadena("Ingrese El Autor Del Artículo");
		int numpaginas=PedirDatos.leerEntero("Ingrese Los Numero De Paginas Del Artículo");
		Articulo a=new Articulo (codarticulo, titulo, autor, numpaginas);
		this.gestionarticulos.add(pos, a);
		System.out.println("El Artículo Con El Codigo "+codarticulo+" Ha Sido Modificado Correctamente.");		
	}

	private void mostrarArticulo() {
		for (int i = 0; i < this.gestionarticulos.size(); i++) {
			System.out.println(this.gestionarticulos.elementAt(i));
			System.out.println("------------UP-------------");
		}
	}
	
	private int buscarArticulo(long codarticulo) {
		for (int i = 0; i < this.gestionarticulos.size(); i++) {
			if (this.gestionarticulos.elementAt(i).getCodarticulo()==codarticulo) {
				return i;
			}
		}
		return -1;
	}
	
	public String toString() {
		String ret="";
		for (int  i= 0; i < this.gestionarticulos.size(); i++) {
			ret+=this.gestionarticulos.elementAt(i)+"\n-------------UP------------\n";
		}
		return ret;
	}
}
