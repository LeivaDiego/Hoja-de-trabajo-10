import java.util.Arrays;

/**
 * Esta clase implementa el algoritmo de Floyd Warshall para calcular las distancias más cortas
 * entre todas las parejas de ciudades en el grafo.
 * También proporciona un método para encontrar el centro del grafo y otro para obtener la ruta
 * más corta entre dos ciudades específicas.
 *
 * @author diego leiva, palbo orellana
 *
 * Referencia de: malonso-uvg
 */
public class FloydWarshall {
    private int[][] distancias;
    private String[][] recorridos;
    private String[] vertices;
    private int SIZE;
    private static final int INF = Integer.MAX_VALUE;

    /**
     * Constructor
     * @param _distancias
     * @param _recorridos
     * @param matriz_size
     */
    public FloydWarshall(int [][]_distancias, String[][] _recorridos, int matriz_size) {
        SIZE = matriz_size;
        distancias = _distancias;
        recorridos = _recorridos;
        vertices = new String[matriz_size];
        for (int i = 0; i < matriz_size; i++) {
            vertices[i] = _recorridos[0][i];
        }
    }



    /**
     * @return the distancias
     */
    public int[][] getDistancias() {
        return distancias;
    }

    /**
     * @param distancias the distancias to set
     */
    public void setDistancias(int[][] distancias) {
        this.distancias = distancias;
    }

    /**
     * @return the recorridos
     */
    public String[][] getRecorridos() {
        return recorridos;
    }

    /**
     * @param recorridos the recorridos to set
     */
    public void setRecorridos(String[][] recorridos) {
        this.recorridos = recorridos;
    }

    /**
     * @return the sIZE
     */
    public int getSIZE() {
        return SIZE;
    }

    /**
     * @param sIZE the sIZE to set
     */
    public void setSIZE(int sIZE) {
        SIZE = sIZE;
    }

    /**
     * Implementacion del algoritmo de Floyd Warshall
     */
    public void CalcularRutas() {
        for (int i = 0; i < SIZE; i++) { //Que fila y que columna trabajo
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {

                    if ((i != j) && (i != k)) {
                        int suma = distancias[j][i] + distancias[i][k];
                        if (suma < distancias[j][k]) {
                            distancias[j][k] = suma;
                            recorridos[j][k] = vertices[i];
                        }
                    }

                }
            }
        }
    }

    /**
     * Encuentra el centro del grafo
     * @return
     */
    public String findCenter() {
        int minSum = Integer.MAX_VALUE;
        String center = null;

        for (int i = 0; i < SIZE; i++) {
            int sum = 0;
            for (int j = 0; j < SIZE; j++) {
                sum += distancias[i][j];
            }
            if (sum < minSum) {
                minSum = sum;
                center = vertices[i];
            }
        }

        return center;
    }


    /**
     * Obtiene la ruta mas corta entre 2 ciudades
     * @param start
     * @param end
     * @return
     */
    public String shortestPath(String start, String end) {
        int startIndex = Arrays.asList(vertices).indexOf(start);
        int endIndex = Arrays.asList(vertices).indexOf(end);

        if (distancias[startIndex][endIndex] == INF) {
            return "No existe una ruta entre las ciudades.";
        }

        String path = start;
        String intermediate = recorridos[startIndex][endIndex];

        while (!intermediate.equals(end)) {
            path += " -> " + intermediate;
            intermediate = recorridos[Arrays.asList(vertices).indexOf(intermediate)][endIndex];
        }

        path += " -> " + end;

        return path;
    }
}
