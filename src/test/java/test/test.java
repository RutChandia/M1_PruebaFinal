package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.CategoriaEnum;
import modelo.Cliente;
import servicio.ClienteServicio;

public class test {
	private Cliente cliente;
	private ClienteServicio clienteServicio;

	// Escribir pruebas unitarias para ClienteServicio
	@BeforeEach
	void setup() {
		clienteServicio = new ClienteServicio();
		cliente = new Cliente("9876543-k", "Julio", "Pérez", 7, CategoriaEnum.ACTIVO);
	}

	@Test
	void agrearClienteTest() {
		clienteServicio.agregarCliente(cliente);
		Assertions.assertNotNull(clienteServicio.getListaClientes());

	}

//	para verificar el funcionamiento de
//	agregarCliente en caso que vengan casos nulos (se debe agregar un cliente
//	nulo para que el test corra de manera correcta).
	@Test
	void agrearClienteNullTest() {
		Cliente cliente = null;
		clienteServicio.agregarCliente(cliente);
		Assertions.assertEquals(clienteServicio.getListaClientes().size(), 0);
	}

}
