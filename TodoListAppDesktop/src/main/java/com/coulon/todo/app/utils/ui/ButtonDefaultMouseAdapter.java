package com.coulon.todo.app.utils.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonDefaultMouseAdapter extends MouseAdapter {

    private final JComponent component;
    private final Color color;
    private final Color highlightColor;

    public ButtonDefaultMouseAdapter(JComponent component, Color color, Color highlightColor) {
        this.component = component;
        this.color = color;
        this.highlightColor = highlightColor;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        component.setBackground(highlightColor);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        component.setBackground(color);
    }

}
