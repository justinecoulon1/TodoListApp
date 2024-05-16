package com.coulon.todo.app.utils.ui;

import javax.swing.*;

public class UiManagerInitializer {

    public static void initialize() {
        UIDefaults ui = UIManager.getLookAndFeelDefaults();
        ui.put("PopupMenu.background", TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        ui.put("Menu.background", TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        ui.put("Menu.opaque", true);
        ui.put("MenuItem.background", TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR);
        ui.put("MenuItem.selectionBackground", TodoListAppConstants.HIGHLIGHT_COLOR);
        ui.put("MenuItem.foreground", TodoListAppConstants.LIGHT_FONT_COLOR);
        ui.put("MenuItem.selectionForeground", TodoListAppConstants.LIGHT_FONT_COLOR);
        ui.put("MenuItem.selectionFont", TodoListAppConstants.SELECTED_DEFAULT_FONT);
        ui.put("MenuItem.opaque", true);
    }

}
