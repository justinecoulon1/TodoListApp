package com.coulon.todo.app.gui.panels.tabs.todolist.popup;

import com.coulon.todo.app.common.dto.TodoListDto;
import com.coulon.todo.app.gui.AppPanels;
import com.coulon.todo.app.utils.ui.images.UiIcons;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class TodoListPopUpManager {

    public static TodoListPopUpManager INSTANCE = new TodoListPopUpManager();

    private final Map<Long, JFrame> popUpByTodoListId = new HashMap<>();

    private TodoListPopUpManager() {
    }

    public void openTodoPopUp(TodoListDto todoListDto) {
        closeTodoPopUp(todoListDto.getId());

        JFrame todoPopUpFrame = new JFrame();
        todoPopUpFrame.setUndecorated(true);
        todoPopUpFrame.setSize(280, 500);
        todoPopUpFrame.setResizable(false);
        todoPopUpFrame.setIconImage(UiIcons.TODO_LOGO.getImage());
        todoPopUpFrame.setTitle("Todo List" + todoListDto.getName());
        todoPopUpFrame.setLocationRelativeTo(AppPanels.MAIN_FRAME);
        int popupLocationDelta = 50;
        todoPopUpFrame.setLocation(AppPanels.MAIN_FRAME.getX() + popupLocationDelta, AppPanels.MAIN_FRAME.getY() + popupLocationDelta);
        todoPopUpFrame.setAlwaysOnTop(true);
        todoPopUpFrame.setOpacity(0.85f);

        PopUpTodoMainPanel popUpTodoMainPanel = new PopUpTodoMainPanel(todoListDto);
        todoPopUpFrame.setContentPane(popUpTodoMainPanel);

        popUpByTodoListId.put(todoListDto.getId(), todoPopUpFrame);

        todoPopUpFrame.setVisible(true);
    }

    public void closeTodoPopUp(Long todoListDtoId) {
        JFrame popUp = popUpByTodoListId.remove(todoListDtoId);
        if (popUp != null) {
            popUp.dispose();
        }
    }

}
