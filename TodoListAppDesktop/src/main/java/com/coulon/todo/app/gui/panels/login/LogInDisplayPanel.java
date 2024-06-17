package com.coulon.todo.app.gui.panels.login;

import com.coulon.todo.app.gui.panels.tabs.settings.LogInPanel;
import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class LogInDisplayPanel extends JPanel {

    public LogInDisplayPanel() {
        this.setLayout(new MigLayout("fill, nogrid, ins 0"));
        this.setBackground(TodoListAppConstants.GENERAL_BACKGROUND_COLOR);

        JPanel settingsContainerPanel = new JPanel(new MigLayout("fillx, ins 0, gap 0"));
        settingsContainerPanel.setBackground(getBackground());
        JScrollPane scrollPane = new JScrollPane(settingsContainerPanel);
        scrollPane.setHorizontalScrollBar(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.getVerticalScrollBar().setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        scrollPane.setBorder(null);
        this.add(scrollPane, "grow, push");

        LogInPanel logInPanel = new LogInPanel();
        settingsContainerPanel.add(logInPanel, "h 190!, wrap, gaptop 3, growx");

    }

}
