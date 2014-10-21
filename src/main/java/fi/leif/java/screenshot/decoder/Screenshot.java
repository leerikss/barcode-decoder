package fi.leif.java.screenshot.decoder;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public interface Screenshot {
    public void handleSelection(Rectangle r);
    public void handleImage(BufferedImage img);
}
