package fi.leif.java.barcode;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;


public class ScreenshotTaker {
    
    public ScreenshotTaker() {
        new SnipIt(this);
    }
    
    public void areaSelected(Rectangle r) {
        BarcodeDecoder bd = new BarcodeDecoder();
        try {
            BufferedImage img = new Robot().createScreenCapture(r);
            bd = new BarcodeDecoder();
            String code = bd.decodeBarcode(img);
            System.out.println(code);
            bd.popup("Barcode decoded successfully into clipboard!\nCode: "+code);
            bd.setToClipboard(code);
        } catch(Exception e) {
            bd.popup("Barcode decoding failed");
            e.printStackTrace();
        }
    }
    
    public static void main(String... args) {
        new ScreenshotTaker();
    }
}