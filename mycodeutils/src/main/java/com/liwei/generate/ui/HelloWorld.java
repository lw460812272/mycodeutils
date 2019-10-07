package com.liwei.generate.ui;

import com.liwei.generate.core.Constants;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @Author: li.wei
 * @Date: 2019/9/8 16:34
 * @Version
 */
public class HelloWorld extends Application {
    public void start(Stage stage) throws Exception {


        // 创建一个标签，用于存放我们的Hello World文本，并设置让它在父容器中居中
        Label label = new Label("Hello"); label.setAlignment(Pos.CENTER);
        // 设置字体大小、颜色（当然我们也可以用css来设置，先这样写）
        label.setFont(Font.font(30)); // 或者顺带设置字体 label.setFont(new Font("Arial", 30));
        label.setTextFill(Color.web("#FFFFFF"));
        label.setBackground(new Background(new BackgroundFill(Paint.valueOf("black"), null, null)));
        // 设置设置图标
        String IMG =Constants.IMG + "java.jpg";
        label.setGraphic(new ImageView(new Image(Constants.IMG + "java.jpg", 50, 50, false, false)));
        /**
         * 三步曲
         */
        // 1、初始化一个场景
        Scene scene = new Scene(label, 400, 300);

        stage.setTitle(Constants.TITILE);

        stage.getIcons().add(new Image(Constants.IMG + "java.jpg"));
        // 2、将场景放入窗口;
        stage.setScene(scene);
        // 3、打开窗口
        stage.show();
    }

    public static void main( String[] args ){
        // 启动软件
        Application.launch(args);
    }

}
