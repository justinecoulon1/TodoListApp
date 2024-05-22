package com.coulon.todo.app.gui.panels.tabs.todolist.popup;

import com.coulon.todo.app.common.dto.TodoListDto;
import com.coulon.todo.app.gui.AppPanels;
import com.coulon.todo.app.utils.ui.images.UiIcons;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class TodoListPopUpManager {

    public static TodoListPopUpManager INSTANCE = new TodoListPopUpManager();

    private final Map<Long, PopUpInfo> popUpInfoByTodoListId = new HashMap<>();

    private TodoListPopUpManager() {
    }

    public void openTodoPopUp(TodoListDto todoListDto) {
        closeTodoPopUp(todoListDto.getId());

        JFrame todoPopUpFrame = new JFrame();
        todoPopUpFrame.setUndecorated(true);
        todoPopUpFrame.setIconImage(UiIcons.TODO_LOGO.getImage());
        todoPopUpFrame.setTitle("Todo List" + todoListDto.getName());
        todoPopUpFrame.setSize(280, 500);
        todoPopUpFrame.setResizable(false);
        todoPopUpFrame.setLocationRelativeTo(AppPanels.MAIN_FRAME);
        int popupLocationDelta = 50;
        todoPopUpFrame.setLocation(AppPanels.MAIN_FRAME.getX() + popupLocationDelta, AppPanels.MAIN_FRAME.getY() + popupLocationDelta);
        todoPopUpFrame.setOpacity(0.85f);

        PopUpTodoMainPanel popUpTodoMainPanel = new PopUpTodoMainPanel(todoListDto);
        todoPopUpFrame.setContentPane(popUpTodoMainPanel);

        PopUpInfo popUpInfo = new PopUpInfo(todoPopUpFrame, popUpTodoMainPanel, todoListDto);

        popUpInfoByTodoListId.put(todoListDto.getId(), popUpInfo);

        todoPopUpFrame.setVisible(true);
    }

    public void closeTodoPopUp(Long todoListDtoId) {
        PopUpInfo popUpInfo = popUpInfoByTodoListId.remove(todoListDtoId);
        if (popUpInfo != null) {
            popUpInfo.getFrame().dispose();
        }
    }

    public void updateTodoPopUp(TodoListDto todoListDto) {
        PopUpInfo popUpInfo = popUpInfoByTodoListId.get(todoListDto.getId());
        if (popUpInfo != null) {
            PopUpTodoMainPanel popUpTodoMainPanel = popUpInfo.getPopUpTodoMainPanel();

            popUpTodoMainPanel.getPopUpTitleBarPanel().updateTitleLabelText(todoListDto.getName());
            popUpTodoMainPanel.updateTodoListTitleLabelText(todoListDto.getName());
            popUpTodoMainPanel.updateTodoListElementsListDisplayPanel(todoListDto);
        }
    }

}
