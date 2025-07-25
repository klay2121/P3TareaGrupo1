package modelo;

/**
 * Clase que implementa el algoritmo de búsqueda exhaustiva sin usar List ni ArrayList
 */
public class BuscadorExhaustivo {
    // Direcciones posibles para la búsqueda (8 direcciones)
    private static final int[][] DIRECCIONES = {
        {-1, -1}, {-1, 0}, {-1, 1},  // Diagonal superior izquierda, arriba, diagonal superior derecha
        {0, -1},          {0, 1},    // Izquierda, derecha
        {1, -1},  {1, 0}, {1, 1}     // Diagonal inferior izquierda, abajo, diagonal inferior derecha
    };

    private SopaLetras sopa;
    private String[] resultados;
    private int contadorResultados;
    private static final int MAX_RESULTADOS = 100; // Tamaño máximo predefinido

    /**
     * Constructor que recibe la sopa de letras donde se buscará
     * @param sopa Instancia de SopaLetras
     */
    public BuscadorExhaustivo(SopaLetras sopa) {
        this.sopa = sopa;
        this.resultados = new String[MAX_RESULTADOS];
        this.contadorResultados = 0;
    }

    /**
     * Método principal de búsqueda que aplica el enfoque exhaustivo
     * @param palabra Palabra a buscar
     * @return Arreglo de resultados con las posiciones y direcciones donde se encontró la palabra
     */
    public String[] buscarPalabra(String palabra) {
        contadorResultados = 0; // Reiniciar contador
        int longitud = palabra.length();
        
        // Búsqueda exhaustiva: recorrer toda la matriz
        for (int i = 0; i < sopa.getFilas(); i++) {
            for (int j = 0; j < sopa.getColumnas(); j++) {
                // Verificar si la primera letra coincide
                if (sopa.getCaracter(i, j) == palabra.charAt(0)) {
                    // Probar todas las direcciones posibles
                    for (int[] dir : DIRECCIONES) {
                        if (verificarDireccion(palabra, i, j, dir[0], dir[1])) {
                            if (contadorResultados < MAX_RESULTADOS) {
                                resultados[contadorResultados++] = String.format(
                                    "Encontrada en (%d,%d) - Dirección: %s", 
                                    i, j, obtenerNombreDireccion(dir[0], dir[1]));
                            }
                        }
                    }
                }
            }
        }
        
        return obtenerResultados();
    }

    /**
     * Devuelve solo los resultados encontrados (sin espacios vacíos)
     */
    private String[] obtenerResultados() {
        String[] resultadosFinales = new String[contadorResultados];
        System.arraycopy(resultados, 0, resultadosFinales, 0, contadorResultados);
        return resultadosFinales;
    }

    /**
     * Verifica si la palabra existe en una dirección específica
     */
    private boolean verificarDireccion(String palabra, int fila, int columna, int dirFila, int dirCol) {
        int longitud = palabra.length();
        
        // Verificar si la palabra cabe en esta dirección
        int ultimaFila = fila + (longitud - 1) * dirFila;
        int ultimaCol = columna + (longitud - 1) * dirCol;
        
        if (ultimaFila < 0 || ultimaFila >= sopa.getFilas() || 
            ultimaCol < 0 || ultimaCol >= sopa.getColumnas()) {
            return false;
        }
        
        // Verificar letra por letra
        for (int k = 1; k < longitud; k++) {
            int nuevaFila = fila + k * dirFila;
            int nuevaCol = columna + k * dirCol;
            if (sopa.getCaracter(nuevaFila, nuevaCol) != palabra.charAt(k)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Obtiene el nombre descriptivo de la dirección
     */
    private String obtenerNombreDireccion(int dirFila, int dirCol) {
        if (dirFila == -1 && dirCol == -1) return "Diagonal Superior Izquierda";
        if (dirFila == -1 && dirCol == 0) return "Arriba";
        if (dirFila == -1 && dirCol == 1) return "Diagonal Superior Derecha";
        if (dirFila == 0 && dirCol == -1) return "Izquierda";
        if (dirFila == 0 && dirCol == 1) return "Derecha";
        if (dirFila == 1 && dirCol == -1) return "Diagonal Inferior Izquierda";
        if (dirFila == 1 && dirCol == 0) return "Abajo";
        if (dirFila == 1 && dirCol == 1) return "Diagonal Inferior Derecha";
        return "Desconocida";
    }
}