package com.coulon.todo.app.gui.panels.menu;

import com.coulon.todo.app.gui.AppPanels;
import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import com.coulon.todo.app.utils.ui.images.UiIcons;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class MainMenuPanel extends JPanel {

    public MainMenuPanel() {
        this.setLayout(new MigLayout("fill, nogrid, ins 5 0 0 0"));
        this.setBackground(TodoListAppConstants.GENERAL_BACKGROUND_COLOR);

        JPanel mainMenuContainerPanel = new JPanel(new MigLayout("fillx, ins 0"));
        mainMenuContainerPanel.setBackground(getBackground());
        this.add(mainMenuContainerPanel, "grow, push");

        TabPanel todoListTabPanel = new TabPanel(UiIcons.NORMAL_TODO, "Todo lists", AppPanels.TODO_LISTS_HOME_PANEL);
        mainMenuContainerPanel.add(todoListTabPanel, "h 50, wrap, growx");

        TabPanel statisticsTabPanel = new TabPanel(UiIcons.STATISTICS, "Statistics", AppPanels.TODO_LISTS_HOME_PANEL);
        mainMenuContainerPanel.add(statisticsTabPanel, "h 50, wrap, growx");

        TabPanel settingsTabPanel = new TabPanel(UiIcons.SETTINGS, "Settings", AppPanels.SETTINGS_MAIN_PANEL);
        mainMenuContainerPanel.add(settingsTabPanel, "h 50, wrap, growx");
    }

}
