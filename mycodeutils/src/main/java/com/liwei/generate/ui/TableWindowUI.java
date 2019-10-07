package com.liwei.generate.ui;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Author: li.wei
 * @Date: 2019/9/14 20:58
 * @Version
 */
public class TableWindowUI {
    ScrollPane sp = new ScrollPane();
    VBox vbox = new VBox();

    public void display(String title , Map<String,Boolean> param){
        Stage window = new Stage();
        window.setTitle(title);
        //modality要使用Modality.APPLICATION_MODEL
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(350);
        window.setMinHeight(350);
        VBox layout = new VBox(10);
        //去人按钮
        Button button = new Button("确定");
        button.setAlignment(Pos.CENTER);
        button.setOnAction(e -> window.close());
        //全选按钮
        Button button2 = new Button("取消");
        button2.setAlignment(Pos.CENTER);
        //选项
        Set<String> keys = param.keySet();
        CheckBox[] cbs = new CheckBox[keys.size()];
        int i = 0;
        boolean status = true;
        for(String key : keys){
            CheckBox box = cbs[i] = new CheckBox(key);
            box.setSelected(param.get(key));
            box.selectedProperty().addListener(e->{
                param.put(box.getText(), box.isSelected());
            } );
            //如果有一个为 false 则 按钮 为全选  否则 为 取消
            if("取消".equals(button2.getText()) && !param.get(key)){
                button2.setText("全选");
            }
            i++;
        }


        layout.getChildren().addAll(cbs);
        layout.setAlignment(Pos.BOTTOM_LEFT);

        button2.setOnAction(e ->{
            int j= 0 ;
            String text  = button2.getText();
            if("全选".equals(text)){
                for(String key : keys){
                    CheckBox box = cbs[j] ;
                    box.setSelected(true);
                    param.put(key,true);
                    j++;
                }
                button2.setText("取消");
            }else{
                for(String key : keys){
                    CheckBox box = cbs[j] ;
                    box.setSelected(false);
                    param.put(key,false);
                    j++;
                }
                button2.setText("全选");
            }
        });
        sp.setVmax(440);
        sp.setPrefSize(115, 300);
        sp.setContent(layout);
        vbox.getChildren().add(sp);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.setPadding(new Insets(0, 18, 0, 0)); //节点到边缘的距离
        hBox.setSpacing(10  ); //节点之间的间距
        hBox.getChildren().addAll(button2,button);
        vbox.getChildren().add(hBox);

        Scene scene = new Scene(vbox);

        window.setScene(scene);
        //使用showAndWait()先处理这个窗口，而如果不处理，main中的那个窗口不能响应
        window.showAndWait();
    }
}
