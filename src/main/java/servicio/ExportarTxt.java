package servicio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import modelo.Cliente;

public class ExportarTxt extends Exportador {

	public void exportar(String ruta, List<Cliente> listaClientes, String fileName) {
		File fichero = new File(
				System.getProperty("user.home") + File.separator + ruta + File.separator + fileName + ".txt");
		if (fichero.exists()) {
			try {

				FileWriter fw = new FileWriter(fichero.getAbsoluteFile(), true);
				PrintWriter pw = new PrintWriter(fw);
				for (Cliente cliente : listaClientes) {
					String salida = String.format("%s,%s,%s,%s años,%s", cliente.getRunCliente(),
							cliente.getNombreCliente(), cliente.getApellidoCliente(), cliente.getAniosCliente(),
							cliente.getOnOff());

					pw.write(salida);
					pw.write("\n");
				}
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
