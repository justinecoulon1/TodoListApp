package com.coulon.todo.app.utils.ui;

import com.coulon.todo.app.utils.ui.images.ImageUtils;
import com.coulon.todo.app.utils.ui.images.UiIcons;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ButtonUtils {

    public static JButton createBigButtonWithBorder(UiIcons icon) {
        JButton button = createBigButtonWithoutBorder(icon);
        button.setBorder(BorderFactory.createLineBorder(TodoListAppConstants.BORDERS_AND_SEPARATOR_COLOR, 3));
        return button;
    }

    public static JButton createBigButtonWithoutBorder(UiIcons icon) {
        JButton button = new JButton();
        BufferedImage image = ImageUtils.resizeImage(icon.getImage(), 35, 35);
        button.setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        button.setIcon(new ImageIcon(image));
        button.setBorder(null);
        button.setFocusable(false);
        button.addMouseListener(new ButtonDefaultMouseAdapter(button, TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR, TodoListAppConstants.DARK_HIGHLIGHT_COLOR));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    public static JButton createSmallButtonWithoutBorder(UiIcons icon) {
        JButton button = new JButton();
        BufferedImage image = ImageUtils.resizeImage(icon.getImage(), 15, 15);
        button.setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        button.setIcon(new ImageIcon(image));
        button.setBorder(null);
        button.setFocusable(false);
        button.addMouseListener(new ButtonDefaultMouseAdapter(button, TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR, TodoListAppConstants.DARK_HIGHLIGHT_COLOR));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    public static JButton createSmallButtonWithBorder(UiIcons icon) {
        JButton button = createSmallButtonWithoutBorder(icon);
        button.setBorder(BorderFactory.createLineBorder(TodoListAppConstants.BORDERS_AND_SEPARATOR_COLOR, 2));
        return button;
    }

}
