package modelo;

public class Cliente {
	private String runCliente;
	private String nombreCliente;
	private String apellidoCliente;
	private int aniosCliente;
	private CategoriaEnum onOff;

	public Cliente(String runCliente, String nombreCliente, String apellidoCliente, int aniosCliente,
			CategoriaEnum onOff) {
		super();
		this.runCliente = runCliente;
		this.nombreCliente = nombreCliente;
		this.apellidoCliente = apellidoCliente;
		this.aniosCliente = aniosCliente;
		this.onOff = onOff;
	}

	public Cliente() {
		this.onOff = null;

	}

	public String getRunCliente() {
		return runCliente;
	}

	public void setRunCliente(String runCliente) {
		this.runCliente = runCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getApellidoCliente() {
		return apellidoCliente;
	}

	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}

	public int getAniosCliente() {
		return aniosCliente;
	}

	public void setAniosCliente(int aniosCliente) {
		this.aniosCliente = aniosCliente;
	}

	public CategoriaEnum getOnOff() {
		return onOff;
	}

	public void setOnOff(CategoriaEnum onOff) {
		this.onOff = onOff;
	}

	@Override
	public String toString() {
		return String.format(
				"\nCliente: \nRUN: %s \nNombre del Cliente: %s \nApellido del Cliente: %s \nAños como Cliente: %d años\nCategoría del Cliente: %s \n",
				runCliente, nombreCliente, apellidoCliente, aniosCliente, onOff);

	}

}
