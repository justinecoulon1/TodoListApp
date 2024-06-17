package com.coulon.todo.app.gui;

import com.coulon.todo.app.gui.panels.MainPanel;
import com.coulon.todo.app.gui.panels.login.LogInDisplayPanel;
import com.coulon.todo.app.gui.panels.todolisthome.TodoListsHomePanel;
import com.coulon.todo.app.gui.panels.menu.MainMenuPanel;
import com.coulon.todo.app.gui.panels.tabs.settings.SettingsMainPanel;
import com.coulon.todo.app.gui.panels.tabs.todolist.TodoListMainPanel;

public class AppPanels {

    public static final LogInDisplayPanel LOG_IN_DISPLAY_PANEL = new LogInDisplayPanel();
    public static final SettingsMainPanel SETTINGS_MAIN_PANEL = new SettingsMainPanel();
    public static final TodoListMainPanel TODO_LIST_MAIN_PANEL = new TodoListMainPanel();
    public static final TodoListsHomePanel TODO_LISTS_HOME_PANEL = new TodoListsHomePanel();
    public static final MainMenuPanel MAIN_MENU_PANEL = new MainMenuPanel();
    public static final MainPanel MAIN_PANEL = new MainPanel();
    public static final MainFrame MAIN_FRAME = new MainFrame();

}
