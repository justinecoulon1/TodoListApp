package com.coulon.todo.app.gui.titlebar;

import com.coulon.todo.app.gui.AppPanels;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class WindowDragListener implements MouseListener, MouseMotionListener {

    private Point offset = new Point();

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        offset = AppPanels.MAIN_FRAME.getMousePosition();
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
        AppPanels.MAIN_FRAME.setLocation(position.x - offset.x, position.y - offset.y);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
