package com.liwei.generate.ui;

import com.liwei.generate.core.Constants;
import com.liwei.generate.entity.DataBase;
import com.liwei.generate.entity.Table;
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
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import sun.applet.Main;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GeneratorUI   {

    Stage stage=new Stage();
    /**
     * 任务目标
     *      使用javaFX 创建一个表单登录页面
     * */
    public void start( DataBase dataBase,Stage primaryStage) {
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
            createTemplate(grid);
            //生成代码路径
            Label ip = new Label("生成代码路径:");
            grid.add(ip, 0, 2);

            TextField ipTextField = new TextField();
            grid.add(ipTextField, 1, 2);

            Button localbtn = new Button("选择");
            HBox localhbBtn = new HBox(2);
            localhbBtn.setAlignment(Pos.CENTER_LEFT); //对齐方式(底部右侧)
            localhbBtn.getChildren().add(localbtn);
            grid.add(localhbBtn, 2, 2);

            localbtn.setOnAction(new EventHandler<ActionEvent>() {
                //选择按钮事件
                public void handle(ActionEvent event) {
                    DirectoryChooser directoryChooser = new DirectoryChooser();
                    File file = directoryChooser.showDialog(stage);
                    String path = file.getPath();//选择的文件夹路径
                    ipTextField.setText(path);
                }
            });
            //服务器IP
            Label portal = new Label("包名:");
            grid.add(portal, 0, 3);

            TextField portalTextField = new TextField();
            grid.add(portalTextField, 1, 3);
            //账号
            Label username = new Label("作者:");
            grid.add(username, 0, 4);

            TextField userTextField = new TextField();
            grid.add(userTextField, 1, 4);

            //密码
            Label pw = new Label("数据库名称:");
            grid.add(pw, 0, 5);

            PasswordField pwBox = new PasswordField();
            grid.add(pwBox, 1, 5);

            //选择数据库
            Label dbName = new Label("选择表:");
            grid.add(dbName, 0, 6);

            Button selectTable = new Button("选择");
            grid.add(selectTable, 1, 6);

            //测试按钮
            Button txbtn = new Button("返回");
            HBox txhbBtn = new HBox(2);
            txhbBtn.setAlignment(Pos.BOTTOM_RIGHT); //对齐方式(底部右侧)
            txhbBtn.getChildren().add(txbtn);
            grid.add(txhbBtn, 1, 7);


            //提交 按钮
            Button btn = new Button("生成");
            HBox hbBtn = new HBox(5);
            hbBtn.setAlignment(Pos.BOTTOM_LEFT); //对齐方式(底部右侧)
            hbBtn.getChildren().add(btn);
            grid.add(hbBtn, 2, 7);

            //添加一个文本框(用于显示信息的控制)
                final Text actiontarget = new Text();
            actiontarget.setId("actiontarget");
            grid.add(actiontarget, 0, 8,2,1);


            //查询表数据
            List<Table> dbInfo = DataBaseUtils.getDbInfo(dataBase);
            Map<String,Boolean> param = new HashMap<>();
            for (Table table : dbInfo) {
                param.put(table.getName(),false);
            }

            //绑定选择表按钮事件
            selectTable.setOnAction(e->new TableWindowUI().display("选择表",param));
            txbtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    primaryStage.show();
                    stage.close();
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

            stage.setTitle("代码生成器");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public  void showWindow(  DataBase dataBase,Stage primaryStage) {
        start(dataBase,primaryStage);
    }

    /**
     * 加载模板位置模块
     * @param grid
     */
    public void createTemplate(GridPane  grid){
        Label db = new Label("选择数据库:");
        grid.add(db, 0, 1);

        File directory = new File("D:/java_myWorkSpace/target/classes/template");
        File[] listFiles = directory.listFiles();
        ObservableList<String> options = FXCollections.observableArrayList();
        if (listFiles != null) {
            for (File f : listFiles) {
                if (f.isDirectory()) {
                    options.add(f.getName());
                }
            }
        }
        ComboBox<String> combo = new ComboBox<>(options);
        combo.getSelectionModel().select(0);
        grid.add(combo, 1, 1);
    }
}
