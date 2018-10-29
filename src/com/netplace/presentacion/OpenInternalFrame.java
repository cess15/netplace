/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netplace.presentacion;

import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author Cess
 */
public class OpenInternalFrame {

    public static void charge(JInternalFrame frm, JDesktopPane desktop) {
        desktop.add(frm);
        Dimension desktopSize = desktop.getSize();
        Dimension FrameSize = frm.getSize();
        frm.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        frm.toFront();
        frm.setVisible(true);
    }
}
