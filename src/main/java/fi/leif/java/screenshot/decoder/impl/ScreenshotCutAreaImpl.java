package fi.leif.java.screenshot.decoder.impl;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

import fi.leif.java.screenshot.decoder.selection.CutAreaSelection;
import fi.leif.java.screenshot.decoder.tools.Popup;


public class ScreenshotCutAreaImpl extends ScreenshotImpl {
    
    public static void main(String... args) {
        new ScreenshotCutAreaImpl();
    }
    
    public ScreenshotCutAreaImpl() {
        new CutAreaSelection(this);
    }

    @Override
    public void handleSelection(Rectangle r) {
        try {
            BufferedImage img = new Robot().createScreenCapture(r);
            handleImage(img);
        } catch(Exception e) {
            Popup.show("Internal error!");
        }
    }
}