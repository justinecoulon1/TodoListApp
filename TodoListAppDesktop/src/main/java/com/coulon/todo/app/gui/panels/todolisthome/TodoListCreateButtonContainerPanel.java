package com.coulon.todo.app.gui.panels.todolisthome;

import com.coulon.todo.app.common.dto.TodoListDto;
import com.coulon.todo.app.gui.AppPanels;
import com.coulon.todo.app.utils.external.BackEndRequestProcessor;
import com.coulon.todo.app.utils.ui.ButtonDefaultMouseAdapter;
import com.coulon.todo.app.utils.ui.DisplayMode;
import com.coulon.todo.app.utils.ui.LabelUtils;
import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import com.coulon.todo.app.utils.ui.images.UiIcons;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TodoListCreateButtonContainerPanel extends JPanel {

    public TodoListCreateButtonContainerPanel() {
        this.setLayout(new MigLayout("fill, nogrid, ins 10"));
        this.setBackground(TodoListAppConstants.GENERAL_BACKGROUND_COLOR);

        JPanel createTodoListButtonPanel = new JPanel(new MigLayout("fill, nogrid, ins 0"));
        createTodoListButtonPanel.setBackground(TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        createTodoListButtonPanel.setBorder(BorderFactory.createLineBorder(TodoListAppConstants.BORDERS_AND_SEPARATOR_COLOR, 3));
        createTodoListButtonPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        createTodoListButtonPanel.setToolTipText("Create a todo list");

        JLabel addImageLabel = LabelUtils.createImageLabel(UiIcons.ADD.getImage(), 32);
        createTodoListButtonPanel.add(addImageLabel, "aligny center, alignx left, gapbefore 20");

        JLabel addTextLabel = LabelUtils.createTransparentTextLabel("Create a new to-do list", TodoListAppConstants.SMALL_TITLE_FONT, getBackground(), TodoListAppConstants.LIGHT_FONT_COLOR);
        createTodoListButtonPanel.add(addTextLabel, "gapbefore 30, aligny center");

        MouseListener createTodoListMouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                TodoListDto todoListDto = BackEndRequestProcessor.INSTANCE.createTodoList("");
                TodoListCardPanel todoListCardPanel = AppPanels.TODO_LISTS_HOME_PANEL.getListDisplayPanel().addTodoListCardPanel(todoListDto);
                todoListCardPanel.openTodoList(DisplayMode.UPDATE);
            }
        };
        createTodoListButtonPanel.addMouseListener(createTodoListMouseAdapter);
        createTodoListButtonPanel.addMouseListener(new ButtonDefaultMouseAdapter(createTodoListButtonPanel, TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR, TodoListAppConstants.DARK_HIGHLIGHT_COLOR));

        this.add(createTodoListButtonPanel, "w 350, h 60!, aligny top, alignx center");
    }

}

