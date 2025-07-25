package vista;

import modelo.SopaLetras;
import modelo.BuscadorExhaustivo;
import java.util.Scanner;

/**
 * Interfaz de usuario en modo consola sin usar List ni ArrayList
 */
public class InterfazConsola {
    private Scanner scanner;
    
    public InterfazConsola() {
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Muestra el menú principal y gestiona la interacción
     */
    public void iniciar() {
        System.out.println("=== BUSCADOR DE PALABRAS EN SOPA DE LETRAS ===");
        
        // Crear una sopa de letras de ejemplo
        char[][] matriz = {
            {'A', 'B', 'C', 'D'},
            {'E', 'F', 'G', 'H'},
            {'I', 'J', 'K', 'L'},
            {'M', 'N', 'O', 'P'}
        };
        
        SopaLetras sopa = new SopaLetras(matriz);
        BuscadorExhaustivo buscador = new BuscadorExhaustivo(sopa);
        
        while (true) {
            System.out.println("\nSopa de letras actual:");
            mostrarMatriz(matriz);
            
            System.out.print("\nIngrese palabra a buscar (o 'salir' para terminar): ");
            String palabra = scanner.nextLine().trim().toUpperCase();
            
            if (palabra.equalsIgnoreCase("salir")) {
                break;
            }
            
            String[] resultados = buscador.buscarPalabra(palabra);
            
            if (resultados.length == 0) {
                System.out.println("La palabra '" + palabra + "' no se encontró en la sopa de letras.");
            } else {
                System.out.println("Resultados para '" + palabra + "':");
                for (String resultado : resultados) {
                    System.out.println(" - " + resultado);
                }
            }
        }
        
        System.out.println("Programa terminado.");
        scanner.close();
    }
    
    /**
     * Muestra la matriz de la sopa de letras en la consola
     * @param matriz Matriz a mostrar
     */
    private void mostrarMatriz(char[][] matriz) {
        for (char[] fila : matriz) {
            for (char c : fila) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}