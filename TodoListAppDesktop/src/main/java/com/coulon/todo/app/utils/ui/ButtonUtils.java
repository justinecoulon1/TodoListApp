package com.coulon.todo.app.utils.ui;

import com.coulon.todo.app.utils.ui.images.ImageUtils;
import com.coulon.todo.app.utils.ui.images.UiIcons;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class ButtonUtils {

    public static JButton createBigButton(UiIcons icon) {
        JButton button = new JButton();
        BufferedImage image = ImageUtils.resizeImage(icon.getImage(), 35, 35);
        button.setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        button.setIcon(new ImageIcon(image));
        button.setBorder(BorderFactory.createLineBorder(TodoListAppConstants.BORDERS_AND_SEPARATORS, 3));
        button.setFocusable(false);
        return button;
    }

    public static JButton createBigButtonWithoutBorder(UiIcons icon) {
        JButton button = new JButton();
        BufferedImage image = ImageUtils.resizeImage(icon.getImage(), 35, 35);
        button.setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        button.setIcon(new ImageIcon(image));
        button.setBorder(null);
        button.setFocusable(false);
        return button;
    }

    public static JButton createSmallButton(UiIcons icon) {
        JButton button = new JButton();
        BufferedImage image = ImageUtils.resizeImage(icon.getImage(), 15, 15);
        button.setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        button.setIcon(new ImageIcon(image));
        button.setBorder(null);
        button.setFocusable(false);
        return button;
    }

}
