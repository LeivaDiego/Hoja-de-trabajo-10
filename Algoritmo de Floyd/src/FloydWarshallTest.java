import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloydWarshallTest {
    @Test
    void testCalcularRutas() {
        int[][] matriz_distancias = {{0,4,8,10000,10000},
                {4,0,1,2,10000},
                {8,10000,0,4,2},
                {10000,2,4,0,7},
                {10000,10000,2,7,0}};

        String[][] matriz_recorridos =
                {{"A","B","C","D","E"},
                        {"A","B","C","D","E"},
                        {"A","B","C","D","E"},
                        {"A","B","C","D","E"},
                        {"A","B","C","D","E"}};

        FloydWarshall algoritmoFW = new FloydWarshall(matriz_distancias, matriz_recorridos, 5);

        algoritmoFW.CalcularRutas();

        int[][] matriz_distancias_resultante =
                {{0,4,5,6,7},
                        {4,0,1,2,3},
                        {8,6,0,4,2},
                        {6,2,3,0,5},
                        {10,8,2,6,0}};


        String[][] matriz_recorridos_resultante =
                {{"A","B","B","B","C"},
                        {"A","B","C","D","C"},
                        {"A","D","C","D","E"},
                        {"B","B","B","D","C"},
                        {"C","D","C","C","E"}};

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertEquals(matriz_distancias_resultante[i][j],
                        algoritmoFW.getDistancias()[i][j]);
            }
        }


        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertEquals(matriz_recorridos_resultante[i][j],
                        algoritmoFW.getRecorridos()[i][j]);
            }
        }
    }

    @Test
    public void testShortestPath() {
        int[][] distancias = {
                {0, 10, 15},
                {10, 0, 20},
                {15, 20, 0}
        };
        String[][] recorridos = {
                {"A", "B", "C"},
                {"A", "B", "C"},
                {"A", "B", "C"}
        };
        FloydWarshall floydWarshall = new FloydWarshall(distancias, recorridos, 3);

        // Prueba de ruta más corta existente
        String shortestPath = floydWarshall.shortestPath("A", "C");
        assertEquals("A -> C", shortestPath);

        // Prueba de ruta más corta inexistente
        String nonexistentPath = floydWarshall.shortestPath("B", "A");
        assertEquals("B -> A", nonexistentPath);
    }

    @Test
    public void testFindCenter() {
        int[][] distancias = {
                {0, 10, 15},
                {10, 0, 20},
                {15, 20, 0}
        };
        String[][] recorridos = {
                {"A", "B", "C"},
                {"A", "B", "C"},
                {"A", "B", "C"}
        };
        FloydWarshall floydWarshall = new FloydWarshall(distancias, recorridos, 3);

        String center = floydWarshall.findCenter();
        assertEquals("A", center);
    }

}