import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Esta clase se encarga de leer y procesar el archivo de texto 'logistica.txt' para crear un grafo.
 * @author diego leiva, pablo orellana
 */
public class CityReader {
    private HashMap<String, Integer> cityIndex; //indice de la ciudad
    private int[][] matrix;     //la matriz
    private String[] cityNames; //listado de nombre de las ciudades


    /**
     * Constructor de la clase
     */
    public CityReader() {
        cityIndex = new HashMap<String, Integer>();
        matrix = null;
        cityNames = null;
    }


    /**
     * Lee el archivo txt y construye la matriz de adyacencia con los datos
     * @param filename nombre del archivo
     * @throws FileNotFoundException excepcion si no existe el archivo
     */
    public void readCities(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        List<String[]> lines = new ArrayList<>();
        while(scanner.hasNextLine()){
            lines.add(scanner.nextLine().split(" "));
        }
        scanner.close();
        matrix = new int[lines.size()][lines.size()];
        cityNames = new String[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i);
            cityIndex.put(parts[0], i);
            cityNames[i] = parts[0];
            for (int j = 2; j < parts.length; j++) {
                matrix[i][cityIndex.get(parts[j])] = Integer.parseInt(parts[j + 1]);
            }
        }
    }



    /**
     * Establece una conexion entre 2 ciudades con un tiempo de viaje para clima normal
     * @param city1
     * @param city2
     * @param normalTime
     */
    public void establishConnection(String city1, String city2, int normalTime) {
        int index1 = cityIndex.get(city1);
        int index2 = cityIndex.get(city2);
        matrix[index1][index2] = normalTime;
        matrix[index2][index1] = normalTime;
    }
    

    // Getters y setters

    /**
     * Getter del CityIndex
     * @return el indice de la ciudad
     */
    public HashMap<String, Integer> getCityIndex() {
        return cityIndex;
    }

    /**
     * Getter de matrix
     * @return la matriz
     */
    public int[][] getMatrix() {
        return matrix;
    }

    /**
     * getter del cityNames
     * @return el listado de ciudades
     */
    public String[] getCityNames() {
        return cityNames;
    }
}
