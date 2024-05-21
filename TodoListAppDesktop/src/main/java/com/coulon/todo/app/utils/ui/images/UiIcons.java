package com.coulon.todo.app.utils.ui.images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public enum UiIcons {

    TODO_LOGO("/icons/todo_list.png"),
    NORMAL_TODO("/icons/normal_todo.png"),
    TIMED_TODO("/icons/timed_todo.png"),
    MINIMIZE("/icons/minimize.png"),
    TODO("/icons/todo.png"),
    DONE("/icons/done.png"),
    STARTED("/icons/started.png"),
    NEARLY_DONE("/icons/nearly_done.png"),
    POSTPONED("/icons/postponed.png"),
    ABANDONED("/icons/abandoned.png"),
    ADD("/icons/add.png"),
    EDIT("/icons/modify.png"),
    VALIDATE("/icons/validate.png"),
    OPEN("/icons/open.png"),
    PIN("/icons/pin.png"),
    HOME("/icons/home.png"),
    MENU("/icons/menu.png"),
    SETTINGS("/icons/settings.png"),
    STATISTICS("/icons/statistics.png"),
    DELETE("/icons/close.png");

    private BufferedImage image;

    UiIcons(String path) {
        try (InputStream inputStream = getClass().getResourceAsStream(path)) {
            this.image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return image;
    }

}
