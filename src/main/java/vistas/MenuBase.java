package vistas;

import java.io.FileNotFoundException;

public interface MenuBase {
	void listarClientes();
	void agregarCliente();
	void editarCliente();
	void cargarDatos() throws FileNotFoundException;
	void exportarDatos();
	void terminarPrograma();
}
