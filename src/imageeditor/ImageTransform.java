package imageeditor;

//~--- JDK imports ------------------------------------------------------------

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 *
 * @author Leonardo
 */
public class ImageTransform {
    private final AffineTransform trasnf;
    private final int             height;
    private final int             width;
    private double                grados;

    public ImageTransform(int alturaImagen, int anchuraImagen) {
        trasnf = new AffineTransform();
        height = alturaImagen;
        width  = anchuraImagen;
    }

    public void cambiarGrados(double a) {
        grados = a;

        // se guarda el medio de la imagen completa
        trasnf.rotate(Math.toRadians(grados), width / 2, height / 2);
    }

    public AffineTransform getRotacion() {
        return trasnf;
    }

    public void findRotacion() {
        Point2D aux, aux1;

        aux  = hallarRotacion1();
        aux1 = trasnf.transform(aux, null);

        double ytrans;

        ytrans = aux1.getY();
        aux    = hallarRotacion2();
        aux1   = trasnf.transform(aux, null);

        double xtrans;

        xtrans = aux1.getX();

        AffineTransform trans;

        trans = new AffineTransform();
        trans.translate(-xtrans, -ytrans);
        trasnf.preConcatenate(trans);
    }

    private Point2D hallarRotacion1() {

        // se busca a donde se movera la esquina inferior izquierda
        Point2D aux;

        if ((grados >= 0) && (grados <= 90)) {
            aux = new Point2D.Double(0.0, 0.0);
        } else {
            if ((grados > 90) && (grados <= 180)) {
                aux = new Point2D.Double(0.0, height);
            } else {
                if ((grados > 180) && (grados <= 270)) {
                    aux = new Point2D.Double(width, height);
                } else {
                    aux = new Point2D.Double(width, 0.0);
                }
            }
        }

        return aux;
    }

    private Point2D hallarRotacion2() {

        // se busca a donde se movera la esquina inferior derecha
        Point2D aux;

        if ((grados >= 0) && (grados <= 90)) {
            aux = new Point2D.Double(0.0, height);
        } else {
            if ((grados > 90) && (grados <= 180)) {
                aux = new Point2D.Double(width, height);
            } else {
                if ((grados > 180) && (grados <= 270)) {
                    aux = new Point2D.Double(width, 0.0);
                } else {
                    aux = new Point2D.Double(0.0, 0.0);
                }
            }
        }

        return aux;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
