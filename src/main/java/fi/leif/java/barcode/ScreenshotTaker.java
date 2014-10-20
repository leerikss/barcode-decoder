package fi.leif.java.barcode;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;


public class ScreenshotTaker {
    
    public ScreenshotTaker() {
        new SelectionRectangle(this);
    }
    
    public void areaSelected(BufferedImage screenshot) {
        BarcodeDecoder bd = new BarcodeDecoder();
        try {
            bd = new BarcodeDecoder();
            String code = bd.decodeBarcode(screenshot);
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