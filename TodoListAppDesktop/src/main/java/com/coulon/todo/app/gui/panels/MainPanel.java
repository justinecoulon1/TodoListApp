package com.coulon.todo.app.gui.panels;

import com.coulon.todo.app.gui.AppPanels;
import com.coulon.todo.app.gui.titlebar.TitleBarPanel;
import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class MainPanel extends JPanel {

    private final JPanel mainContentPanel = new JPanel(new MigLayout("fill, nogrid, ins 0"));

    public MainPanel() {
        this.setLayout(new MigLayout("fill, nogrid, ins 0, gap 0"));
        this.setBackground(TodoListAppConstants.GENERAL_BACKGROUND_COLOR);

        this.add(new TitleBarPanel(), "growx, h 70!, aligny top, wrap");
        this.add(mainContentPanel, "grow, push");

        setDisplayedPanel(AppPanels.HOME_MAIN_PANEL);
    }

    public void setDisplayedPanel(JPanel displayedPanel) {
        mainContentPanel.removeAll();
        mainContentPanel.add(displayedPanel, "grow, push");
        if (super.getRootPane() != null) {
            super.getRootPane().updateUI();
        }
    }

}
