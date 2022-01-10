package servicio;

import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;

public class ClienteServicio {
	private List<Cliente> listaClientes;

	public ClienteServicio() {
		super();
		listaClientes = new ArrayList<>();
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void listarClientes() {
		listaClientes.forEach(cl -> System.out.println(cl.toString()));
	}

	public Cliente buscarCliente(String run) {
		for (Cliente cliente : listaClientes) {
			if (cliente.getRunCliente().equals(run)) {
				return cliente;
			}
		}
		return null;

	}

	public void agregarCliente(Cliente cl) {
		if (cl != null && buscarCliente(cl.getRunCliente()) == null) {
			listaClientes.add(cl);
			System.out.println("El cliente ha sido agregado");
		} else {
			System.out.println("El cliente ya existe");
		}
	}
}
