package fi.leif.java.screenshot.decoder.tools;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;

public class ClipboardUtil {

    public static BufferedImage getImageFromClipboard() throws Exception {
        Clipboard cp = Toolkit.getDefaultToolkit().getSystemClipboard();
        return (BufferedImage) cp.getData(DataFlavor.imageFlavor);

    }

    public static void setTextToClipboard(String text) {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard cp = Toolkit.getDefaultToolkit().getSystemClipboard();
        cp.setContents(stringSelection, null);
    }
}
