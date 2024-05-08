package com.coulon.todo.app.gui.panels.home;

import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class TodoListsMainPanel extends JPanel {

    private final TodoListCreateButtonContainerPanel todoListCreateButtonContainerPanel = new TodoListCreateButtonContainerPanel(this);
    private final ListDisplayPanel listDisplayPanel = new ListDisplayPanel();

    public TodoListsMainPanel() {
        this.setLayout(new MigLayout("fill, nogrid, ins 0"));
        this.setBackground(TodoListAppConstants.BACKGROUND_COLOR);

        this.add(todoListCreateButtonContainerPanel, "growx, aligny top, wrap");
        this.add(listDisplayPanel, "grow, push");

    }

    public ListDisplayPanel getListDisplayPanel() {
        return listDisplayPanel;
    }
}
