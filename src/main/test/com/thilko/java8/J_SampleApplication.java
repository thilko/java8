package com.thilko.java8;

import javafx.application.Application;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class J_SampleApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // http://fxexperience.com/2013/01/modena-new-theme-for-javafx-8/
        setUserAgentStylesheet(STYLESHEET_MODENA);

        String family = "Helvetica";
        double size = 20;

        TextFlow textFlow = new TextFlow();
        textFlow.setLayoutX(40);
        textFlow.setLayoutY(40);
        Text text1 = new Text("Hello ");
        text1.setFont(Font.font(family, size));
        Text text2 = new Text("Bold");
        text2.setFont(Font.font(family, FontWeight.BOLD, size));
        Text text3 = new Text(" World");
        text3.setFont(Font.font(family, FontPosture.ITALIC, size));
        textFlow.getChildren().addAll(text1, text2, text3);

        DatePicker dp = new DatePicker();
        TextFlow textFlow2 = new TextFlow();
        textFlow2.getChildren().add(dp);

//        PrinterJob job = PrinterJob.createPrinterJob();
//        if (job.printPage(textFlow)) {
//            job.endJob();
//        }


        Group group = new Group(textFlow2);
        Scene scene = new Scene(group, 500, 500, Color.WHITE);
        stage.setTitle("Hello Rich Text");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(J_SampleApplication.class);
    }
}
