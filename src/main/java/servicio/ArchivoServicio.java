package servicio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import modelo.CategoriaEnum;
import modelo.Cliente;

public class ArchivoServicio extends Exportador {

	public ArrayList<Cliente> cargarDatos(String ruta, String fileName) throws FileNotFoundException {
		File fichero = new File(System.getProperty("user.home") + File.separator + ruta + File.separator + fileName);
		ArrayList<Cliente> clientes = new ArrayList<>();

		if (fichero.exists()) {
			try {
				FileReader fr = new FileReader(fichero);
				BufferedReader br = new BufferedReader(fr);
				String datos = br.readLine();
				while (datos != null) {
					String[] datosListos = datos.split(",");
					String elInt = datosListos[3];
					String elEnum = datosListos[4];
					Cliente cl = new Cliente(datosListos[0], datosListos[1], datosListos[2],
							Integer.parseInt(elInt.substring(0,2).replaceAll(" ","")), CategoriaEnum.valueOf(elEnum.toUpperCase()));
					clientes.add(cl);
					datos = br.readLine();
				}
				br.close();
			} catch (FileNotFoundException e) {
				System.out.println("El archivo no se encuentra en esa ruta.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			throw new FileNotFoundException("El archivo no se encuentra en esa ruta.");
		}
		return clientes;
	}

	public void exportar(String ruta, List<Cliente> listaClientes, String fileName, int opmenu) {
		File directorio = new File(System.getProperty("user.home") + File.separator + ruta);
		if (!directorio.exists()) {
			directorio.mkdir();
		}
		File archivocsv = new File(directorio + File.separator + fileName + ".csv");
		File archivotxt = new File(directorio + File.separator + fileName + ".txt");
		if (opmenu == 1 && !archivocsv.exists()) {
			try {
				archivocsv.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (opmenu == 2 && !archivotxt.exists()) {
			try {
				archivotxt.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
