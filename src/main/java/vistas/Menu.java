package vistas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import modelo.CategoriaEnum;
import modelo.Cliente;
import servicio.ArchivoServicio;
import servicio.ClienteServicio;
import servicio.Exportador;
import servicio.ExportarCsv;
import servicio.ExportarTxt;
import utilidad.Utilidad;

public class Menu implements MenuBase {

	ClienteServicio clienteServicio;
	ArchivoServicio archivoServicio;
	Exportador exportadorCsv;
	Exportador exportarTxt;
	Utilidad utilidad;
	private String fileName = "clientes"; // para exportar el archivo
	private String fileName1 = "DBClientes.csv"; // para importar el archivo
	private int op = 0;
	private String ruta = "";
	Scanner sc;

	public Menu() {
		super();
		sc = new Scanner(System.in);
		clienteServicio = new ClienteServicio();
		archivoServicio = new ArchivoServicio();
		exportadorCsv = new ExportarCsv();
		exportarTxt = new ExportarTxt();
		utilidad = new Utilidad();
	}

	public void listarClientes() {
		if (clienteServicio.getListaClientes().size() == 0) {
			System.out.println("No hay clientes en el listado");
		} else {
			clienteServicio.listarClientes();
		}
	}

	public void agregarCliente() {
		System.out.println("Ingrese RUN del Cliente:");
		String runCliente = sc.next();
		System.out.println("Ingrese Nombre del Cliente:");
		sc.nextLine();
		String nombreCliente = sc.nextLine();
		System.out.println("Ingrese Apellido del Cliente:");
		String apellidoCliente = sc.nextLine();
		System.out.println("Ingrese años como Cliente:");
		int aniosCliente = sc.nextInt();
		Cliente cl = new Cliente(runCliente, nombreCliente, apellidoCliente, aniosCliente, CategoriaEnum.ACTIVO);
		clienteServicio.agregarCliente(cl);
	}

	public void editarCliente() {
		Cliente cl = new Cliente();
		String runedit = "";
		System.out.printf(
				"Seleccione qué desea hacer:\n1. Cambiar el estado del Cliente\n2. Editar los datos ingresados del Cliente\n\nIngrese opcion:");
		op = sc.nextInt();
		try {
			switch (op) {
			case 1:// Editar ENUM
				System.out.println("Ingrese el run del cliente a editar:");
				runedit = sc.next();
				System.out.println("-----Actualizando estado del Cliente-----");
				cl = clienteServicio.buscarCliente(runedit);
				if (cl != null) {
					System.out.printf("El estado actual es: %s\n", cl.getOnOff());
					System.out.printf(
							"\n1. Si desea cambiar el estado del Cliente a Inactivo\n2. Si desea mantener el estado del cliente Activo\n\nIngrese opcion:\n");
					System.out.println("----------------------------------------");
					op = sc.nextInt();
					editarOnOff(cl);
				}
				break;
			case 2:// Editar datos
				System.out.println("Ingrese el run del cliente a editar:");
				runedit = sc.next();
				cl = clienteServicio.buscarCliente(runedit);
				if (cl != null) {
					System.out.println("----Actualizando datos del Cliente-----");
					System.out.printf(
							"1. El RUN del Cliente es: %s\n2. El Nombre del Cliente es: %s\n3. El Apellido del Cliente es: %s\n4. Los años como Cliente son: %d\n\n",
							cl.getRunCliente(), cl.getNombreCliente(), cl.getApellidoCliente(), cl.getAniosCliente());
					System.out.println("Ingrese la opción a editar de los datos del cliente:\n");
					System.out.println("----------------------------------------\n");
					op = sc.nextInt();
					editarDatos(cl);
				} else {
					System.out.println("El cliente no existe.");
				}

				break;
			default:
				System.out.println("Opción no válida. Intente nuevamente.");
				break;
			}
		} catch (InputMismatchException e) {
			e.getMessage();
		}
	}

