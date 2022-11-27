package camara.jose.utils.utils;

public class RandomNumber {
    /**
     * Metodo que genera un numero entero entre dos valores
     *
     * @param min Valor minimo que toma el metodo
     * @param max Valor maximo que toma el metodo
     * @return devuelve un valor entre numero minimo y maximo
     */
    public static int randomNumber(int min, int max) {
        int aux = 0;
        if (min > max) { // Validamos que el número mínimo y máximo corespondan con sus valores.
            aux = min;
            min = max;
            max = aux;
        }
        // return (int)(Math.random()*(maximo-minimo)+(maximo));
        return (int) Math.floor(Math.random() * (max - min + 1) + (min));
    }
}
