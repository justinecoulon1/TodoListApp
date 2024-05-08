package com.coulon.todo.app.gui.panels.home;

import com.coulon.todo.app.utils.ui.ButtonUtils;
import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import com.coulon.todo.app.utils.ui.images.ImageUtils;
import com.coulon.todo.app.utils.ui.images.UiIcons;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class TodoListPanel extends JPanel {

    private final JTextField todoListNameTextField;
    private final JButton deleteTodoListElement;
    private final JButton validateTodoListElement;
    private final JPanel buttonPanel;

    public TodoListPanel() {
        this.setLayout(new MigLayout("fill, nogrid, ins 0, gap 0"));
        this.setBackground(TodoListAppConstants.PANEL_BACKGROUND_COLOR);

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                buttonPanel.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonPanel.setVisible(false);
            }
        };

        JLabel todoImageLabel = new JLabel();
        todoImageLabel.setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        todoImageLabel.setOpaque(true);
        BufferedImage todoImage = ImageUtils.resizeImage(UiIcons.NORMAL_TODO.getImage(), 32, 32);
        ImageIcon imageIcon = new ImageIcon(todoImage);
        todoImageLabel.setIcon(imageIcon);
        todoImageLabel.setBorder(BorderFactory.createLineBorder(TodoListAppConstants.BORDERS_AND_SEPARATORS, 3));
        this.add(todoImageLabel, "aligny center, alignx left, gapbefore 10");

        todoListNameTextField = new JTextField();
        todoListNameTextField.setBackground(getBackground());
        todoListNameTextField.setFont(TodoListAppConstants.DEFAULT_FONT);
        todoListNameTextField.setForeground(TodoListAppConstants.DARK_FONT_COLOR);
        todoListNameTextField.setCaretColor(TodoListAppConstants.DARK_FONT_COLOR);
        todoListNameTextField.setHorizontalAlignment(JTextField.LEFT);
        todoListNameTextField.setOpaque(true);
        todoListNameTextField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(TodoListAppConstants.BORDERS_AND_SEPARATORS), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        todoListNameTextField.addMouseListener(mouseAdapter);
        this.add(todoListNameTextField,"aligny center, alignx left, gapbefore 10, w 275!, h 40!");

        buttonPanel = new JPanel(new MigLayout("ins 0, gap 0"));
        buttonPanel.setVisible(false);
        buttonPanel.setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        validateTodoListElement = ButtonUtils.createSmallButton(UiIcons.VALIDATE);
        validateTodoListElement.addActionListener(this::handleDeleteButton);
        validateTodoListElement.addMouseListener(mouseAdapter);
        buttonPanel.add(validateTodoListElement, "aligny top, alignx right, w 25!, h 20!");

        deleteTodoListElement = ButtonUtils.createSmallButton(UiIcons.DELETE);
        deleteTodoListElement.addActionListener(this::handleDeleteButton);
        deleteTodoListElement.addMouseListener(mouseAdapter);
        buttonPanel.add(deleteTodoListElement, "aligny top, alignx right, w 25!, h 20!");

        this.add(buttonPanel,"aligny top, alignx right");

        this.addMouseListener(mouseAdapter);

    }

    private void handleDeleteButton(ActionEvent actionEvent) {

    }
}