	public void cargarDatos() throws FileNotFoundException {
		System.out.println("--------- Cargar Datos ---------");
		System.out.println("Ingrese la ruta en donde se encuentra el archivo DBClientes.csv:");
		System.out.println(System.getProperty("user.home"));
		System.out.println("-----------------------------------------------");
		ruta = sc.next();
		for (Cliente cl : archivoServicio.cargarDatos(ruta, fileName1)) {
			clienteServicio.agregarCliente(cl);
		}
		System.out.println(clienteServicio.getListaClientes());

	}

	public void exportarDatos() {
		System.out.println("---------Exportar Datos-----------");
		System.out.printf("Seleccione el formato a exportar:\n1. Formato csv\n2. Formato txt\n\n");
		System.out.printf("Ingrese una opción para exportar:\n----------------------------------\n\r");
		op = sc.nextInt();
		System.out.println("Ingrese una ruta para exportar la información: ");
		System.out.println(System.getProperty("user.home"));
		System.out.println("-----------------------------------------------");
		ruta = sc.next();
		archivoServicio.exportar(ruta, clienteServicio.getListaClientes(), fileName, op);
		try {
			switch (op) {
			case 1:
				exportadorCsv.exportar(ruta, clienteServicio.getListaClientes(), fileName);
				System.out.println("Datos de clientes exportados correctamente en formato csv");
				break;
			case 2:
				exportarTxt.exportar(ruta, clienteServicio.getListaClientes(), fileName);
				System.out.println("Datos de clientes exportados correctamente en formato txt");
				break;
			default:
				System.out.println("Ingrese una opción válida");
			}
		} catch (InputMismatchException | IOException e) {
			System.out.println("No se pudo exportar");
			e.getMessage();
		}
	}

	public void terminarPrograma() {
		System.out.println("Abandonando sistema...");
		utilidad.tiempoEspera();
		utilidad.limpiarPantalla();
		System.exit(0);
	}

	// EXTRAS
	final public void iniciarMenu() { // Inicia el menu en el Main
		do {
			System.out.printf(
					"\n1. Listar Clientes\n2. Agregar Cliente\n3. Editar Cliente\n4. Cargar Datos\n5. Exportar Datos\n6. Salir\n\n");
			try {
				System.out.println("Ingrese una opción: ");
				op = sc.nextInt();
				switch (op) {
				case 1:
					listarClientes();
					break;
				case 2:
					agregarCliente();
					break;
				case 3:
					editarCliente();
					break;
				case 4:
					cargarDatos();
					break;
				case 5:
					exportarDatos();
					break;
				case 6:
					terminarPrograma();
					break;
				default:
					System.out.println("Opción no válida.");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Ingreso es incorrecto.");
				e.getStackTrace();
				sc.next();
			} catch (FileNotFoundException e) {
				System.out.println("La ruta no existe.");
				e.getMessage();
			}
		} while (op != 6);

	}

	private void editarOnOff(Cliente cl) {
		try {
			switch (op) {
			case 1:
				cl.setOnOff(CategoriaEnum.INACTIVO);
				System.out.println("Se cambia el estado del cliente a INACTIVO");
				break;
			default:
				cl.setOnOff(CategoriaEnum.ACTIVO);// Seteo a activo, por que no le dan ningún margen de error al usuario
													// y eso me molesta un poco.
				System.out.println("Se mantiene el estado del cliente como ACTIVO");
				break;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	private void editarDatos(Cliente cl) {
		String n = "";
		try {
			switch (op) {
			case 1:
				System.out.println("Ingrese el nuevo run del Cliente: ");
				n = sc.next();
				cl.setRunCliente(n);
				break;
			case 2:
				System.out.println("Ingrese el nuevo nombre: ");
				n = sc.next();
				cl.setNombreCliente(n);
				break;
			case 3:
				System.out.println("Ingrese el nuevo apellido: ");
				n = sc.next();
				cl.setApellidoCliente(n);
				break;
			case 4:
				System.out.println("Ingrese los años como cliente: ");
				n = sc.next();
				cl.setAniosCliente(Integer.parseInt(n));
				break;
			default:
				System.out.println("Opción no válida");
				break;
			}
		} catch (InputMismatchException e) {
			e.getMessage();
		}
	}
}
