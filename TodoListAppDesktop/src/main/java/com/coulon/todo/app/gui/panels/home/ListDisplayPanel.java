package com.coulon.todo.app.gui.panels.home;

import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class ListDisplayPanel extends JPanel {

    private final JPanel listContainerPanel;
    public ListDisplayPanel() {
        this.setLayout(new MigLayout("fill, nogrid, ins 0"));
        this.setBackground(TodoListAppConstants.BACKGROUND_COLOR);

        listContainerPanel = new JPanel(new MigLayout("fillx, ins 0"));
        listContainerPanel.setBackground(getBackground());
        JScrollPane scrollPane = new JScrollPane(listContainerPanel);
        scrollPane.setHorizontalScrollBar(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.getVerticalScrollBar().setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        scrollPane.setBorder(null);
        this.add(scrollPane, "grow, push");

    }

    public void addTodoListPanel() {
        TodoListPanel todoListPanel = new TodoListPanel();
        listContainerPanel.add(todoListPanel, "h 50, wrap, growx");
        super.getRootPane().updateUI();
    }

}
