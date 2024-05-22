package com.coulon.todo.app.gui;

import com.coulon.todo.app.gui.panels.MainPanel;
import com.coulon.todo.app.gui.panels.home.HomeMainPanel;
import com.coulon.todo.app.gui.panels.menu.MainMenuPanel;
import com.coulon.todo.app.gui.panels.tabs.todolist.TodoListMainPanel;

public class AppPanels {

    public static final TodoListMainPanel TODO_LIST_MAIN_PANEL = new TodoListMainPanel();
    public static final HomeMainPanel HOME_MAIN_PANEL = new HomeMainPanel();
    public static final MainMenuPanel MAIN_MENU_PANEL = new MainMenuPanel();
    public static final MainPanel MAIN_PANEL = new MainPanel();
    public static final MainFrame MAIN_FRAME = new MainFrame();

}
