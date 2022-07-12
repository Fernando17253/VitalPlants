package Planta;

import java.util.Vector;

public class GestionUsuarios {

	private Vector <Usuario> gestionusuarios=new Vector <Usuario>();
	
	public void menu() {
		int opcion=0;
		do {
			System.out.println("GESTIoN DE USUARIOS");
			System.out.println("---------UP----------");
			System.out.println("1. Agregar Usuario.");
			System.out.println("2. Eliminar Usuario.");
			System.out.println("3. Modificar Usuario.");
			System.out.println("4. Mostrar Usuarios.");
			System.out.println("0. Volver Al Menú Principal.");
			opcion=PedirDatos.leerEntero("Elija Una Opcion");
			switch (opcion) {
			case 1:
				addUsuario();
				break;
			case 2:
				delUsuario();
				break;
			case 3:
				setUsuario();
				break;
			case 4:
				mostrarUsuarios();
				break;
			case 0:
				System.out.println("Menu Principal...");
				System.out.println();
				break;
			default:
				System.out.println("Agrege Una  opción entre 0 y 4.");
				break;
			}			
		} while (opcion!=0);
	}

	private void addUsuario() {
		long codusuario=PedirDatos.leerLong("Ingrese El Codigo Del Usuario Que Sesea Agregar.");
		if (buscarUsuario(codusuario)!=-1) {
			System.out.println("NO Se Puede Ingresarr El Usuario Con El Codigo "+codusuario+" Porque Ya Existe.");
			return;
		}
		String nombre=PedirDatos.leerCadena("Ingrese El Nombre Del Usuario.");
		String apellido1=PedirDatos.leerCadena("Ingrese El Primer Apellido Del Usuario.");
		String apellido2=PedirDatos.leerCadena("Ingrese El Segundo Apellido Del Usuario.");
		Usuario u=new Usuario(codusuario, nombre, apellido1, apellido2);
		this.gestionusuarios.addElement(u);
		System.out.println("El Usuario Con El Codigo "+codusuario+" Ha Sido Agregado Correctamente.");
	}

	private void delUsuario() {
		if (this.gestionusuarios.isEmpty()) {
			System.out.println("No Se Puede Eliminar Usuarios Porque No Existe Ninguno.");
			return;
		}
		long codusuario=PedirDatos.leerLong("Ingrese El Codigo Del Usuario Que Desea Eliminar.");
		int pos=buscarUsuario(codusuario);
		if (pos==-1) {
			System.out.println("NO Se Puede Eliminar UsuarioS Con El Codigo "+codusuario+" Porque NO Existe.");
			return;
		}
		this.gestionusuarios.remove(pos);
		System.out.println("El Usuario Con El Codigo "+codusuario+" Ha Sido Eliminado Correctamente.");
	}

	private void setUsuario() {
		if (this.gestionusuarios.isEmpty()) {
			System.out.println("No Puede Eliminar Usuarios Porque NO Existe Ninguno.");
			return;
		}
		long codusuario=PedirDatos.leerLong("Ingrese El Codigo Del Usuario Que Desea Modificar.");
		int pos=buscarUsuario(codusuario);
		if (pos==-1) {
			System.out.println("NO Se Puede Modificar El Usuario Con El Codigo "+codusuario+" Porque NO Existe.");
			return;
		}
		System.out.println("Los Datos Del Usuario Con El Codigo "+codusuario+" Son:");
		System.out.println(this.gestionusuarios.elementAt(pos));
		String nombre=PedirDatos.leerCadena("Ingrese El Nuevo Nombre Del Usuario.");
		String apellido1=PedirDatos.leerCadena("Ingrese EL Primer Apellido Del Usuario.");
		String apellido2=PedirDatos.leerCadena("Ingrese El Segundo Apellido Del Usuario.");
		Usuario u=new Usuario(codusuario, nombre, apellido1, apellido2);
		this.gestionusuarios.add(pos, u);
		System.out.println("El Usuario Con El Codigo "+codusuario+" Ha Sdo Modificado Correctamente.");
	}

	private void mostrarUsuarios() {
		for (int i = 0; i < this.gestionusuarios.size(); i++) {
			System.out.println(this.gestionusuarios.elementAt(i));
			System.out.println("------------UP-------------");
		}
	}
	
	public int buscarUsuario(long codusuario) {
		for (int i = 0; i < this.gestionusuarios.size(); i++) {
			if (this.gestionusuarios.elementAt(i).getCodusuario()==codusuario) {
				return i;
			}
		}
		return -1;
	}
}