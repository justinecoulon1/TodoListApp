package com.coulon.todo.app.gui.panels.todolisthome;

import com.coulon.todo.app.common.dto.TodoListDto;
import com.coulon.todo.app.utils.external.BackEndRequestProcessor;
import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.List;

public class TodoListsHomePanel extends JPanel {

    private final ListDisplayPanel listDisplayPanel = new ListDisplayPanel();

    public TodoListsHomePanel() {
        this.setLayout(new MigLayout("fill, nogrid, ins 0"));
        this.setBackground(TodoListAppConstants.GENERAL_BACKGROUND_COLOR);

        this.add(new TodoListCreateButtonContainerPanel(), "growx, aligny top, wrap");
        this.add(listDisplayPanel, "grow, push");

//        refresh();

    }

    public ListDisplayPanel getListDisplayPanel() {
        return listDisplayPanel;
    }


    public void refresh() {
        List<TodoListDto> todoLists = BackEndRequestProcessor.INSTANCE.getAllTodoLists();
        listDisplayPanel.clearTodoListCardPanels();
        for (TodoListDto todoListDto : todoLists) {
            getListDisplayPanel().addTodoListCardPanel(todoListDto);
        }
    }

}
