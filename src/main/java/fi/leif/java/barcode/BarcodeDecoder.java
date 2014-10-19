/*
  The MIT License (MIT)

  Copyright (c) 2014 Leif Eriksson

  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  copies of the Software, and to permit persons to whom the Software is
  furnished to do so, subject to the following conditions:

  The above copyright notice and this permission notice shall be included in
  all copies or substantial portions of the Software.

  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
  THE SOFTWARE.
*/

package fi.leif.java.barcode;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

/**
 * A simple class for decoding a barcode from an image retrieved from the clipboard.
 * The decoded barcode is than placed back to the clipboard.
 * 
 * @author Leif Eriksson
 *
 */
public class BarcodeDecoder {

  /**
   * Attepmts to retrieve a BufferedImage from the clipboard (assuming there's an image), 
   * decodes that BufferedImage (assuming it's a barcode), than places the decoded barcode String back to the clipboard.
   * Finally a popup will show if decoding was successfull or not.
   * @param args
   */
  public static void main(String... args) {
    BarcodeDecoder bc = new BarcodeDecoder();
    try {
      BufferedImage img = bc.getImageFromClipboard();
      String code = bc.decodeBarcode(img);
      bc.setToClipboard(code);
      bc.popup("Successfully decoded into clipboard:\n"+code);
      System.out.println("Decoded: "+code);
    } catch(Exception e) {
      bc.popup("Decoding failed!");
      e.printStackTrace();
    }
  }

  /**
   * Get a BufferedImage from the clipboard
   * @return Returns the image
   * @throws Exception If the clipboard contains no image
   */
  private BufferedImage getImageFromClipboard() throws Exception {
    Clipboard cp = Toolkit.getDefaultToolkit().getSystemClipboard();
    return (BufferedImage)cp.getData(DataFlavor.imageFlavor);
        
  }
	
  /**
   * Decodes a barcode from the passed bufferedImage
   * @param img Image to decode
   * @return decoded barcode
   * @throws Exception If unable to decode for some reason
   */
  private String decodeBarcode(BufferedImage img) throws Exception {
    LuminanceSource source = new BufferedImageLuminanceSource(img);
    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));		
    MultiFormatReader barcodeReader = new MultiFormatReader();		
    Result r = barcodeReader.decode(bitmap);
    return String.valueOf(r.getText());
  }
	
  /**
   * Sets the passed String to the clipboard
   * @param code Code to be set to the clipboard
   */
  private void setToClipboard(String code) {
    StringSelection stringSelection = new StringSelection (code);
    Clipboard cp = Toolkit.getDefaultToolkit ().getSystemClipboard ();
    cp.setContents (stringSelection, null);
  }
	
  /**
   * Displays a popup with the passed message
   * @param message
   */
  private void popup(String message) {
    JOptionPane.showMessageDialog(null, message);
  }
}
