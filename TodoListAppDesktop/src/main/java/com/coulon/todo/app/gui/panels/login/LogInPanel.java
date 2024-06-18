package com.coulon.todo.app.gui.panels.login;

import com.coulon.todo.app.common.dto.SessionDto;
import com.coulon.todo.app.gui.AppPanels;
import com.coulon.todo.app.utils.external.BackEndRequestProcessor;
import com.coulon.todo.app.utils.managers.CredentialsManager;
import com.coulon.todo.app.utils.ui.ButtonDefaultMouseAdapter;
import com.coulon.todo.app.utils.ui.LabelUtils;
import com.coulon.todo.app.utils.ui.TextFieldUtils;
import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LogInPanel extends JPanel {

    private final JTextField emailTextField;
    private final JPasswordField passwordTextField;

    public LogInPanel() {
        this.setLayout(new MigLayout("fill, nogrid, ins 0, gap 0"));
        this.setBackground(TodoListAppConstants.PANEL_BACKGROUND_COLOR);

        JLabel emailLabel = LabelUtils.createTextLabel("Email: ", TodoListAppConstants.DEFAULT_FONT, getBackground(), TodoListAppConstants.DARK_FONT_COLOR);
        emailLabel.setHorizontalAlignment(JLabel.LEFT);

        JLabel passwordLabel = LabelUtils.createTextLabel("Password: ", TodoListAppConstants.DEFAULT_FONT, getBackground(), TodoListAppConstants.DARK_FONT_COLOR);
        passwordLabel.setHorizontalAlignment(JTextField.LEFT);

        emailTextField = TextFieldUtils.createTextFieldWithBorder(getBackground());
        passwordTextField = TextFieldUtils.createPasswordTextFieldWithBorder(getBackground());

        KeyListener enterKeyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    logIn(emailTextField.getText(), new String(passwordTextField.getPassword()));
                }
            }
        };

        emailTextField.addKeyListener(enterKeyListener);
        passwordTextField.addKeyListener(enterKeyListener);

        this.add(emailLabel, "gaptop 5, gapleft 10, h 40!, w 80!");
        this.add(emailTextField, "gaptop 5, gapleft 10, gapright 10, h 40!, growx, wrap");

        this.add(passwordLabel, "gaptop 5, gapleft 10, h 40!, w 80!");
        this.add(passwordTextField, "gaptop 5, gapleft 10, gapright 10, h 40!, growx, wrap");

        JPanel logInButtonContainerPanel = new JPanel(new MigLayout("fill, nogrid, ins 10"));
        logInButtonContainerPanel.setBackground(TodoListAppConstants.PANEL_BACKGROUND_COLOR);

        JPanel logInButtonPanel = new JPanel(new MigLayout("fill, nogrid, ins 0"));
        logInButtonPanel.setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        logInButtonPanel.setBorder(BorderFactory.createLineBorder(TodoListAppConstants.BORDERS_AND_SEPARATOR_COLOR, 3));
        logInButtonPanel.setToolTipText("Log in");
        logInButtonPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logInButtonPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                logIn(emailTextField.getText(), new String(passwordTextField.getPassword()));
            }
        });
        logInButtonPanel.addMouseListener(new ButtonDefaultMouseAdapter(logInButtonPanel, TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR, TodoListAppConstants.DARK_HIGHLIGHT_COLOR));

        JLabel logInTextLabel = LabelUtils.createTransparentTextLabel("Log In", TodoListAppConstants.SMALL_TITLE_FONT, logInButtonPanel.getBackground(), TodoListAppConstants.LIGHT_FONT_COLOR);
        logInButtonPanel.add(logInTextLabel, "align center");


        this.add(logInButtonPanel, "w 350, h 60!, aligny top, alignx center, gaptop 10");
    }

    public void logIn(String email, String password) {
        SessionDto sessionDto = BackEndRequestProcessor.INSTANCE.logIn(email, password);
        CredentialsManager.INSTANCE.setCredentials(email, password);
        BackEndRequestProcessor.SESSION_TOKEN = sessionDto.getToken();
        AppPanels.TODO_LISTS_HOME_PANEL.refresh();
        AppPanels.MAIN_PANEL.setDisplayedPanel(AppPanels.TODO_LISTS_HOME_PANEL);
        AppPanels.SETTINGS_MAIN_PANEL.getLogOutPanel().updateEmail(email);
        emailTextField.setText("");
        passwordTextField.setText("");
    }

}
