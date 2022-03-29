package org.openjfx;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PrimaryController {

    public TextArea output;
    public TextField max_value;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    public void startCounting(MouseEvent mouseEvent)
    {
        output.setText("");
        Counter counter = new Counter(Integer.parseInt(max_value.getText().replace(".", "")));
        counter.setOutput(new Output() {
            @Override
            public void append(String message) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        output.setText(output.getText() + message + "\n");
                    }
                });
            }
        });
        counter.startIncrementing();
    }
}
