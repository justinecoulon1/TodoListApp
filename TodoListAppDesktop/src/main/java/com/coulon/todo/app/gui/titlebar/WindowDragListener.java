package com.coulon.todo.app.gui.titlebar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class WindowDragListener implements MouseListener, MouseMotionListener {

    private Point offset = new Point();
    private JFrame frame;
    private final JComponent component;

    public WindowDragListener(JComponent component) {
        this.component = component;
    }

    private JFrame getFrame() {
        if (frame == null) {
            frame = (JFrame) SwingUtilities.getWindowAncestor(component);
        }
        return frame;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        offset = getFrame().getMousePosition();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point position = MouseInfo.getPointerInfo().getLocation();
        getFrame().setLocation(position.x - offset.x, position.y - offset.y);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
