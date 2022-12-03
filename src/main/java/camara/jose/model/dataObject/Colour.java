package camara.jose.model.dataObject;

public class Colour {

    /**
     * Atributos de clase
     */
    private String name;
    private int red;
    private int green;
    private int blue;

    /**
     * Constructor parametrizado
     * @param name
     * @param red
     * @param green
     * @param blue
     */
    public Colour(String name, int red, int green, int blue) {
        this.name = name;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Constructor por defecto
     */
    public Colour() { this("default",0,0,0); }

    /**
     * Getters and Setters
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    /**
     * Metodo toString
     * @return
     */
    @Override
    public String toString() {
        return "name= " + name + ", red=" + red +", green=" + green +", blue=" + blue;
    }
}
