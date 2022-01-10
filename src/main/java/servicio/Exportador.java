package servicio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import modelo.Cliente;

public abstract class Exportador {

	public void exportar(String ruta, List<Cliente> listaClientes, String fileName) throws FileNotFoundException, IOException {

	}
}
