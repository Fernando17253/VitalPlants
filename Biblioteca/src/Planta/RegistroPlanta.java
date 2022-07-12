package Planta;

import java.util.Vector;

public class RegistroPlanta {
	
	private Vector <Revista> gestionrevistas=new Vector <Revista>();
	
	public void menu() {
		int opcion=0;
		do {
			System.out.println("GESTIÓN DE PLANTA");
			System.out.println("----------UP---------");
			System.out.println("1. Registrar Planta.");
			System.out.println("2. Eliminar planta.");
			//System.out.println("3. Modificar planta.");
			System.out.println("4. Mostrar plantas Registradas.");
			System.out.println("5. Gestión de artículos.");
			System.out.println("0. Volver al menú principal.");
			opcion=PedirDatos.leerEntero("Elija una opcion");
			switch (opcion) {
			case 1:
				addRevista();
				break;
			case 2:
				delRevista();
				break;
			case 3:
				setRevista();
				break;
			case 4:
				mostrarRevistas();
				break;
			case 5:
				gestionarArticulos();
			case 0:
				System.out.println("Menu principal...");
				System.out.println();
				break;
			default:
				System.out.println("Ingresar Una Opcion Entre 0 y 5.");
				break;
			}
		} while (opcion!=0);
	}

	private void addRevista() {

		long codrevista=PedirDatos.leerLong("Ingrese el codigo(num) de la Planta que desea Agregar.");
		if (buscarRevista(codrevista)!=-1) {
			System.out.println("No puede agregar la Planta con el codigo "+codrevista+" porque ya Existe.");
			return;
		}
		String signatura=PedirDatos.leerCadena("..");
		String nombre=PedirDatos.leerCadena("Ingrese el nombre de la Planta.");
		String materia=PedirDatos.leerCadena("Ingrese el lugar donde se encuentra la planta.");
		Revista r=new Revista(codrevista, signatura, nombre, materia);
		this.gestionrevistas.addElement(r);
		System.out.println("La Planta con el codigo "+codrevista+" se ha añadido correctamente.");
	}

	private void delRevista() {
		if (this.gestionrevistas.isEmpty()) {
			System.out.println("No puede eliminar La planta porque no Existe ninguna.");
			return;
		}
		long codrevista=PedirDatos.leerLong("Ingrese el codigo de la planta que desea Eliminar.");
		int pos=buscarRevista(codrevista);
		if (pos==-1) {
			System.out.println("No se puede eliminar la planta con el codigo "+codrevista+" porque NO existe.");
			return;
		}
		this.gestionrevistas.remove(pos);
		System.out.println("La planta con el codigo "+codrevista+" Se elimino correctamente.");
	}

	private void setRevista() {
		if (this.gestionrevistas.isEmpty()) {
			System.out.println("No puede eliminar la planta porque no existe ninguna.");
			return;
		}
		long codrevista=PedirDatos.leerLong("Ingrese el codigo de la planta que desea modificar.");
		int pos=buscarRevista(codrevista);
		if (pos==-1) {
			System.out.println("No se puede modificar la plata con el codigo "+codrevista+" porque NO existe.");
			return;
		}
		System.out.println("Los datos de la planta con el codigo "+codrevista+" son:");
		System.out.println(this.gestionrevistas.elementAt(pos));
		String signatura=PedirDatos.leerCadena("...");
		String nombre=PedirDatos.leerCadena("Ingrese el nombre de la planta.");
		String materia=PedirDatos.leerCadena("Ingrese el lugar donde se encuentra la planta.");
		Revista r=new Revista(codrevista, signatura, nombre, materia);
		this.gestionrevistas.add(pos, r);
		System.out.println("La planta con el Codigo "+codrevista+" ha sido modificada correctamente.");
	}

	private void mostrarRevistas() {
		for (int i = 0; i < this.gestionrevistas.size(); i++) {
			System.out.println(this.gestionrevistas.elementAt(i));
			System.out.println("------------UP-------------");
		}
	}
	
	public void gestionarArticulos() {
		long codrevista=PedirDatos.leerLong("InGRESE el codigo de la planta que desea Agregar.");
		int pos=buscarRevista(codrevista);
		if (pos==-1) {
			System.out.println("No existe ninguna planta con el Codigo "+codrevista+".");
			return;
		}
		this.gestionrevistas.elementAt(pos).gestionArticulos();
	}
	
	public int buscarRevista(long codrevista) {
		for (int i = 0; i < this.gestionrevistas.size(); i++) {
			if (this.gestionrevistas.elementAt(i).getCodrevista()==codrevista) {
				return i;
			}
		}
		return -1;
	}
}
