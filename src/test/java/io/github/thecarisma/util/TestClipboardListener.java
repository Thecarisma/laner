package io.github.thecarisma.util;

import io.github.thecarisma.laner.LanerListener;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public class TestClipboardListener {

    public static void main(String[] args) {
        new ClipboardListener(new LanerListener() {
            @Override
            public void report(Object o) {
                if (o instanceof ClipboardListener.ClipboardStatus) {
                    String tempText;
                    Transferable trans = ((ClipboardListener.ClipboardStatus) o).transferable;

                    try {
                        if (trans != null && trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                            tempText = (String) trans.getTransferData(DataFlavor.stringFlavor);
                            System.out.println(tempText);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).run();
    }

}
