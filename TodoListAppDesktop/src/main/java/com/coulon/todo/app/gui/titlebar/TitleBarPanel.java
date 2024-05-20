package com.coulon.todo.app.gui.titlebar;

import com.coulon.todo.app.gui.AppPanels;
import com.coulon.todo.app.utils.ui.ButtonDefaultMouseAdapter;
import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import com.coulon.todo.app.utils.ui.ButtonUtils;
import com.coulon.todo.app.utils.ui.images.ImageUtils;
import com.coulon.todo.app.utils.ui.images.UiIcons;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class TitleBarPanel extends JPanel {

    public TitleBarPanel() {
        this.setLayout(new MigLayout("ins 0, gap 0, fill"));
        this.setBackground(TodoListAppConstants.HEADER_BACKGROUND_COLOR);

        JLabel menuImageLabel = new JLabel();
        menuImageLabel.setToolTipText("Menu");
        menuImageLabel.setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        menuImageLabel.setOpaque(true);
        BufferedImage menuImage = ImageUtils.resizeImage(UiIcons.MENU.getImage(), 24, 24);
        ImageIcon menuImageIcon = new ImageIcon(menuImage);
        menuImageLabel.setIcon(menuImageIcon);
        menuImageLabel.setBorder(BorderFactory.createLineBorder(TodoListAppConstants.BORDERS_AND_SEPARATOR_COLOR, 2));
        menuImageLabel.addMouseListener(new ButtonDefaultMouseAdapter(menuImageLabel, TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR, TodoListAppConstants.DARK_HIGHLIGHT_COLOR));
        menuImageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (AppPanels.MAIN_PANEL.getCurrentDisplayedPanel() == AppPanels.MAIN_MENU_PANEL) {
                    AppPanels.MAIN_PANEL.setDisplayedPanel(AppPanels.HOME_MAIN_PANEL);
                } else {
                    AppPanels.MAIN_PANEL.setDisplayedPanel(AppPanels.MAIN_MENU_PANEL);
                }
            }
        });
        menuImageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.add(menuImageLabel, "aligny center, alignx left, gapbefore 10");

        JLabel appLogoIconLabel = new JLabel();
        appLogoIconLabel.setBackground(this.getBackground());
        BufferedImage appLogoImage = ImageUtils.resizeImage(UiIcons.TODO_LOGO.getImage(), 64, 64);
        ImageIcon appLogoImageIcon = new ImageIcon(appLogoImage);
        appLogoIconLabel.setIcon(appLogoImageIcon);
        this.add(appLogoIconLabel, "gapbefore 10, gapafter 15, aligny center");

        JLabel titleLabel = new JLabel();
        titleLabel.setText("To-do List");
        titleLabel.setFont(TodoListAppConstants.TITLE_FONT);
        titleLabel.setBackground(this.getBackground());
        titleLabel.setForeground(TodoListAppConstants.LIGHT_FONT_COLOR);
        this.add(titleLabel, " aligny center, gapbefore 10, pushx");

        JPanel buttonPanel = new JPanel(new MigLayout("ins 0, gap 0"));
        buttonPanel.setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        JButton minimizeButton = ButtonUtils.createBigButtonWithoutBorder(UiIcons.MINIMIZE);
        minimizeButton.addActionListener(actionEvent -> AppPanels.MAIN_FRAME.setState(Frame.ICONIFIED));
        minimizeButton.setToolTipText("Minimize");
        buttonPanel.add(minimizeButton, "aligny center, alignx right");

        JButton closeButton = ButtonUtils.createBigButtonWithoutBorder(UiIcons.DELETE);
        buttonPanel.add(closeButton, "growy, alignx right");
        closeButton.addActionListener(actionEvent -> System.exit(0));
        closeButton.setToolTipText("Close");
        this.add(buttonPanel, "aligny top, alignx right");

        WindowDragListener dragListener = new WindowDragListener(this);
        this.addMouseListener(dragListener);
        this.addMouseMotionListener(dragListener);
    }

}
