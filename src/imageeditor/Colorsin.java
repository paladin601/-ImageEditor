
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package imageeditor;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;

/**
 *
 * @author FliaMejias
 */
public class Colorsin {
    int[] color;

    public Colorsin() {
        color    = new int[4];
        color[0] = 0;
        color[1] = 0;
        color[2] = 0;
    }

    public Colorsin(Colorsin co) {
        color = new int[3];
        System.arraycopy(co.color, 0, this.color, 0, 3);
    }

    public Colorsin(int rgb) {
        color = new int[3];
        assignRGB(rgb);
    }

    public Colorsin(int red, int green, int blue) {
        color    = new int[3];
        color[0] = red;
        color[1] = green;
        color[2] = blue;
    }

    public Color toColor() {
        return new Color(color[0], color[1], color[2]);
    }

    public int toRGB() {
        return toColor().getRGB();
    }

    public Colorsin assignRGB(int rgb) {
        Color co = new Color(rgb);

        color[2] = co.getBlue();
        color[1] = co.getGreen();
        color[0] = co.getRed();

        return this;
    }

    public Colorsin operate(ProcesamientoImagen.IntOperation operation, Colorsin co) {
        return new Colorsin(operation.op(this.color[0], co.color[0]), operation.op(this.color[1], co.color[1]),
                            operation.op(this.color[2], co.color[2]));
    }

    public Colorsin sumarConstante(int cons) {
        Colorsin out = new Colorsin(this);

        for (int ii = 0; ii < 3; ii++) {
            out.color[ii] += cons;
        }

        return out;
    }

    public Colorsin multiplicarConstante(float cons) {
        Colorsin out = new Colorsin(this);

        for (int ii = 0; ii < 3; ii++) {
            out.color[ii] *= cons;
        }

        return out;
    }

    public Colorsin clamp() {
        for (int ii = 0; ii < 3; ii++) {
            int aux = color[ii];

            aux       = (aux > 255)
                        ? 255
                        : aux;
            aux       = (aux < 0)
                        ? 0
                        : aux;
            color[ii] = aux;
        }

        return this;
    }

    public String toString() {
        return "Colorsin Red " + color[0] + "/Green " + color[1] + " /Blue " + color[2];
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
