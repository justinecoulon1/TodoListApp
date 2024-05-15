package com.coulon.todo.app.gui.titlebar;

import com.coulon.todo.app.gui.AppPanels;
import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import com.coulon.todo.app.utils.ui.ButtonUtils;
import com.coulon.todo.app.utils.ui.images.ImageUtils;
import com.coulon.todo.app.utils.ui.images.UiIcons;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TitleBarPanel extends JPanel {

    public TitleBarPanel() {
        this.setLayout(new MigLayout("ins 0, gap 0, fill"));
        this.setBackground(TodoListAppConstants.HEADER_BACKGROUND_COLOR);

        JLabel iconLabel = new JLabel();
        iconLabel.setBackground(this.getBackground());
        BufferedImage titleBarImage = ImageUtils.resizeImage(UiIcons.TODO_LOGO.getImage(), 64, 64);
        ImageIcon imageIcon = new ImageIcon(titleBarImage);
        iconLabel.setIcon(imageIcon);
        this.add(iconLabel, "gapbefore 10, gapafter 15, aligny center");

        JLabel titleLabel = new JLabel();
        titleLabel.setText("To-do List");
        titleLabel.setFont(TodoListAppConstants.TITLE_FONT);
        titleLabel.setBackground(this.getBackground());
        titleLabel.setForeground(TodoListAppConstants.LIGHT_FONT_COLOR);
        this.add(titleLabel, " aligny center, pushx");

        JPanel buttonPanel = new JPanel(new MigLayout("ins 0, gap 0"));
        buttonPanel.setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        JButton minimizeButton = ButtonUtils.createBigButtonWithoutBorder(UiIcons.MINIMIZE);
        minimizeButton.addActionListener(actionEvent -> AppPanels.MAIN_FRAME.setState(Frame.ICONIFIED));
        buttonPanel.add(minimizeButton, "aligny center, alignx right");

        JButton closeButton = ButtonUtils.createBigButtonWithoutBorder(UiIcons.DELETE);
        buttonPanel.add(closeButton, "growy, alignx right");
        closeButton.addActionListener(actionEvent -> System.exit(0));
        this.add(buttonPanel, "aligny top, alignx right");

        WindowDragListener dragListener = new WindowDragListener();
        this.addMouseListener(dragListener);
        this.addMouseMotionListener(dragListener);

    }
}
