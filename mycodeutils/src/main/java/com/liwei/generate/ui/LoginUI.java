package com.liwei.generate.ui;

import com.liwei.generate.core.Constants;
import com.liwei.generate.entity.DataBase;
import com.liwei.generate.utils.DataBaseUtils;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;


public class LoginUI extends Application {
    /**
     * 任务目标
     *      使用javaFX 创建一个表单登录页面
     * */
    @Override
    public void start(Stage primaryStage) {
        try {

            /**
             * 1.使用gridpanel(网格面板)
             * */
            GridPane  grid = new GridPane();
            grid.setAlignment(Pos.CENTER); //对齐方式(居中)

            //设置grippanel属性
            grid.setHgap(10); //水平距离
            grid.setVgap(20); //垂直距离
            grid.setPadding(new Insets(25,25,25,25)); //设置内边距

            /*
             * 2.声明组件 并设置组件属性
             * */

            //页面标题
            Text screenTitle = new Text("Welcome");
            screenTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
            //设置页面标题id
            screenTitle.setId("title");
            //grid.add(child, 列索引, 行索引, 跨列数, 跨行数);
            grid.add(screenTitle, 0, 0, 2, 1);
            //选择数据库
            Label db = new Label("选择数据库:");
            grid.add(db, 0, 1);
            ObservableList<String> options = FXCollections.observableArrayList("MYSQL","ORACLE","SQLSERVER");
            ComboBox<String> combo = new ComboBox<>(options);
            combo.getSelectionModel().select(0);
            grid.add(combo, 1, 1);

            //服务器IP
            Label ip = new Label("服务器IP:");
            grid.add(ip, 0, 2);

            TextField ipTextField = new TextField("127.0.0.1");
            grid.add(ipTextField, 1, 2);
            //服务器IP
            Label portal = new Label("端口:");
            grid.add(portal, 0, 3);

            TextField portalTextField = new TextField("3306");
            grid.add(portalTextField, 1, 3);
            //账号
            Label username = new Label("用户名:");
            grid.add(username, 0, 4);

            TextField userTextField = new TextField();
            grid.add(userTextField, 1, 4);

            //密码
            Label pw = new Label("用户密码:");
            grid.add(pw, 0, 5);

            PasswordField pwBox = new PasswordField();
            grid.add(pwBox, 1, 5);

            //选择数据库
            Label dbName = new Label("选择数据库:");
            grid.add(dbName, 0, 6);

            ComboBox<String> dbNameCombo = new ComboBox<>();
            grid.add(dbNameCombo, 1, 6);

            //测试按钮
            Button txbtn = new Button("Test");
            HBox txhbBtn = new HBox();
            txhbBtn.setAlignment(Pos.BOTTOM_RIGHT); //对齐方式(底部右侧)
            txhbBtn.getChildren().add(txbtn);
            grid.add(txhbBtn, 1, 7);
            //提交 按钮
            Button btn = new Button("Sign");
            HBox hbBtn = new HBox(5);
            hbBtn.setAlignment(Pos.BOTTOM_LEFT); //对齐方式(底部右侧)
            hbBtn.getChildren().add(btn);
            grid.add(hbBtn,2, 7);

            //添加一个文本框(用于显示信息的控制)
            final Text actiontarget = new Text();
            actiontarget.setId("actiontarget");
            grid.add(actiontarget, 0, 8,2,1);

         //登录按按钮事件
            btn.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    String dbType = combo.getValue();
                    String ip = ipTextField.getText();
                    String port = portalTextField.getText();
                    String username = userTextField.getText();
                    String password = pwBox.getText();
                    String dnName = dbNameCombo.getValue();
                    DataBase dataBase= new DataBase(dbType,ip,port,dnName);
                    dataBase.setUserName(username);
                    dataBase.setPassWord(password);
                    GeneratorUI generatorUI = new GeneratorUI();
                    generatorUI.showWindow(dataBase,primaryStage);
                    primaryStage.hide();
                }
            });
            //测试连接保存按钮事件
            txbtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        String url = combo.getValue();
                        String ip = ipTextField.getText();
                        String port = portalTextField.getText();
                        String username = userTextField.getText();
                        String password = pwBox.getText();
                        DataBase dataBase = new DataBase(url,ip,port,"");
                        dataBase.setPassWord(password);
                        dataBase.setUserName(username);
                        List<String> schemas = DataBaseUtils.getSchemas(dataBase);
                        ObservableList<String> items = dbNameCombo.getItems();
                        items.clear();
                        for (String schema : schemas) {
                            items.add(schema);
                        }
                        dbNameCombo.getSelectionModel().selectFirst();
                        actiontarget.setText("连接成功！");
                    }catch (Exception e){
                        actiontarget.setFill(Color.FIREBRICK);
                        if(e.getMessage().contains("(using password: YES)")){
                            actiontarget.setText("密码错误！");
                        }else{
                            actiontarget.setText("无法连接数据库，请核对连接信息是否正确！");
                        }
                        e.printStackTrace();
                    }

                }
            });
            /**
             * 4.把容器添加到场景中  并设置场景大小
             * ps:如果不设置场景大小，默认是最小
             * */
            Scene scene = new Scene(grid,500,450);

            //场景引入css文件
            scene.getStylesheets().add(
                     Constants.CSS+"Login.css");

            primaryStage.setTitle("代码生成器");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


}
