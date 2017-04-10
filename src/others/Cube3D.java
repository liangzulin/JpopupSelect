/*
 * Copyright (c) 2008, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle Corporation nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package others;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * A sample that demonstrates an animated rotation of 3D cubes. When the
 * application runs in standalone mode, the scene must be constructed with
 * the depthBuffer argument set to true, and the root node must have depthTest
 * set to true.
 *
 * @see javafx.scene.transform.Rotate
 * @see javafx.scene.paint.Color
 * @see javafx.scene.shape.RectangleBuilder
 */
public class Cube3D extends Application {

    private Timeline animation;

    private void init(Stage primaryStage) {
        Group root = new Group();
        root.setDepthTest(DepthTest.ENABLE);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 1400, 750, true));
        primaryStage.getScene().setCamera(new PerspectiveCamera());
        root.getTransforms().addAll(
            new Translate(400 / 2, 150 / 2),
            new Rotate(180, Rotate.X_AXIS)
        );
        root.getChildren().add(create3dContent());
    }

    public Node create3dContent() {
        Cube c = new Cube(150,Color.RED,1);
        c.setTranslateX(200);
        c.setTranslateY(200);
        c.rx.setAngle(45);
        c.ry.setAngle(45);
        Cube c2 = new Cube(50,Color.GREEN,1);
        c2.setTranslateX(100);
        c2.rx.setAngle(45);
        c2.ry.setAngle(45);
        Cube c3 = new Cube(50,Color.ORANGE,1);
        c3.setTranslateX(300);
        c3.rx.setAngle(45);
        c3.ry.setAngle(45);

        // 建立3D方塊
        Box box3;
        box3 = new Box(100, 100, 100);
        // 設定省略描繪Back Face
        box3.setCullFace(CullFace.BACK);
        // 設定以填滿的方式繪製3D方塊
        box3.setDrawMode(DrawMode.FILL);
        FileInputStream is = null;
        try {
            
            is=new FileInputStream(new File("D:\\opencl.png"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cube3D.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image image = new Image(is);
        // 設定Phong Material
        PhongMaterial material2 = new PhongMaterial();
        // 設定漫射貼圖 
        material2.setDiffuseMap(image);
        // 設定物體表面的材質
        box3.setMaterial(material2);
        box3.setLayoutX(350);
        box3.setLayoutY(300);
        
        
        animation = new Timeline();
        animation.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(c.ry.angleProperty(), 0d),
                        new KeyValue(c2.rx.angleProperty(), 0d),
                        new KeyValue(c3.rz.angleProperty(), 0d)
                ),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(c.ry.angleProperty(), 360d),
                        new KeyValue(c2.rx.angleProperty(), 360d),
                        new KeyValue(c3.rz.angleProperty(), 360d)
                ));
        animation.setCycleCount(Animation.INDEFINITE);

        return new Group(c,c2,c3,box3);
    }

    public void play() {
        animation.play();
    }

    @Override public void stop() {
        animation.pause();
    }

    

    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
        play();
    }

}
