package com.coulon.todo.app.utils.ui;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonDefaultMouseAdapter extends MouseAdapter {

    private final JComponent component;

    public ButtonDefaultMouseAdapter(JComponent component) {
        this.component = component;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        component.setBackground(TodoListAppConstants.HIGHLIGHT_COLOR);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        component.setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
    }

}
