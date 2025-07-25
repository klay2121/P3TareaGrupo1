package modelo;

/**
 * Clase que representa la sopa de letras y contiene la matriz de caracteres
 */
public class SopaLetras {
    private char[][] matriz;
    private int filas;
    private int columnas;

    /**
     * Constructor que inicializa la sopa de letras
     * @param matriz Matriz de caracteres que representa la sopa de letras
     */
    public SopaLetras(char[][] matriz) {
        this.matriz = matriz;
        this.filas = matriz.length;
        this.columnas = matriz[0].length;
    }

    /**
     * Obtiene el carácter en una posición específica
     * @param fila Fila de la matriz
     * @param columna Columna de la matriz
     * @return Carácter en la posición solicitada
     */
    public char getCaracter(int fila, int columna) {
        return matriz[fila][columna];
    }

    /**
     * @return Número de filas de la matriz
     */
    public int getFilas() {
        return filas;
    }

    /**
     * @return Número de columnas de la matriz
     */
    public int getColumnas() {
        return columnas;
    }
}