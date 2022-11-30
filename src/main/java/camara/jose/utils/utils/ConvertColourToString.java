package camara.jose.utils.utils;

public class ConvertColourToString {

    public static String convert(int red, int green, int blue) {
        String newRed=red+"";
        String newGreen=green+"";
        String newBlue=blue+"";

        if(red<10) {
            newRed="0"+red;
        }
        if(green<10) {
            newGreen="0"+red;
        }
        if(blue<10) {
            newBlue="0"+red;
        }

        return "#"+newRed+newGreen+newBlue;
    }
}
