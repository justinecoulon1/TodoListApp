package com.coulon.todo.app.gui.panels.tabs.todolist;

import com.coulon.todo.app.common.dto.TodoListElementDto;
import com.coulon.todo.app.utils.ui.ButtonDefaultMouseAdapter;
import com.coulon.todo.app.utils.ui.DisplayMode;
import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import com.coulon.todo.app.utils.ui.images.ImageUtils;
import com.coulon.todo.app.utils.ui.images.UiIcons;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class AddTodoElementButtonContainerPanel extends JPanel {

    public AddTodoElementButtonContainerPanel(TodoListMainPanel todoListMainPanel) {
        this.setLayout(new MigLayout("fill, nogrid, ins 10"));
        this.setBackground(TodoListAppConstants.GENERAL_BACKGROUND_COLOR);


        JPanel addStepButtonPanel = new JPanel(new MigLayout("fill, nogrid, ins 0"));
        addStepButtonPanel.setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        addStepButtonPanel.setBorder(BorderFactory.createLineBorder(TodoListAppConstants.BORDERS_AND_SEPARATOR_COLOR, 3));
        addStepButtonPanel.setToolTipText("Create a todo list");
        addStepButtonPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addStepButtonPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                todoListMainPanel.getTodoListElementsListDisplayPanel().addTodoListElementCardPanel(new TodoListElementDto(), DisplayMode.UPDATE);
            }
        });
        addStepButtonPanel.addMouseListener(new ButtonDefaultMouseAdapter(addStepButtonPanel, TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR, TodoListAppConstants.DARK_HIGHLIGHT_COLOR));

        JLabel addImageLabel = new JLabel();
        BufferedImage addImage = ImageUtils.resizeImage(UiIcons.ADD.getImage(), 32, 32);
        ImageIcon addImageIcon = new ImageIcon(addImage);
        addImageLabel.setIcon(addImageIcon);
        addStepButtonPanel.add(addImageLabel, "aligny center, alignx left, gapbefore 20");

        JLabel addTextLabel = new JLabel();
        addTextLabel.setText("Add a new task");
        addTextLabel.setFont(TodoListAppConstants.SMALL_TITLE_FONT);
        addTextLabel.setBackground(getBackground());
        addTextLabel.setForeground(TodoListAppConstants.LIGHT_FONT_COLOR);
        addStepButtonPanel.add(addTextLabel, "gapbefore 30, aligny center");

        this.add(addStepButtonPanel, "w 350, h 60!, aligny top, alignx center");
    }

}

