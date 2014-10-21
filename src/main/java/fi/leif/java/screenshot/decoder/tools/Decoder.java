package fi.leif.java.screenshot.decoder.tools;

import java.awt.image.BufferedImage;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class Decoder {
    
    public static String decodeImage(BufferedImage img) throws Exception {
        LuminanceSource source = new BufferedImageLuminanceSource(img);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));        
        MultiFormatReader barcodeReader = new MultiFormatReader();      
        Result r = barcodeReader.decode(bitmap);
        return String.valueOf(r.getText());
      }
}
