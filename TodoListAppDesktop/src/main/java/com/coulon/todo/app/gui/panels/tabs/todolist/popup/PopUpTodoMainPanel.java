package com.coulon.todo.app.gui.panels.tabs.todolist.popup;

import com.coulon.todo.app.common.dto.TodoListDto;
import com.coulon.todo.app.gui.panels.tabs.todolist.TodoListElementsListDisplayPanel;
import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import com.coulon.todo.app.utils.ui.images.ImageUtils;
import com.coulon.todo.app.utils.ui.images.UiIcons;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class PopUpTodoMainPanel extends JPanel {

    public PopUpTodoMainPanel(TodoListDto todoListDto) {
        this.setLayout(new MigLayout("fill, nogrid, ins 0, hidemode 2"));
        this.setBackground(TodoListAppConstants.GENERAL_BACKGROUND_COLOR);

        this.add(new PopUpTitleBarPanel(todoListDto), "growx, h 42!, aligny top, wrap");

        JPanel todoListNamePanel = new JPanel(new MigLayout("fill, nogrid, ins 0"));
        todoListNamePanel.setBackground(TodoListAppConstants.PANEL_BACKGROUND_COLOR);

        JLabel todoImageLabel = new JLabel();
        todoImageLabel.setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        todoImageLabel.setOpaque(true);
        BufferedImage todoImage = ImageUtils.resizeImage(UiIcons.NORMAL_TODO.getImage(), 32, 32);
        ImageIcon imageIcon = new ImageIcon(todoImage);
        todoImageLabel.setIcon(imageIcon);
        todoImageLabel.setBorder(BorderFactory.createLineBorder(TodoListAppConstants.BORDERS_AND_SEPARATOR_COLOR, 3));
        todoListNamePanel.add(todoImageLabel, "aligny center, alignx left, gapbefore 10");

        JLabel todoListTitleLabel = new JLabel();
        todoListTitleLabel.setText(todoListDto.getName());
        todoListTitleLabel.setBackground(todoListNamePanel.getBackground());
        todoListTitleLabel.setFont(TodoListAppConstants.DEFAULT_FONT);
        todoListTitleLabel.setForeground(TodoListAppConstants.DARK_FONT_COLOR);
        todoListTitleLabel.setHorizontalAlignment(JTextField.LEFT);
        todoListTitleLabel.setOpaque(true);
        todoListNamePanel.add(todoListTitleLabel, "aligny center, alignx left, gapbefore 10, gapafter 10, growx, h 40!");

        this.add(todoListNamePanel, "growx, aligny top, h 70!, wrap");

        TodoListElementsListDisplayPanel todoListElementsListDisplayPanel = new TodoListElementsListDisplayPanel(todoListDto);
        this.add(todoListElementsListDisplayPanel, "grow, push");
    }

}
