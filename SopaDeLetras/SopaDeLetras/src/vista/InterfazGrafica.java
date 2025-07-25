package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.BuscadorExhaustivo;
import modelo.SopaLetras;

public class InterfazGrafica extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtPalabra;
    private JLabel[][] lblLetras;
    private JTextArea txtResultados;
    private SopaLetras sopa;
    private BuscadorExhaustivo buscador;
    private Map<String, Color> coloresPalabras; // Para mantener colores únicos por palabra

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InterfazGrafica frame = new InterfazGrafica();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public InterfazGrafica() {
        // Inicializar modelo con la matriz 12x12 proporcionada
        char[][] matrizEjemplo = {
            {'P', 'R', 'I', 'V', 'I', 'L', 'E', 'G', 'I', 'A', 'D', 'O'},
            {'O', 'E', 'N', 'I', 'R', 'T', 'E', 'X', 'Z', 'D', 'U', 'J'},
            {'R', 'E', 'N', 'A', 'C', 'I', 'M', 'I', 'E', 'N', 'T', 'O'},
            {'O', 'T', 'S', 'A', 'F', 'O', 'T', 'L', 'A', 'O', 'R', 'N'},
            {'A', 'S', 'O', 'T', 'N', 'E', 'V', 'T', 'C', 'E', 'I', 'E'},
            {'R', 'T', 'I', 'A', 'A', 'E', 'A', 'O', 'Z', 'L', 'V', 'N'},
            {'S', 'A', 'R', 'P', 'I', 'M', 'E', 'O', 'O', 'A', 'U', 'O'},
            {'O', 'C', 'Z', 'I', 'B', 'S', 'P', 'C', 'N', 'O', 'R', 'B'},
            {'A', 'M', 'I', 'R', 'A', 'V', 'O', 'A', 'R', 'G', 'E', 'R'},
            {'C', 'O', 'N', 'V', 'O', 'C', 'A', 'T', 'O', 'R', 'I', 'A'},
            {'A', 'T', 'S', 'E', 'I', 'F', 'O', 'L', 'O', 'E', 'N', 'J'},
            {'A', 'C', 'A', 'M', 'A', 'H', 'O', 'L', 'L', 'E', 'A', 'E'}
        };
        
        sopa = new SopaLetras(matrizEjemplo);
        buscador = new BuscadorExhaustivo(sopa);
        coloresPalabras = new HashMap<>();
        
        setTitle("Buscador Exhaustivo - Sopa de Letras Profesional");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 800); // Ventana más grande para la matriz 12x12
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);
        
        // Panel superior - Búsqueda
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setLayout(new BorderLayout(10, 10));
        contentPane.add(panelBusqueda, BorderLayout.NORTH);
        
        JLabel lblTitulo = new JLabel("Ingrese palabra a buscar:");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        panelBusqueda.add(lblTitulo, BorderLayout.WEST);
        
        txtPalabra = new JTextField();
        txtPalabra.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panelBusqueda.add(txtPalabra, BorderLayout.CENTER);
        
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnBuscar.setBackground(new Color(70, 130, 180));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarPalabra();
            }
        });
        panelBusqueda.add(btnBuscar, BorderLayout.EAST);
        
        // Panel central - Sopa de letras
        JPanel panelSopa = new JPanel();
        panelSopa.setBorder(BorderFactory.createTitledBorder("Sopa de Letras (12x12)"));
        panelSopa.setLayout(new GridLayout(matrizEjemplo.length, matrizEjemplo[0].length, 1, 1)); // Espaciado mínimo
        
        // Fuente más pequeña para caber mejor
        Font fuenteLetras = new Font("Arial", Font.BOLD, 16);
        
        lblLetras = new JLabel[matrizEjemplo.length][matrizEjemplo[0].length];
        for (int i = 0; i < matrizEjemplo.length; i++) {
            for (int j = 0; j < matrizEjemplo[0].length; j++) {
                lblLetras[i][j] = new JLabel(String.valueOf(matrizEjemplo[i][j]), SwingConstants.CENTER);
                lblLetras[i][j].setFont(fuenteLetras);
                lblLetras[i][j].setOpaque(true);
                lblLetras[i][j].setBackground(Color.WHITE);
                lblLetras[i][j].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                panelSopa.add(lblLetras[i][j]);
            }
        }
        
        JScrollPane scrollSopa = new JScrollPane(panelSopa);
        contentPane.add(scrollSopa, BorderLayout.CENTER);
        
        // Panel inferior - Resultados
        JPanel panelResultados = new JPanel();
        panelResultados.setBorder(BorderFactory.createTitledBorder("Resultados"));
        panelResultados.setLayout(new BorderLayout());
        
        txtResultados = new JTextArea();
        txtResultados.setEditable(false);
        txtResultados.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtResultados.setBackground(new Color(240, 240, 240));
        txtResultados.setLineWrap(true);
        txtResultados.setWrapStyleWord(true);
        
        JScrollPane scrollResultados = new JScrollPane(txtResultados);
        scrollResultados.setPreferredSize(new java.awt.Dimension(0, 150)); // Altura fija
        
        panelResultados.add(scrollResultados, BorderLayout.CENTER);
        contentPane.add(panelResultados, BorderLayout.SOUTH);
    }
    
    private void buscarPalabra() {
        String palabra = txtPalabra.getText().trim().toUpperCase();
        
        if (palabra.isEmpty()) {
            txtResultados.setText("Por favor ingrese una palabra a buscar.");
            return;
        }
        
        if (palabra.length() < 3) {
            txtResultados.setText("La palabra debe tener al menos 3 letras.");
            return;
        }
        
        String[] resultados = buscador.buscarPalabra(palabra);
        
        if (resultados.length == 0) {
            txtResultados.setText("La palabra \"" + palabra + "\" no se encontró en la sopa de letras.");
        } else {
            // Asignar color único a la palabra si no lo tiene
            if (!coloresPalabras.containsKey(palabra)) {
                coloresPalabras.put(palabra, generarColorUnico());
            }
            
            StringBuilder sb = new StringBuilder();
            sb.append("La palabra \"").append(palabra).append("\" se encontró ")
              .append(resultados.length).append(" vez/veces:\n\n");
            
            for (String resultado : resultados) {
                sb.append("• ").append(resultado).append("\n");
                resaltarPalabraEnResultado(resultado, palabra);
            }
            
            txtResultados.setText(sb.toString());
        }
    }
    
    private void resaltarPalabraEnResultado(String resultado, String palabra) {
        // Extraer coordenadas del resultado
        int inicio = resultado.indexOf("(") + 1;
        int fin = resultado.indexOf(")");
        String[] coords = resultado.substring(inicio, fin).split(",");
        int fila = Integer.parseInt(coords[0].trim());
        int col = Integer.parseInt(coords[1].trim());
        
        // Determinar dirección
        String direccion = resultado.substring(resultado.indexOf("Dirección:") + 10).trim();
        
        // Determinar vector dirección
        int dirFila = 0, dirCol = 0;
        
        switch(direccion) {
            case "Diagonal Superior Izquierda": dirFila = -1; dirCol = -1; break;
            case "Arriba": dirFila = -1; dirCol = 0; break;
            case "Diagonal Superior Derecha": dirFila = -1; dirCol = 1; break;
            case "Izquierda": dirFila = 0; dirCol = -1; break;
            case "Derecha": dirFila = 0; dirCol = 1; break;
            case "Diagonal Inferior Izquierda": dirFila = 1; dirCol = -1; break;
            case "Abajo": dirFila = 1; dirCol = 0; break;
            case "Diagonal Inferior Derecha": dirFila = 1; dirCol = 1; break;
        }
        
        // Resaltar todas las letras de la palabra
        Color colorPalabra = coloresPalabras.get(palabra);
        for (int k = 0; k < palabra.length(); k++) {
            int nuevaFila = fila + k * dirFila;
            int nuevaCol = col + k * dirCol;
            if (nuevaFila >= 0 && nuevaFila < lblLetras.length && 
                nuevaCol >= 0 && nuevaCol < lblLetras[0].length) {
                lblLetras[nuevaFila][nuevaCol].setBackground(colorPalabra);
            }
        }
    }
    
    private Color generarColorUnico() {
        // Generar colores pastel distintos para cada palabra
        float hue = (float) Math.random();
        return Color.getHSBColor(hue, 0.3f, 0.9f);
    }
    
    private void resetColores() {
        for (int i = 0; i < lblLetras.length; i++) {
            for (int j = 0; j < lblLetras[0].length; j++) {
                lblLetras[i][j].setBackground(Color.WHITE);
            }
        }
        coloresPalabras.clear();
    }
}