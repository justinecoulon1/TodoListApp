package com.coulon.todo.app;

import com.coulon.todo.app.gui.AppPanels;
import com.coulon.todo.app.utils.ui.UiManagerInitializer;
import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        UiManagerInitializer.initialize();
        ToolTipManager.sharedInstance().setInitialDelay(500);
        AppPanels.MAIN_FRAME.setVisible(true);
    }

}
