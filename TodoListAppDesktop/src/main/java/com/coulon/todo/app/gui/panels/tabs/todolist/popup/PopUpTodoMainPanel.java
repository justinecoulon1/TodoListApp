package com.coulon.todo.app.gui.panels.tabs.todolist.popup;

import com.coulon.todo.app.common.dto.TodoListDto;
import com.coulon.todo.app.gui.panels.tabs.todolist.TodoListElementsListDisplayPanel;
import com.coulon.todo.app.utils.ui.LabelUtils;
import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import com.coulon.todo.app.utils.ui.images.UiIcons;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class PopUpTodoMainPanel extends JPanel {

    private final PopUpTitleBarPanel popUpTitleBarPanel;
    private final JLabel todoListTitleLabel;
    private final TodoListElementsListDisplayPanel todoListElementsListDisplayPanel;

    public PopUpTodoMainPanel(TodoListDto todoListDto) {
        this.setLayout(new MigLayout("fill, nogrid, ins 0, gap 0, hidemode 2"));
        this.setBackground(TodoListAppConstants.GENERAL_BACKGROUND_COLOR);

        popUpTitleBarPanel = new PopUpTitleBarPanel(todoListDto);
        this.add(popUpTitleBarPanel, "growx, h 42!, aligny top, wrap");

        JPanel todoListNamePanel = new JPanel(new MigLayout("fill, nogrid, ins 0"));
        todoListNamePanel.setBackground(TodoListAppConstants.PANEL_BACKGROUND_COLOR);

        JLabel todoImageLabel = LabelUtils.createImageLabelWithBorders(UiIcons.NORMAL_TODO.getImage(), 32, TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR, BorderFactory.createLineBorder(TodoListAppConstants.BORDERS_AND_SEPARATOR_COLOR, 3));
        todoImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        todoListNamePanel.add(todoImageLabel, "h 70!, w 50!, alignx left");

        todoListTitleLabel = LabelUtils.createTextLabel(todoListDto.getName(), TodoListAppConstants.DEFAULT_FONT, todoListNamePanel.getBackground(), TodoListAppConstants.DARK_FONT_COLOR);
        todoListTitleLabel.setHorizontalAlignment(JTextField.LEFT);
        todoListNamePanel.add(todoListTitleLabel, "aligny center, alignx left, gapbefore 10, gapafter 10, growx, h 40!");

        this.add(todoListNamePanel, "growx, aligny top, h 70!, gapbottom 3, wrap");

        todoListElementsListDisplayPanel = new TodoListElementsListDisplayPanel();
        todoListElementsListDisplayPanel.updateTodoListElementsDto(todoListDto.getTodoListElementDtos());
        this.add(todoListElementsListDisplayPanel, "grow, push");
    }

    public PopUpTitleBarPanel getPopUpTitleBarPanel() {
        return popUpTitleBarPanel;
    }

    public void updateTodoListTitleLabelText(String updatedTodoName) {
        todoListTitleLabel.setText(updatedTodoName);
    }

    public void updateTodoListElementsListDisplayPanel(TodoListDto updatedTodoListDto) {
        todoListElementsListDisplayPanel.updateTodoListElementsDto(updatedTodoListDto.getTodoListElementDtos());
    }

}
