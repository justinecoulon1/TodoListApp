package com.coulon.todo.app.gui.panels;

import com.coulon.todo.app.common.dto.SessionDto;
import com.coulon.todo.app.gui.AppPanels;
import com.coulon.todo.app.gui.titlebar.TitleBarPanel;
import com.coulon.todo.app.utils.external.BackEndRequestProcessor;
import com.coulon.todo.app.utils.managers.Credentials;
import com.coulon.todo.app.utils.managers.CredentialsManager;
import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class MainPanel extends JPanel {

    private final JPanel mainContentPanel = new JPanel(new MigLayout("fill, nogrid, ins 0"));
    private JPanel currentDisplayedPanel;

    public MainPanel() {
        this.setLayout(new MigLayout("fill, nogrid, ins 0, gap 0"));
        this.setBackground(TodoListAppConstants.GENERAL_BACKGROUND_COLOR);

        this.add(new TitleBarPanel(), "growx, h 70!, aligny top, wrap");
        this.add(mainContentPanel, "grow, push");

        SessionDto sessionDto = getSession();
        if (sessionDto != null) {
            BackEndRequestProcessor.SESSION_TOKEN = sessionDto.getToken();
            AppPanels.TODO_LISTS_HOME_PANEL.refresh();
            setDisplayedPanel(AppPanels.TODO_LISTS_HOME_PANEL);
        } else {
            setDisplayedPanel(AppPanels.LOG_IN_DISPLAY_PANEL);
        }
    }

    public void setDisplayedPanel(JPanel displayedPanel) {
        mainContentPanel.removeAll();
        mainContentPanel.add(displayedPanel, "grow, push");
        currentDisplayedPanel = displayedPanel;
        if (super.getRootPane() != null) {
            super.getRootPane().updateUI();
        }
    }

    public JPanel getCurrentDisplayedPanel() {
        return currentDisplayedPanel;
    }

    public SessionDto getSession() {
        Credentials credentials = CredentialsManager.INSTANCE.getCredentials();
        if (credentials != null) {
            try {
                return BackEndRequestProcessor.INSTANCE.logIn(credentials.getEmail(), credentials.getPassword());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

}
