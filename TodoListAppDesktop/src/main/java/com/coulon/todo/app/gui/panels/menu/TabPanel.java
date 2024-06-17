package com.coulon.todo.app.gui.panels.menu;

import com.coulon.todo.app.gui.AppPanels;
import com.coulon.todo.app.utils.ui.LabelUtils;
import com.coulon.todo.app.utils.ui.TodoListAppConstants;
import com.coulon.todo.app.utils.ui.images.UiIcons;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TabPanel extends JPanel {

    private final JLabel tabNameLabel;

    public TabPanel(UiIcons uiIcons, String tabName, JPanel toDisplayPanel) {
        this.setLayout(new MigLayout("fill, nogrid, ins 0, gap 0"));
        this.setBackground(TodoListAppConstants.PANEL_BACKGROUND_COLOR);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                TabPanel.this.setBackground(TodoListAppConstants.LIGHT_HIGHLIGHT_COLOR);
                tabNameLabel.setBackground(TodoListAppConstants.LIGHT_HIGHLIGHT_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                TabPanel.this.setBackground(TodoListAppConstants.PANEL_BACKGROUND_COLOR);
                tabNameLabel.setBackground(TodoListAppConstants.PANEL_BACKGROUND_COLOR);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                AppPanels.MAIN_PANEL.setDisplayedPanel(toDisplayPanel);
            }
        });

        JLabel tabImageLabel = LabelUtils.createImageLabelWithBorders(uiIcons.getImage(), 32,TodoListAppConstants.UI_ELEMENTS_BACKGROUND_COLOR, BorderFactory.createLineBorder(TodoListAppConstants.BORDERS_AND_SEPARATOR_COLOR, 3) );
        this.add(tabImageLabel, "aligny center, alignx left, gapbefore 10");

        tabNameLabel = LabelUtils.createTextLabel(tabName,TodoListAppConstants.DEFAULT_FONT, getBackground(), TodoListAppConstants.DARK_FONT_COLOR);
        this.add(tabNameLabel, "aligny center, alignx left, gapbefore 10, growx, h 40!");

        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

}
