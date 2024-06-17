package com.coulon.todo.app.gui.panels.tabs.todolist;

import com.coulon.todo.app.common.dto.TodoListDto;
import com.coulon.todo.app.gui.AppPanels;
import com.coulon.todo.app.gui.panels.tabs.todolist.popup.TodoListPopUpManager;
import com.coulon.todo.app.utils.external.BackEndRequestProcessor;
import com.coulon.todo.app.utils.ui.*;
import com.coulon.todo.app.utils.ui.DisplayMode;
import com.coulon.todo.app.utils.ui.images.ImageUtils;
import com.coulon.todo.app.utils.ui.images.UiIcons;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class TodoListNamePanel extends JPanel {

    private final JTextField todoListNameTextField;
    private JButton cancelUpdateButton;
    private JButton validateUpdateTodoListButton;
    private JButton startUpdateTodoListButton;
    private JButton openTodoListButton;
    private TodoListDto todoListDto;

    public TodoListNamePanel() {
        this.setLayout(new MigLayout("fill, nogrid, ins 0"));
        this.setBackground(TodoListAppConstants.PANEL_BACKGROUND_COLOR);

        JLabel homeButtonLabel = new JLabel();
        BufferedImage todoImage = ImageUtils.resizeImage(UiIcons.HOME.getImage(), 28, 28);
        ImageIcon imageIcon = new ImageIcon(todoImage);
        homeButtonLabel.setIcon(imageIcon);
        homeButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
        homeButtonLabel.setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        homeButtonLabel.setOpaque(true);
        homeButtonLabel.setBorder(BorderFactory.createLineBorder(TodoListAppConstants.BORDERS_AND_SEPARATOR_COLOR, 3));
        homeButtonLabel.setToolTipText("Home");
        homeButtonLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        homeButtonLabel.addMouseListener(new ButtonDefaultMouseAdapter(homeButtonLabel, TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR, TodoListAppConstants.DARK_HIGHLIGHT_COLOR));
        homeButtonLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                AppPanels.MAIN_PANEL.setDisplayedPanel(AppPanels.TODO_LISTS_HOME_PANEL);
                AppPanels.TODO_LISTS_HOME_PANEL.refresh();
            }
        });
        this.add(homeButtonLabel, "h 70!, w 50!, alignx left");

        todoListNameTextField = TextFieldUtils.createTextFieldWithBorder(getBackground());
        this.add(todoListNameTextField, "aligny center, alignx left, gapbefore 10, gapafter 10, growx, h 40!");

        JPanel buttonPanel = createButtonPanel();
        this.add(buttonPanel, "aligny top, alignx right");
    }

    private void handleCancelUpdateButton(ActionEvent actionEvent) {
        AppPanels.TODO_LIST_MAIN_PANEL.updateTodoList(todoListDto, DisplayMode.READ);
        if (super.getRootPane() != null) {
            super.getRootPane().updateUI();
        }
    }

    private void openTodoList(ActionEvent actionEvent) {
        TodoListPopUpManager.INSTANCE.openTodoPopUp(todoListDto);
    }

    private void handleStartUpdateButton(ActionEvent actionEvent) {
        AppPanels.TODO_LIST_MAIN_PANEL.updateDisplayMode(DisplayMode.UPDATE);
    }

    private void handleValidateUpdateButton(ActionEvent actionEvent) {
        todoListDto.setName(todoListNameTextField.getText());
        todoListDto.setTodoListElementDtos(AppPanels.TODO_LIST_MAIN_PANEL.getTodoListElementsListDisplayPanel().getUpdatedTodoListElementDtos());
        TodoListDto updatedTodoListDto = BackEndRequestProcessor.INSTANCE.updateTodoList(todoListDto);
        TodoListPopUpManager.INSTANCE.updateTodoPopUp(updatedTodoListDto);
        AppPanels.TODO_LIST_MAIN_PANEL.updateTodoList(updatedTodoListDto, DisplayMode.READ);
    }

    public void updateTodoList(TodoListDto todoListDto) {
        this.todoListDto = todoListDto;
        todoListNameTextField.setText(todoListDto.getName());
    }

    public void updateDisplayMode(DisplayMode displayMode) {
        boolean isUpdate = displayMode == DisplayMode.UPDATE;
        todoListNameTextField.setEditable(isUpdate);
        validateUpdateTodoListButton.setVisible(isUpdate);
        cancelUpdateButton.setVisible(isUpdate);
        startUpdateTodoListButton.setVisible(!isUpdate);
        openTodoListButton.setVisible(!isUpdate);

        todoListNameTextField.setFocusable(isUpdate);

        if (isUpdate) {
            todoListNameTextField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(TodoListAppConstants.BORDERS_AND_SEPARATOR_COLOR), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        } else {
            todoListNameTextField.setBorder(null);
        }

        if (super.getRootPane() != null) {
            super.getRootPane().updateUI();
        }
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new MigLayout("ins 2, gap 2, hidemode 2"));
        buttonPanel.setBackground(getBackground());
        validateUpdateTodoListButton = ButtonUtils.createSmallButtonWithBorder(UiIcons.VALIDATE);
        validateUpdateTodoListButton.addActionListener(this::handleValidateUpdateButton);
        validateUpdateTodoListButton.setToolTipText("Save");
        buttonPanel.add(validateUpdateTodoListButton, "aligny top, alignx right, w 28!, h 30!, wrap");

        startUpdateTodoListButton = ButtonUtils.createSmallButtonWithBorder(UiIcons.EDIT);
        startUpdateTodoListButton.addActionListener(this::handleStartUpdateButton);
        startUpdateTodoListButton.setToolTipText("Edit");
        buttonPanel.add(startUpdateTodoListButton, "aligny top, alignx right, w 28!, h 30!, wrap");

        openTodoListButton = ButtonUtils.createSmallButtonWithBorder(UiIcons.OPEN);
        openTodoListButton.addActionListener(this::openTodoList);
        openTodoListButton.setToolTipText("Open todo list");
        buttonPanel.add(openTodoListButton, "aligny bottom, alignx right, w 28!, h 30!, wrap");

        cancelUpdateButton = ButtonUtils.createSmallButtonWithBorder(UiIcons.DELETE);
        cancelUpdateButton.addActionListener(this::handleCancelUpdateButton);
        cancelUpdateButton.setToolTipText("Cancel edit");
        buttonPanel.add(cancelUpdateButton, "aligny bottom, alignx right, w 28!, h 30!");

        return buttonPanel;
    }

}
