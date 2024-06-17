package com.coulon.todo.app.utils.ui;

import javax.swing.*;
import java.awt.*;

public class TextFieldUtils {

    public static JTextField createTextFieldWithBorderAndText(Color background, String text) {
        JTextField textField = createTextFieldWithBorder(background);
        textField.setText(text);
        return textField;
    }

    public static JTextField createTextFieldWithoutBorderWithText(Color background, String text) {
        JTextField textField = createTextFieldWithoutBorder(background);
        textField.setText(text);
        return textField;
    }

    public static JTextField createTextFieldWithBorder(Color background) {
        JTextField textField = createTextFieldWithoutBorder(background);
        textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(TodoListAppConstants.BORDERS_AND_SEPARATOR_COLOR), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        return textField;
    }

    public static JTextField createTextFieldWithoutBorder(Color background) {
        JTextField textField = new JTextField();
        textField.setBackground(background);
        textField.setFont(TodoListAppConstants.DEFAULT_FONT);
        textField.setForeground(TodoListAppConstants.DARK_FONT_COLOR);
        textField.setCaretColor(TodoListAppConstants.DARK_FONT_COLOR);
        textField.setHorizontalAlignment(JTextField.LEFT);
        textField.setOpaque(true);
        textField.setBorder(null);
        return textField;
    }

    public static JPasswordField createPasswordTextFieldWithBorder(Color background) {
        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setBackground(background);
        passwordTextField.setFont(TodoListAppConstants.DEFAULT_FONT);
        passwordTextField.setForeground(TodoListAppConstants.DARK_FONT_COLOR);
        passwordTextField.setCaretColor(TodoListAppConstants.DARK_FONT_COLOR);
        passwordTextField.setHorizontalAlignment(JTextField.LEFT);
        passwordTextField.setOpaque(true);
        passwordTextField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(TodoListAppConstants.BORDERS_AND_SEPARATOR_COLOR), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        return passwordTextField;
    }
}
