package com.coulon.todo.app.gui.panels.tabs.settings;

import com.coulon.todo.app.gui.AppPanels;
import com.coulon.todo.app.utils.managers.CredentialsManager;
import com.coulon.todo.app.utils.ui.ButtonDefaultMouseAdapter;
import com.coulon.todo.app.utils.ui.LabelUtils;
import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LogOutPanel extends JPanel {

    private final JLabel currentEmailLabel;

    public LogOutPanel() {
        this.setLayout(new MigLayout("fill, nogrid, ins 0, gap 0"));
        this.setBackground(TodoListAppConstants.PANEL_BACKGROUND_COLOR);

        currentEmailLabel = LabelUtils.createTextLabel("", TodoListAppConstants.BOLD_DEFAULT_FONT, getBackground(), TodoListAppConstants.DARK_FONT_COLOR);
        this.add(currentEmailLabel, "aligny center, alignx center, gaptop 10, wrap");

        JPanel logOutButtonContainerPanel = new JPanel(new MigLayout("fill, nogrid, ins 10"));
        logOutButtonContainerPanel.setBackground(TodoListAppConstants.PANEL_BACKGROUND_COLOR);

        JPanel logOutButtonPanel = new JPanel(new MigLayout("fill, nogrid, ins 0"));
        logOutButtonPanel.setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        logOutButtonPanel.setBorder(BorderFactory.createLineBorder(TodoListAppConstants.BORDERS_AND_SEPARATOR_COLOR, 3));
        logOutButtonPanel.setToolTipText("Log Out");
        logOutButtonPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logOutButtonPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                logOut();
            }
        });
        logOutButtonPanel.addMouseListener(new ButtonDefaultMouseAdapter(logOutButtonPanel, TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR, TodoListAppConstants.DARK_HIGHLIGHT_COLOR));

        JLabel logOutTextLabel = LabelUtils.createTransparentTextLabel("Log Out", TodoListAppConstants.SMALL_TITLE_FONT, logOutButtonPanel.getBackground(), TodoListAppConstants.LIGHT_FONT_COLOR);
        logOutButtonPanel.add(logOutTextLabel, "align center");

        this.add(logOutButtonPanel, "w 350, h 60!, aligny top, alignx center, gaptop 10");
    }

    public void logOut() {
        AppPanels.MAIN_PANEL.setDisplayedPanel(AppPanels.LOG_IN_DISPLAY_PANEL);
        CredentialsManager.INSTANCE.resetCredentials();
    }

    public void updateEmail(String email) {
        currentEmailLabel.setText("Currently logged in with: " + email);
    }

}
