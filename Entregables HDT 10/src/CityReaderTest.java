import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class CityReaderTest {

    @Test
    public void testCityExists() throws FileNotFoundException {
        CityReader cityReader = new CityReader();
        cityReader.readCities("C:/Users/diego/Documents/UVG/5to Semestre/Algoritmos y Estructuras de Datos/Hoja-de-trabajo-10/Algoritmo de Floyd/Otros/logistica.txt");

        assertTrue(cityReader.cityExists("BuenosAires"));
        assertFalse(cityReader.cityExists("Paris"));
    }

    @Test
    public void testInterruptTraffic() throws FileNotFoundException {
        CityReader cityReader = new CityReader();
        cityReader.readCities("C:/Users/diego/Documents/UVG/5to Semestre/Algoritmos y Estructuras de Datos/Hoja-de-trabajo-10/Algoritmo de Floyd/Otros/logistica.txt");

        cityReader.interruptTraffic("BuenosAires", "SaoPaulo");

        assertFalse(cityReader.cityExists("BuenosAires"));
        assertFalse(cityReader.cityExists("SaoPaulo"));
    }

    @Test
    public void testEstablishConnection() throws FileNotFoundException {
        CityReader cityReader = new CityReader();
        cityReader.readCities("C:/Users/diego/Documents/UVG/5to Semestre/Algoritmos y Estructuras de Datos/Hoja-de-trabajo-10/Algoritmo de Floyd/Otros/logistica.txt");

        cityReader.establishConnection("BuenosAires", "SaoPaulo", 5);

        assertTrue(cityReader.cityExists("BuenosAires"));
        assertTrue(cityReader.cityExists("SaoPaulo"));
        assertEquals(5, cityReader.getMatrix()[0][1]);
        assertEquals(5, cityReader.getMatrix()[1][0]);
    }

    @Test
    public void testChangeWeather() throws FileNotFoundException {
        CityReader cityReader = new CityReader();
        cityReader.readCities("C:/Users/diego/Documents/UVG/5to Semestre/Algoritmos y Estructuras de Datos/Hoja-de-trabajo-10/Algoritmo de Floyd/Otros/logistica.txt");

        cityReader.changeWeather("BuenosAires", "SaoPaulo", 30);

        assertTrue(cityReader.cityExists("BuenosAires"));
        assertTrue(cityReader.cityExists("SaoPaulo"));
        assertEquals(30, cityReader.getMatrix()[0][1]);
        assertEquals(30, cityReader.getMatrix()[1][0]);
    }

}