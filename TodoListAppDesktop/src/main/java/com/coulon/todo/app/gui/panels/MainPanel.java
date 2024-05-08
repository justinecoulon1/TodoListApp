package com.coulon.todo.app.gui.panels;

import com.coulon.todo.app.gui.panels.home.TodoListsMainPanel;
import com.coulon.todo.app.gui.titlebar.TitleBarPanel;
import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class MainPanel extends JPanel {

    private final TitleBarPanel titleBarPanel = new TitleBarPanel();
    private final TodoListsMainPanel todoListsMainPanel = new TodoListsMainPanel();

    public MainPanel() {
        this.setLayout(new MigLayout("fill, nogrid, ins 0, gap 0"));
        this.setBackground(TodoListAppConstants.BACKGROUND_COLOR);

        this.add(titleBarPanel, "growx, h 70!, aligny top, wrap");
        this.add(todoListsMainPanel, "grow, push");

    }
}
