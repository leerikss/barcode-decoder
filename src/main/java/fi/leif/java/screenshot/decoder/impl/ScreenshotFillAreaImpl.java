package fi.leif.java.screenshot.decoder.impl;

import fi.leif.java.screenshot.decoder.selection.FillAreaSelection;


public class ScreenshotFillAreaImpl extends ScreenshotImpl {

    public static void main(String... args) {
        new ScreenshotFillAreaImpl();
    }
    
    public ScreenshotFillAreaImpl() {
        new FillAreaSelection(this);
    }
}
