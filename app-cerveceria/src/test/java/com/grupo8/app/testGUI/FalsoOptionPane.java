package com.grupo8.app.testGUI;

import com.grupo8.app.gui.InterfazOptionPanel;
import java.awt.Component;

public class FalsoOptionPane implements InterfazOptionPanel {
    private String mensaje = null;

    public FalsoOptionPane() {
        super();
    }

    @Override
    public void ShowMessage(Component parent, String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
