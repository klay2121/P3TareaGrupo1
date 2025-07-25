package controlador;

import modelo.SopaLetras;
import modelo.BuscadorExhaustivo;
import vista.InterfazConsola;
import vista.InterfazGrafica;

/**
 * Controlador principal que coordina el modelo y la vista
 */
public class ControladorSopaLetras {
    /**
     * Método principal que inicia la aplicación
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        // Elegir interfaz (consola o gráfica)
        if (args.length > 0 && args[0].equalsIgnoreCase("consola")) {
            InterfazConsola consola = new InterfazConsola();
            consola.iniciar();
        } else {
            InterfazGrafica gui = new InterfazGrafica();
            gui.setVisible(true);
        }
    }
}