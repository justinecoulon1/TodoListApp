package com.coulon.todo.app.utils.ui;

import com.coulon.todo.app.utils.ui.images.ImageUtils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LabelUtils {

    public static JLabel createTransparentTextLabel(String text, Font font, Color backgroundColor, Color foregroundColor) {
        JLabel label = createTextLabel(text, font, backgroundColor, foregroundColor);
        label.setOpaque(false);
        return label;
    }

    public static JLabel createTextLabel(String text, Font font, Color backgroundColor, Color foregroundColor) {
        JLabel label = new JLabel();
        label.setText(text);
        label.setFont(font);
        label.setBackground(backgroundColor);
        label.setForeground(foregroundColor);
        label.setHorizontalAlignment(JTextField.LEFT);
        label.setOpaque(true);
        label.setFocusable(false);
        label.setBorder(null);
        return label;
    }

    public static JLabel createImageLabel(BufferedImage image, int size) {
        JLabel label = new JLabel();
        BufferedImage imageLabel = ImageUtils.resizeImage(image, size, size);
        ImageIcon imageIcon = new ImageIcon(imageLabel);
        label.setIcon(imageIcon);
        return label;
    }

    public static JLabel createImageLabelWithBorders(BufferedImage image, int size, Color background, Border border) {
        JLabel label = new JLabel();
        label.setBackground(background);
        label.setOpaque(true);
        BufferedImage imageLabel = ImageUtils.resizeImage(image, size, size);
        ImageIcon imageIcon = new ImageIcon(imageLabel);
        label.setIcon(imageIcon);
        label.setBorder(border);
        return label;
    }

}
