package com.coulon.todo.app.gui.panels.todolisttab;

import com.coulon.todo.app.common.dto.TodoListDto;
import com.coulon.todo.app.utils.ui.DisplayMode;
import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class TodoListMainPanel extends JPanel {

    private final TodoListNamePanel todoListNamePanel;
    private final TodoListElementsListDisplayPanel todoListElementsListDisplayPanel;

    public TodoListMainPanel(TodoListDto todoListDto, DisplayMode displayMode) {
        this.setLayout(new MigLayout("fill, nogrid, ins 0"));
        this.setBackground(TodoListAppConstants.GENERAL_BACKGROUND_COLOR);

        todoListNamePanel = new TodoListNamePanel(todoListDto, displayMode, this);
        todoListElementsListDisplayPanel = new TodoListElementsListDisplayPanel(todoListDto.getTodoListElementDtos());


        this.add(todoListNamePanel, "growx, aligny top, h 70!, wrap");
        this.add(todoListElementsListDisplayPanel, "grow, push");

        updateDisplayMode(displayMode);
    }

    public void updateDisplayMode(DisplayMode displayMode) {
        todoListNamePanel.updateDisplayMode(displayMode);
        todoListElementsListDisplayPanel.updateDisplayMode(displayMode);
    }

    public TodoListElementsListDisplayPanel getTodoListElementsListDisplayPanel() {
        return todoListElementsListDisplayPanel;
    }

}
