package com.coulon.todo.app.gui.panels.tabs.todolist.popup;

import com.coulon.todo.app.common.dto.TodoListDto;
import com.coulon.todo.app.gui.titlebar.WindowDragListener;
import com.coulon.todo.app.utils.ui.ButtonUtils;
import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import com.coulon.todo.app.utils.ui.images.ImageUtils;
import com.coulon.todo.app.utils.ui.images.UiIcons;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PopUpTitleBarPanel extends JPanel {

    public PopUpTitleBarPanel(TodoListDto todoListDto) {
        this.setLayout(new MigLayout("ins 0, gap 0, fill"));
        this.setBackground(TodoListAppConstants.HEADER_BACKGROUND_COLOR);

        JLabel appLogoIconLabel = new JLabel();
        appLogoIconLabel.setBackground(this.getBackground());
        BufferedImage appLogoImage = ImageUtils.resizeImage(UiIcons.TODO_LOGO.getImage(), 24, 24);
        ImageIcon appLogoImageIcon = new ImageIcon(appLogoImage);
        appLogoIconLabel.setIcon(appLogoImageIcon);
        this.add(appLogoIconLabel, "gapbefore 10, gapafter 15, aligny center");

        JLabel titleLabel = new JLabel();
        titleLabel.setText("To-do List");
        titleLabel.setFont(TodoListAppConstants.SMALL_TITLE_FONT);
        titleLabel.setBackground(this.getBackground());
        titleLabel.setForeground(TodoListAppConstants.LIGHT_FONT_COLOR);
        this.add(titleLabel, " aligny center, gapbefore 10, pushx");

        JPanel buttonPanel = new JPanel(new MigLayout("ins 0, gap 0"));
        buttonPanel.setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        JButton closeButton = ButtonUtils.createBigButtonWithoutBorder(UiIcons.DELETE);
        buttonPanel.add(closeButton, "growy, alignx right");
        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                TodoListPopUpManager.INSTANCE.closeTodoPopUp(todoListDto.getId());
            }
        });
        closeButton.setToolTipText("Close");
        this.add(buttonPanel, "aligny top, alignx right");

        WindowDragListener dragListener = new WindowDragListener(this);
        this.addMouseListener(dragListener);
        this.addMouseMotionListener(dragListener);
    }

}
