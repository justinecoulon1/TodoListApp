package com.coulon.todo.app.gui.panels.tabs.todolist;

import com.coulon.todo.app.common.dto.TodoListDto;
import com.coulon.todo.app.utils.ui.DisplayMode;
import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class TodoListMainPanel extends JPanel {

    private final TodoListNamePanel todoListNamePanel;
    private final TodoListElementsListDisplayPanel todoListElementsListDisplayPanel;
    private final AddTodoElementButtonContainerPanel addTodoElementButtonContainerPanel = new AddTodoElementButtonContainerPanel(this);
    private TodoListDto todoListDto;
    private DisplayMode displayMode;

    public TodoListMainPanel() {
        this.setLayout(new MigLayout("fill, nogrid, ins 0, gap 0, hidemode 2"));
        this.setBackground(TodoListAppConstants.GENERAL_BACKGROUND_COLOR);

        todoListNamePanel = new TodoListNamePanel();
        todoListElementsListDisplayPanel = new TodoListElementsListDisplayPanel();

        this.add(todoListNamePanel, "growx, aligny top, h 70!, gapbottom 3, wrap");
        this.add(addTodoElementButtonContainerPanel, "growx, aligny top, wrap");
        this.add(todoListElementsListDisplayPanel, "grow, push");
    }

    public void updateTodoList(TodoListDto todoListDto, DisplayMode displayMode) {
        this.todoListDto = todoListDto;
        todoListNamePanel.updateTodoList(todoListDto);
        todoListElementsListDisplayPanel.updateTodoListElementsDto(todoListDto.getTodoListElementDtos());
        updateDisplayMode(displayMode);
    }

    public void updateDisplayMode(DisplayMode displayMode) {
        this.displayMode = displayMode;
        addTodoElementButtonContainerPanel.setVisible(displayMode == DisplayMode.UPDATE);
        todoListNamePanel.updateDisplayMode(displayMode);
        todoListElementsListDisplayPanel.updateDisplayMode(displayMode);
    }

    public TodoListElementsListDisplayPanel getTodoListElementsListDisplayPanel() {
        return todoListElementsListDisplayPanel;
    }

    public TodoListDto getTodoListDto() {
        return todoListDto;
    }

    public DisplayMode getDisplayMode() {
        return displayMode;
    }

}
