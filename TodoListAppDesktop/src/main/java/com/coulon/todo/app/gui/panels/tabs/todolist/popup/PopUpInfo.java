package com.coulon.todo.app.gui.panels.tabs.todolist.popup;

import com.coulon.todo.app.common.dto.TodoListDto;

import javax.swing.*;

public class PopUpInfo {

    private final JFrame frame;
    private final PopUpTodoMainPanel popUpTodoMainPanel;
    private final TodoListDto todoListDto;

    public PopUpInfo(JFrame frame, PopUpTodoMainPanel popUpTodoMainPanel, TodoListDto todoListDto) {
        this.frame = frame;
        this.popUpTodoMainPanel = popUpTodoMainPanel;
        this.todoListDto = todoListDto;
    }

    public JFrame getFrame() {
        return frame;
    }

    public PopUpTodoMainPanel getPopUpTodoMainPanel() {
        return popUpTodoMainPanel;
    }

    public TodoListDto getTodoListDto() {
        return todoListDto;
    }
}
