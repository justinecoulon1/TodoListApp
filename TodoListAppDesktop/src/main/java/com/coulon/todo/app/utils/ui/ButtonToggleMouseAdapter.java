package com.coulon.todo.app.utils.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class ButtonToggleMouseAdapter extends MouseAdapter {

    private final JComponent component;
    private final Color color;
    private final Color highlightColor;
    private boolean isToggleOn = false;

    public ButtonToggleMouseAdapter(JComponent component, Color color, Color highlightColor) {
        this.component = component;
        this.color = color;
        this.highlightColor = highlightColor;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (isToggleOn) {
            component.setBackground(color);
        } else {
            component.setBackground(highlightColor);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (isToggleOn) {
            component.setBackground(highlightColor);
        } else {
            component.setBackground(color);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        isToggleOn = !isToggleOn;
        if (isToggleOn) {
            component.setBackground(highlightColor);
            onToggleOn();
        } else {
            component.setBackground(color);
            onToggleOff();
        }
    }

    protected abstract void onToggleOn();

    protected abstract void onToggleOff();
}
