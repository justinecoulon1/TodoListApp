package com.coulon.todo.app.gui.panels.home;

import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import com.coulon.todo.app.utils.ui.images.ImageUtils;
import com.coulon.todo.app.utils.ui.images.UiIcons;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class TodoListCreateButtonContainerPanel extends JPanel {

    private final TodoListsMainPanel todoListsMainPanel;

    public TodoListCreateButtonContainerPanel(TodoListsMainPanel todoListsMainPanel) {
        this.setLayout(new MigLayout("fill, nogrid, ins 10"));
        this.setBackground(TodoListAppConstants.BACKGROUND_COLOR);
        this.todoListsMainPanel = todoListsMainPanel;

        JPanel createTodoListButtonPanel = new JPanel(new MigLayout("fill, nogrid, ins 0"));
        createTodoListButtonPanel.setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        createTodoListButtonPanel.setBorder(BorderFactory.createLineBorder(TodoListAppConstants.BORDERS_AND_SEPARATORS, 3));

        JLabel addImageLabel = new JLabel();
        BufferedImage addImage = ImageUtils.resizeImage(UiIcons.ADD.getImage(), 32, 32);
        ImageIcon addImageIcon = new ImageIcon(addImage);
        addImageLabel.setIcon(addImageIcon);
        createTodoListButtonPanel.add(addImageLabel, "aligny center, alignx left, gapbefore 20");

        JLabel addTextLabel = new JLabel();
        addTextLabel.setText("Create a new to-do list");
        addTextLabel.setFont(TodoListAppConstants.SMALL_TITLE_FONT);
        addTextLabel.setBackground(getBackground());
        addTextLabel.setForeground(TodoListAppConstants.LIGHT_FONT_COLOR);
        createTodoListButtonPanel.add(addTextLabel,"gapbefore 30, aligny center");

        createTodoListButtonPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleCreateTodoListButton();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                createTodoListButtonPanel.setBackground(TodoListAppConstants.HIGHLIGHT_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                createTodoListButtonPanel.setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
            }
        });

        this.add(createTodoListButtonPanel, "w 350, h 60!, aligny top, alignx center");

    }

    private void handleCreateTodoListButton() {
        todoListsMainPanel.getListDisplayPanel().addTodoListPanel();
    }

}

