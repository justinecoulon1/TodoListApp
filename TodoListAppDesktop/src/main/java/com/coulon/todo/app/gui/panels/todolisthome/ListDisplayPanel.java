package com.coulon.todo.app.gui.panels.todolisthome;

import com.coulon.todo.app.common.dto.TodoListDto;
import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ListDisplayPanel extends JPanel {

    private final List<TodoListCardPanel> todoListCardPanels = new ArrayList<>();

    private final JPanel listContainerPanel;

    public ListDisplayPanel() {
        this.setLayout(new MigLayout("fill, nogrid, ins 0"));
        this.setBackground(TodoListAppConstants.GENERAL_BACKGROUND_COLOR);

        listContainerPanel = new JPanel(new MigLayout("fillx, ins 0"));
        listContainerPanel.setBackground(getBackground());
        JScrollPane scrollPane = new JScrollPane(listContainerPanel);
        scrollPane.setHorizontalScrollBar(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.getVerticalScrollBar().setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        scrollPane.setBorder(null);
        this.add(scrollPane, "grow, push");

    }

    public void clearTodoListCardPanels() {
        todoListCardPanels.clear();
        listContainerPanel.removeAll();
    }

    public TodoListCardPanel addTodoListCardPanel(TodoListDto todoListDto) {
        TodoListCardPanel todoListCardPanel = new TodoListCardPanel(todoListDto, this);
        todoListCardPanels.add(todoListCardPanel);
        listContainerPanel.add(todoListCardPanel, "h 50, wrap, growx");
        if (super.getRootPane() != null) {
            super.getRootPane().updateUI();
        }
        return todoListCardPanel;
    }

    public void deleteTodoListCardPanel(TodoListDto todoListDto) {
        TodoListCardPanel toDeleteTodoListCardPanel = null;
        for (TodoListCardPanel todoListCardPanel : todoListCardPanels) {
            if (todoListCardPanel.getTodoListDto().getId() == todoListDto.getId()) {
                toDeleteTodoListCardPanel = todoListCardPanel;
                break;
            }
        }
        if (toDeleteTodoListCardPanel != null) {
            todoListCardPanels.remove(toDeleteTodoListCardPanel);
            listContainerPanel.remove(toDeleteTodoListCardPanel);
            if (super.getRootPane() != null) {
                super.getRootPane().updateUI();
            }
        }
    }

}
