import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Programa que implementa un grafo para aplicar el algoritmo de Floyd
 * y encontrar la ruta mas corta, el centro del grafo
 * @author diego leiva, pablo orellana
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CityReader cityReader = new CityReader();
        try {
            cityReader.readCities("C:/Users/diego/Documents/UVG/5to Semestre/Algoritmos y Estructuras de Datos/Hoja-de-trabajo-10/Algoritmo de Floyd/Otros/logistica.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String[][] recorridos = new String[cityReader.getCityNames().length][cityReader.getCityNames().length];
        for (int i = 0; i < cityReader.getCityNames().length; i++) {
            recorridos[0][i] = cityReader.getCityNames()[i];
        }

        FloydWarshall floydWarshall = new FloydWarshall(cityReader.getMatrix(), recorridos, cityReader.getCityNames().length);
        floydWarshall.CalcularRutas();

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Encontrar la ruta más corta entre dos ciudades");
            System.out.println("2. Encontrar la ciudad en el centro del grafo");
            System.out.println("3. Modificar el grafo");
            System.out.println("4. Salir");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese la ciudad de origen:");
                    String start = scanner.next();
                    System.out.println("Ingrese la ciudad de destino:");
                    String end = scanner.next();
                    System.out.println("La ruta más corta es: " + floydWarshall.shortestPath(start, end));
                    break;
                case 2:
                    System.out.println("La ciudad en el centro del grafo es: " + floydWarshall.findCenter());
                    break;
                case 3:
                    System.out.println("Seleccione una opción:");
                    System.out.println("A. Interrumpir el tráfico entre dos ciudades");
                    System.out.println("B. Establecer una conexión entre dos ciudades");
                    System.out.println("C. Cambiar el clima entre dos ciudades");
                    String opcionGrafo = scanner.next();
                    switch (opcionGrafo) {
                        case "A":
                            System.out.println("Ingrese la ciudad 1:");
                            String city1A = scanner.next();
                            System.out.println("Ingrese la ciudad 2:");
                            String city2A = scanner.next();
                            cityReader.interruptTraffic(city1A, city2A);
                            floydWarshall.CalcularRutas();
                            break;
                        case "B":
                            System.out.println("Ingrese la ciudad 1:");
                            String city1B = scanner.next();
                            System.out.println("Ingrese la ciudad 2:");
                            String city2B = scanner.next();
                            System.out.println("Ingrese el tiempo de viaje normal:");
                            int normalTime = scanner.nextInt();
                            cityReader.establishConnection(city1B, city2B, normalTime);
                            floydWarshall.CalcularRutas();
                            break;
                        case "C":
                            System.out.println("Ingrese la ciudad 1:");
                            String city1C = scanner.next();
                            System.out.println("Ingrese la ciudad 2:");
                            String city2C = scanner.next();
                            System.out.println("Ingrese el nuevo tiempo de viaje:");
                            int newTime = scanner.nextInt();
                            cityReader.changeWeather(city1C, city2C, newTime);
                            floydWarshall.CalcularRutas();
                            break;
                        default:
                            System.out.println("Opción no válida");
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}