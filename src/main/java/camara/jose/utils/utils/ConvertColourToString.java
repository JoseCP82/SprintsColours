package camara.jose.utils.utils;

public class ConvertColourToString {

    /**
     * Convierte datos de tipo entero en string y los concatena
     * @param red Dato de tipo entero
     * @param green Dato de tipo entero
     * @param blue Dato de tipo entero
     * @return Cadena con los valores enteros covertidos y concatenados
     */
    public static String convert(int red, int green, int blue) {
        String newRed=red+"";
        String newGreen=green+"";
        String newBlue=blue+"";
        if(red<10) {
            newRed="0"+red;
        }
        if(green<10) {
            newGreen="0"+green;
        }
        if(blue<10) {
            newBlue="0"+blue;
        }
        return "#"+newRed+newGreen+newBlue;
    }
}
