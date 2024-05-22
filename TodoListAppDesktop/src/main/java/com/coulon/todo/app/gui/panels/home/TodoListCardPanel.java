package com.coulon.todo.app.gui.panels.home;

import com.coulon.todo.app.common.dto.TodoListDto;
import com.coulon.todo.app.gui.AppPanels;
import com.coulon.todo.app.utils.external.BackEndRequestProcessor;
import com.coulon.todo.app.utils.ui.ButtonUtils;
import com.coulon.todo.app.utils.ui.DisplayMode;
import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import com.coulon.todo.app.utils.ui.images.ImageUtils;
import com.coulon.todo.app.utils.ui.images.UiIcons;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class TodoListCardPanel extends JPanel {

    private final JPanel buttonPanel;
    private final TodoListDto todoListDto;
    private final JTextField todoListNameTextField;
    private final ListDisplayPanel listDisplayPanel;

    public TodoListCardPanel(TodoListDto todoListDto, ListDisplayPanel listDisplayPanel) {
        this.setLayout(new MigLayout("fill, nogrid, ins 0, gap 0"));
        this.setBackground(TodoListAppConstants.PANEL_BACKGROUND_COLOR);
        MouseAdapter highlightMouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                TodoListCardPanel.this.setBackground(TodoListAppConstants.LIGHT_HIGHLIGHT_COLOR);
                todoListNameTextField.setBackground(TodoListAppConstants.LIGHT_HIGHLIGHT_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                TodoListCardPanel.this.setBackground(TodoListAppConstants.PANEL_BACKGROUND_COLOR);
                todoListNameTextField.setBackground(TodoListAppConstants.PANEL_BACKGROUND_COLOR);
            }
        };
        this.addMouseListener(highlightMouseAdapter);

        this.todoListDto = todoListDto;
        this.listDisplayPanel = listDisplayPanel;

        MouseAdapter buttonPanelVisibilityMouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                buttonPanel.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonPanel.setVisible(false);
            }
        };

        MouseAdapter clickMouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                openTodoList(DisplayMode.READ);
            }
        };

        JLabel todoImageLabel = new JLabel();
        todoImageLabel.setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        todoImageLabel.setOpaque(true);
        BufferedImage todoImage = ImageUtils.resizeImage(UiIcons.NORMAL_TODO.getImage(), 32, 32);
        ImageIcon imageIcon = new ImageIcon(todoImage);
        todoImageLabel.setIcon(imageIcon);
        todoImageLabel.setBorder(BorderFactory.createLineBorder(TodoListAppConstants.BORDERS_AND_SEPARATOR_COLOR, 3));
        this.add(todoImageLabel, "aligny center, alignx left, gapbefore 10");

        todoListNameTextField = new JTextField();
        todoListNameTextField.setText(todoListDto.getName());
        todoListNameTextField.setBackground(getBackground());
        todoListNameTextField.setFont(TodoListAppConstants.DEFAULT_FONT);
        todoListNameTextField.setForeground(TodoListAppConstants.DARK_FONT_COLOR);
        todoListNameTextField.setCaretColor(TodoListAppConstants.DARK_FONT_COLOR);
        todoListNameTextField.setHorizontalAlignment(JTextField.LEFT);
        todoListNameTextField.setOpaque(true);
        todoListNameTextField.setEditable(false);
        todoListNameTextField.setFocusable(false);
        todoListNameTextField.setBorder(null);
        todoListNameTextField.addMouseListener(buttonPanelVisibilityMouseAdapter);
        todoListNameTextField.addMouseListener(clickMouseAdapter);
        todoListNameTextField.addMouseListener(highlightMouseAdapter);
        this.add(todoListNameTextField, "aligny center, alignx left, gapbefore 10, growx, h 40!");

        buttonPanel = new JPanel(new MigLayout("ins 0, gap 0"));
        buttonPanel.setVisible(false);
        buttonPanel.setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);

        JButton deleteTodoListButton = ButtonUtils.createSmallButtonWithoutBorder(UiIcons.DELETE);
        deleteTodoListButton.addActionListener(this::handleDeleteButton);
        deleteTodoListButton.addMouseListener(buttonPanelVisibilityMouseAdapter);
        deleteTodoListButton.setToolTipText("Delete todo list");
        buttonPanel.add(deleteTodoListButton, "aligny top, alignx right, w 25!, h 20!");

        this.add(buttonPanel, "aligny top, alignx right");

        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.addMouseListener(buttonPanelVisibilityMouseAdapter);
        this.addMouseListener(clickMouseAdapter);
    }

    private void handleDeleteButton(ActionEvent actionEvent) {
        BackEndRequestProcessor.INSTANCE.deleteTodoList(todoListDto.getId());
        listDisplayPanel.deleteTodoListCardPanel(todoListDto);
    }

    public TodoListDto getTodoListDto() {
        return todoListDto;
    }

    public void openTodoList(DisplayMode displayMode) {
        TodoListDto toUpdateTodoList = BackEndRequestProcessor.INSTANCE.getTodoListById(todoListDto.getId());
        AppPanels.TODO_LIST_MAIN_PANEL.updateTodoList(toUpdateTodoList, displayMode);
        AppPanels.MAIN_PANEL.setDisplayedPanel(AppPanels.TODO_LIST_MAIN_PANEL);
    }

}
