package fi.leif.java.screenshot.decoder.impl;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import fi.leif.java.screenshot.decoder.Screenshot;
import fi.leif.java.screenshot.decoder.tools.ClipboardUtil;
import fi.leif.java.screenshot.decoder.tools.Decoder;
import fi.leif.java.screenshot.decoder.tools.Popup;

public class ScreenshotImpl implements Screenshot {

    @Override
    public void handleSelection(Rectangle r) {
    }

    @Override
    public void handleImage(BufferedImage img) {
        try {
            String code = Decoder.decodeImage(img);
            Popup.show("Code was decoded successfully into clipboard!\nCode: "+code);
            ClipboardUtil.setTextToClipboard(code);
            System.out.println(code);
        } catch(Exception e) {
            Popup.show("Unable to decode code");
            e.printStackTrace();
        }        
    }

}
