package com.coulon.todo.app.gui.panels.tabs.todolist;

import com.coulon.todo.app.common.dto.TodoListElementDto;
import com.coulon.todo.app.utils.ui.DisplayMode;
import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TodoListElementsListDisplayPanel extends JPanel {

    private final List<TodoListElementCardPanel> todoListElementCardPanels = new ArrayList<>();
    private final JPanel todoListElementsListContainerPanel;

    public TodoListElementsListDisplayPanel() {
        this.setLayout(new MigLayout("fill, nogrid, ins 0"));
        this.setBackground(TodoListAppConstants.GENERAL_BACKGROUND_COLOR);

        todoListElementsListContainerPanel = new JPanel(new MigLayout("fillx, ins 0, gap 0"));
        todoListElementsListContainerPanel.setBackground(getBackground());
        JScrollPane scrollPane = new JScrollPane(todoListElementsListContainerPanel);
        scrollPane.setHorizontalScrollBar(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.getVerticalScrollBar().setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        scrollPane.setBorder(null);
        this.add(scrollPane, "grow, push");
    }

    public void updateDisplayMode(DisplayMode displayMode) {
        for (TodoListElementCardPanel todoListElementCardPanel : todoListElementCardPanels) {
            todoListElementCardPanel.updateDisplayMode(displayMode);
        }
    }

    public void addTodoListElementCardPanel(TodoListElementDto todoListElementDto, DisplayMode displayMode) {
        TodoListElementCardPanel todoListElementCardPanel = new TodoListElementCardPanel(todoListElementDto, displayMode, this);
        todoListElementCardPanels.add(todoListElementCardPanel);
        todoListElementsListContainerPanel.add(todoListElementCardPanel, "h 50, wrap, gaptop 3, growx");
        if (super.getRootPane() != null) {
            super.getRootPane().updateUI();
        }
    }

    public void deleteTodoListElementCardPanel(TodoListElementCardPanel toDeleteTodoListElementCardPanel) {
        if (toDeleteTodoListElementCardPanel != null) {
            todoListElementCardPanels.remove(toDeleteTodoListElementCardPanel);
            todoListElementsListContainerPanel.remove(toDeleteTodoListElementCardPanel);
            if (super.getRootPane() != null) {
                super.getRootPane().updateUI();
            }
        }
    }

    public List<TodoListElementDto> getUpdatedTodoListElementDtos() {
        List<TodoListElementDto> todoListElementDtos = new ArrayList<>();
        for (TodoListElementCardPanel todoListElementCardPanel : todoListElementCardPanels) {
            TodoListElementDto toUpdateTodoListElementDto = todoListElementCardPanel.getTodoListElementDto();
            toUpdateTodoListElementDto.setDescription(todoListElementCardPanel.getTodoListElementDescriptionTextField().getText());
            toUpdateTodoListElementDto.setStatus(todoListElementCardPanel.getTodoListElementStatusSelectorLabel().getSelectedStatus().getTodoListElementStatus());
            todoListElementDtos.add(todoListElementCardPanel.getTodoListElementDto());
        }
        return todoListElementDtos;
    }

    public void updateTodoListElementsDto(List<TodoListElementDto> todoListElementDtos) {
        todoListElementCardPanels.clear();
        todoListElementsListContainerPanel.removeAll();
        for (TodoListElementDto todoListElementDto : todoListElementDtos) {
            addTodoListElementCardPanel(todoListElementDto, DisplayMode.READ);
        }
        if (super.getRootPane() != null) {
            super.getRootPane().updateUI();
        }
    }

}
