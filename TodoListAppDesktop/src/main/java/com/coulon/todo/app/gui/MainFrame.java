package com.coulon.todo.app.gui;

import com.coulon.todo.app.utils.ui.images.UiIcons;

import javax.swing.*;

public class MainFrame extends JFrame {


    public MainFrame() {
        this.setUndecorated(true);
        this.setSize(400, 700);
        this.setResizable(false);
        this.setIconImage(UiIcons.TODO_LOGO.getImage());
        this.setTitle("Todo List");

        this.setContentPane(AppPanels.MAIN_PANEL);

    }

}
