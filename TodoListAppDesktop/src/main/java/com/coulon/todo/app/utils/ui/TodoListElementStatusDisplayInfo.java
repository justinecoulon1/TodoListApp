package com.coulon.todo.app.utils.ui;

import com.coulon.todo.app.common.dto.TodoListElementStatus;
import com.coulon.todo.app.utils.ui.images.UiIcons;

public enum TodoListElementStatusDisplayInfo {

    TODO("To do", UiIcons.TODO, TodoListElementStatus.TODO),
    DONE("Done", UiIcons.DONE, TodoListElementStatus.DONE),
    STARTED("Started", UiIcons.STARTED, TodoListElementStatus.STARTED),
    NEARLY_DONE("Nearly Done", UiIcons.NEARLY_DONE, TodoListElementStatus.NEARLY_DONE),
    POSTPONED("Postponed", UiIcons.POSTPONED, TodoListElementStatus.POSTPONED),
    ABANDONED("Abandoned", UiIcons.ABANDONED, TodoListElementStatus.ABANDONED);

    private final String text;
    private final UiIcons uiIcons;
    private final TodoListElementStatus todoListElementStatus;

    TodoListElementStatusDisplayInfo(String text, UiIcons uiIcons, TodoListElementStatus todoListElementStatus) {
        this.text = text;
        this.uiIcons = uiIcons;
        this.todoListElementStatus = todoListElementStatus;
    }

    public String getText() {
        return text;
    }

    public UiIcons getUiIcons() {
        return uiIcons;
    }

    public TodoListElementStatus getTodoListElementStatus() {
        return todoListElementStatus;
    }

    public static TodoListElementStatusDisplayInfo getByStatus(TodoListElementStatus status) {
        for (TodoListElementStatusDisplayInfo statusDisplayInfo : values()) {
            if (status == statusDisplayInfo.todoListElementStatus) {
                return statusDisplayInfo;
            }
        }
        throw new RuntimeException("Unsupported status : " + status);
    }

}
