package com.coulon.todo.app.gui.panels.todolisttab;

import com.coulon.todo.app.common.dto.TodoListDto;
import com.coulon.todo.app.common.dto.TodoListElementDto;
import com.coulon.todo.app.gui.AppPanels;
import com.coulon.todo.app.utils.external.BackEndRequestProcessor;
import com.coulon.todo.app.utils.ui.ButtonDefaultMouseAdapter;
import com.coulon.todo.app.utils.ui.ButtonUtils;
import com.coulon.todo.app.utils.ui.DisplayMode;
import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import com.coulon.todo.app.utils.ui.images.ImageUtils;
import com.coulon.todo.app.utils.ui.images.UiIcons;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class TodoListNamePanel extends JPanel {

    private final JTextField todoListNameTextField;
    private final JButton addTodoListElementButton;
    private final JButton validateUpdateTodoListButton;
    private final JButton startUpdateTodoListButton;

    private TodoListDto todoListDto;
    private final TodoListMainPanel todoListMainPanel;

    public TodoListNamePanel(TodoListDto todoListDto, DisplayMode displayMode, TodoListMainPanel todoListMainPanel) {
        this.setLayout(new MigLayout("fill, nogrid, ins 0"));
        this.setBackground(TodoListAppConstants.PANEL_BACKGROUND_COLOR);

        this.todoListDto = todoListDto;
        this.todoListMainPanel = todoListMainPanel;

        JLabel homeButtonLabel = new JLabel();
        BufferedImage todoImage = ImageUtils.resizeImage(UiIcons.HOME.getImage(), 28, 28);
        ImageIcon imageIcon = new ImageIcon(todoImage);
        homeButtonLabel.setIcon(imageIcon);
        homeButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
        homeButtonLabel.setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        homeButtonLabel.setOpaque(true);
        homeButtonLabel.setBorder(BorderFactory.createLineBorder(TodoListAppConstants.BORDERS_AND_SEPARATOR_COLOR, 3));
        homeButtonLabel.addMouseListener(new ButtonDefaultMouseAdapter(homeButtonLabel));
        homeButtonLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                AppPanels.MAIN_PANEL.setDisplayedPanel(AppPanels.HOME_MAIN_PANEL);
                AppPanels.HOME_MAIN_PANEL.refresh();
            }
        });
        this.add(homeButtonLabel, "h 70!, w 50!, alignx left");

        todoListNameTextField = new JTextField();
        todoListNameTextField.setText(todoListDto.getName());
        todoListNameTextField.setBackground(getBackground());
        todoListNameTextField.setFont(TodoListAppConstants.DEFAULT_FONT);
        todoListNameTextField.setForeground(TodoListAppConstants.DARK_FONT_COLOR);
        todoListNameTextField.setCaretColor(TodoListAppConstants.DARK_FONT_COLOR);
        todoListNameTextField.setHorizontalAlignment(JTextField.LEFT);
        todoListNameTextField.setOpaque(true);
        this.add(todoListNameTextField, "aligny center, alignx left, gapbefore 10, gapafter 10, growx, h 40!");

        JPanel buttonPanel = new JPanel(new MigLayout("ins 2, gap 2, hidemode 2"));
        buttonPanel.setBackground(getBackground());

        validateUpdateTodoListButton = ButtonUtils.createSmallButtonWithBorder(UiIcons.VALIDATE);
        validateUpdateTodoListButton.addActionListener(this::handleValidateUpdateButton);
        buttonPanel.add(validateUpdateTodoListButton, "aligny top, alignx right, w 28!, h 30!, wrap");

        startUpdateTodoListButton = ButtonUtils.createSmallButtonWithBorder(UiIcons.EDIT);
        startUpdateTodoListButton.addActionListener(this::handleStartUpdateButton);
        buttonPanel.add(startUpdateTodoListButton, "aligny top, alignx right, w 28!, h 30!, wrap");

        addTodoListElementButton = ButtonUtils.createSmallButtonWithBorder(UiIcons.PLUS);
        addTodoListElementButton.addActionListener(this::handleAddTodoListElementButton);
        buttonPanel.add(addTodoListElementButton, "aligny bottom, alignx right, w 28!, h 30!");

        this.add(buttonPanel, "aligny top, alignx right");

        updateDisplayMode(displayMode);

    }

    private void handleStartUpdateButton(ActionEvent actionEvent) {
        todoListMainPanel.updateDisplayMode(DisplayMode.UPDATE);
    }

    private void handleValidateUpdateButton(ActionEvent actionEvent) {
        todoListDto.setName(todoListNameTextField.getText());
        todoListDto.setTodoListElementDtos(todoListMainPanel.getTodoListElementsListDisplayPanel().getUpdatedTodoListElementDtos());
        TodoListDto updatedTodoListDto = BackEndRequestProcessor.INSTANCE.updateTodoList(todoListDto);
        updateTodoList(updatedTodoListDto);
        todoListMainPanel.getTodoListElementsListDisplayPanel().updateTodoListElementsDto(updatedTodoListDto.getTodoListElementDtos());
        todoListMainPanel.updateDisplayMode(DisplayMode.READ);
    }

    private void handleAddTodoListElementButton(ActionEvent actionEvent) {
        todoListMainPanel.getTodoListElementsListDisplayPanel().addTodoListElementCardPanel(new TodoListElementDto(), DisplayMode.UPDATE);
    }

    public void updateTodoList(TodoListDto todoListDto) {
        this.todoListDto = todoListDto;
        todoListNameTextField.setText(todoListDto.getName());
    }

    public void updateDisplayMode(DisplayMode displayMode) {
        boolean isUpdate = displayMode == DisplayMode.UPDATE;
        todoListNameTextField.setEditable(isUpdate);
        validateUpdateTodoListButton.setVisible(isUpdate);
        startUpdateTodoListButton.setVisible(!isUpdate);
        addTodoListElementButton.setVisible(isUpdate);

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

}
