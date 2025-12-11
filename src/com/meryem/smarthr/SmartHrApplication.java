package com.meryem.smarthr;

import javafx.application.Application;
import javafx.stage.Stage;
import com.meryem.smarthr.view.LoginView;

public class SmartHrApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        LoginView login = new LoginView();
        login.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
