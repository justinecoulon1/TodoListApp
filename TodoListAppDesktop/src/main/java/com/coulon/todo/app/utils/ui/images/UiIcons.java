package com.coulon.todo.app.utils.ui.images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public enum UiIcons {

    TODO_LOGO("/icons/todo_list.png"),
    MINIMIZE("/icons/minimize.png"),
    ADD("/icons/add.png"),
    MODIFY("/icons/modify.png"),
    VALIDATE("/icons/validate.png"),
    NORMAL_TODO("/icons/normal_todo.png"),
    TIMED_TODO("/icons/timed_todo.png"),
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
