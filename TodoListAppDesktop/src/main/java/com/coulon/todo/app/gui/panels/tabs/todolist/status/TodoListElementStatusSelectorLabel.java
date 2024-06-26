package com.coulon.todo.app.gui.panels.tabs.todolist.status;

import com.coulon.todo.app.common.dto.TodoListElementStatus;
import com.coulon.todo.app.utils.ui.ButtonDefaultMouseAdapter;
import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import com.coulon.todo.app.utils.ui.TodoListElementStatusDisplayInfo;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TodoListElementStatusSelectorLabel extends JLabel {

    private TodoListElementStatusDisplayInfo selectedStatus;

    public TodoListElementStatusSelectorLabel(TodoListElementStatus currentStatus, TodoListElementStatusSelectorLabelListener listener) {
        setSelectedStatus(currentStatus);
        this.addMouseListener(new ButtonDefaultMouseAdapter(this, TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR, TodoListAppConstants.DARK_HIGHLIGHT_COLOR));

        JPopupMenu popupMenu = new JPopupMenu();
        for (TodoListElementStatusDisplayInfo elementStatusDisplayInfo : TodoListElementStatusDisplayInfo.values()) {
            JMenuItem menuItem = new JMenuItem(elementStatusDisplayInfo.getText(), buildImageIcon(elementStatusDisplayInfo));
            menuItem.setFont(TodoListAppConstants.DEFAULT_FONT);
            menuItem.addActionListener((e) -> listener.onStatusSelected(elementStatusDisplayInfo.getTodoListElementStatus()));
            popupMenu.add(menuItem);
        }
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (popupMenu.isVisible()) {
                    popupMenu.setVisible(false);
                } else {
                    popupMenu.show(TodoListElementStatusSelectorLabel.this, 0, TodoListElementStatusSelectorLabel.this.getHeight());
                }
            }
        });

    }

    public void setSelectedStatus(TodoListElementStatus status) {
        TodoListElementStatusDisplayInfo statusDisplayInfo = TodoListElementStatusDisplayInfo.getByStatus(status);
        selectedStatus = statusDisplayInfo;
        this.setIcon(buildImageIcon(statusDisplayInfo));
    }

    private ImageIcon buildImageIcon(TodoListElementStatusDisplayInfo elementStatusDisplayInfo) {
        return new ImageIcon(elementStatusDisplayInfo.getImageSize32());
    }

    public TodoListElementStatusDisplayInfo getSelectedStatus() {
        return selectedStatus;
    }

}
