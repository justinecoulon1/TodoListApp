package com.coulon.todo.app.gui.panels.todolisttab;

import com.coulon.todo.app.common.dto.TodoListElementDto;
import com.coulon.todo.app.utils.ui.ButtonUtils;
import com.coulon.todo.app.utils.ui.DisplayMode;
import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import com.coulon.todo.app.utils.ui.images.ImageUtils;
import com.coulon.todo.app.utils.ui.images.UiIcons;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class TodoListElementCardPanel extends JPanel {

    private final TodoListElementDto todoListElementDto;
    private final JTextField todoListElementDescriptionTextField;
    private final JButton deleteTodoListButton;
    private final TodoListElementsListDisplayPanel todoListElementsListDisplayPanel;
    private DisplayMode displayMode;

    public TodoListElementCardPanel(TodoListElementDto todoListElementDto, DisplayMode displayMode, TodoListElementsListDisplayPanel todoListElementsListDisplayPanel) {
        this.setLayout(new MigLayout("fill, nogrid, ins 0, gap 0"));
        this.setBackground(TodoListAppConstants.PANEL_BACKGROUND_COLOR);

        this.todoListElementDto = todoListElementDto;
        this.displayMode = displayMode;
        this.todoListElementsListDisplayPanel = todoListElementsListDisplayPanel;

        JLabel todoImageLabel = new JLabel();
        todoImageLabel.setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        todoImageLabel.setOpaque(true);
        BufferedImage todoImage = ImageUtils.resizeImage(UiIcons.NORMAL_TODO.getImage(), 32, 32);
        ImageIcon imageIcon = new ImageIcon(todoImage);
        todoImageLabel.setIcon(imageIcon);
        todoImageLabel.setBorder(BorderFactory.createLineBorder(TodoListAppConstants.BORDERS_AND_SEPARATOR_COLOR, 3));
        this.add(todoImageLabel, "aligny center, alignx left, gapbefore 10");

        todoListElementDescriptionTextField = new JTextField();
        todoListElementDescriptionTextField.setText(todoListElementDto.getDescription());
        todoListElementDescriptionTextField.setBackground(getBackground());
        todoListElementDescriptionTextField.setFont(TodoListAppConstants.DEFAULT_FONT);
        todoListElementDescriptionTextField.setForeground(TodoListAppConstants.DARK_FONT_COLOR);
        todoListElementDescriptionTextField.setCaretColor(TodoListAppConstants.DARK_FONT_COLOR);
        todoListElementDescriptionTextField.setHorizontalAlignment(JTextField.LEFT);
        todoListElementDescriptionTextField.setOpaque(true);
        this.add(todoListElementDescriptionTextField, "aligny center, alignx left, gapbefore 10, growx, h 40!");

        JPanel buttonPanel = new JPanel(new MigLayout("ins 0, gap 0, hidemode 2"));
        buttonPanel.setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);

        deleteTodoListButton = ButtonUtils.createSmallButtonWithoutBorder(UiIcons.DELETE);
        deleteTodoListButton.addActionListener(this::handleDeleteButton);
        buttonPanel.add(deleteTodoListButton, "aligny top, alignx right, w 25!, h 20!");

        this.add(buttonPanel, "aligny top, alignx right");

        updateDisplayMode(displayMode);
    }

    private void handleDeleteButton(ActionEvent actionEvent) {
        todoListElementsListDisplayPanel.deleteTodoListElementCardPanel(this);
    }

    public TodoListElementDto getTodoListElementDto() {
        return todoListElementDto;
    }

    public void updateDisplayMode(DisplayMode displayMode) {
        this.displayMode = displayMode;
        boolean isUpdate = displayMode == DisplayMode.UPDATE;
        todoListElementDescriptionTextField.setEditable(isUpdate);
        todoListElementDescriptionTextField.setFocusable(isUpdate);
        deleteTodoListButton.setVisible(isUpdate);

        if (isUpdate) {
            todoListElementDescriptionTextField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(TodoListAppConstants.BORDERS_AND_SEPARATOR_COLOR), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        } else {
            todoListElementDescriptionTextField.setBorder(null);
        }

        if (super.getRootPane() != null) {
            super.getRootPane().updateUI();
        }
    }

    public JTextField getTodoListElementDescriptionTextField() {
        return todoListElementDescriptionTextField;
    }
}
