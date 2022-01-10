package utilidad;

public class Utilidad {
	public void limpiarPantalla() {
		int contador = 0;
		do {
			System.out.println();
			contador++;
		} while (contador < 100);
	}

	public void tiempoEspera() {
		try {
			Thread.sleep(3000); // 3 segundos
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
