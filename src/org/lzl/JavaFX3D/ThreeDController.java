/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lzl.JavaFX3D;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import others.Cube;

/**
 *
 * @author liangzl2
 */
public class ThreeDController {
    public static double oldX=0.0;
    public static double oldY=0.0;
    public static Timeline animation;
    
    public static void JavaFXInit(){
        
//        
//        
//        Cube3D c3=new Cube3D();
//        // 往根节点里面添加元素
//        root.getChildren().add(c3.create3dContent());
//
//        
//        
//        
//        
//        public class Cube extends Group {
//        final Rotate rx = new Rotate(0,Rotate.X_AXIS);
//        final Rotate ry = new Rotate(0,Rotate.Y_AXIS);
//        final Rotate rz = new Rotate(0,Rotate.Z_AXIS);
//        public Cube(double size, Color color, double shade) {
//            getTransforms().addAll(rz, ry, rx);
//            getChildren().addAll(
//                RectangleBuilder.create() // back face
//                    .width(size).height(size)
//                    .fill(color.deriveColor(0.0, 1.0, (1 - 0.5*shade), 1.0))
//                    .translateX(-0.5*size)
//                    .translateY(-0.5*size)
//                    .translateZ(0.5*size)
//                    .build(),
//                RectangleBuilder.create() // bottom face
//                    .width(size).height(size)
//                    .fill(color.deriveColor(0.0, 1.0, (1 - 0.4*shade), 1.0))
//                    .translateX(-0.5*size)
//                    .translateY(0)
//                    .rotationAxis(Rotate.X_AXIS)
//                    .rotate(90)
//                    .build(),
//                RectangleBuilder.create() // right face
//                    .width(size).height(size)
//                    .fill(color.deriveColor(0.0, 1.0, (1 - 0.3*shade), 1.0))
//                    .translateX(-1*size)
//                    .translateY(-0.5*size)
//                    .rotationAxis(Rotate.Y_AXIS)
//                    .rotate(90)
//                    .build(),
//                RectangleBuilder.create() // left face
//                    .width(size).height(size)
//                    .fill(color.deriveColor(0.0, 1.0, (1 - 0.2*shade), 1.0))
//                    .translateX(0)
//                    .translateY(-0.5*size)
//                    .rotationAxis(Rotate.Y_AXIS)
//                    .rotate(90)
//                    .build(),
//                RectangleBuilder.create() // top face
//                    .width(size).height(size)
//                    .fill(color.deriveColor(0.0, 1.0, (1 - 0.1*shade), 1.0))
//                    .translateX(-0.5*size)
//                    .translateY(-1*size)
//                    .rotationAxis(Rotate.X_AXIS)
//                    .rotate(90)
//                    .build(),
//                RectangleBuilder.create() // top face
//                    .width(size).height(size)
//                    .fill(color)
//                    .translateX(-0.5*size)
//                    .translateY(-0.5*size)
//                    .translateZ(-0.5*size)
//                    .build()
//            );
//        }
//    }
//        
//        
//        
//        
//        
//        
//        
//        
//        
//        
//        Cube c = new Cube(150,Color.RED,1);
//        c.setTranslateX(200);
//        c.setTranslateY(200);
//        c.rx.setAngle(45);
//        c.ry.setAngle(45);
//        Cube c2 = new Cube(50,Color.GREEN,1);
//        c2.setTranslateX(100);
//        c2.rx.setAngle(45);
//        c2.ry.setAngle(45);
//        Cube c3 = new Cube(50,Color.ORANGE,1);
//        c3.setTranslateX(300);
//        c3.rx.setAngle(45);
//        c3.ry.setAngle(45);
//
//        animation = new Timeline();
//        animation.getKeyFrames().addAll(
//                new KeyFrame(Duration.ZERO,
//                        new KeyValue(c.ry.angleProperty(), 0d),
//                        new KeyValue(c2.rx.angleProperty(), 0d),
//                        new KeyValue(c3.rz.angleProperty(), 0d)
//                ),
//                new KeyFrame(Duration.seconds(1),
//                        new KeyValue(c.ry.angleProperty(), 360d),
//                        new KeyValue(c2.rx.angleProperty(), 360d),
//                        new KeyValue(c3.rz.angleProperty(), 360d)
//                ));
//        animation.setCycleCount(Animation.INDEFINITE);
//
//        return new Group(c,c2,c3);
//        
//        
//        
//        
//        
//        
//        c3.play();
//        return (scene);
//        
        
    }
    public static JFXPanel getJFXPanel(){
        JFXPanel fxPanel = new JFXPanel();

        
        Scene scene;
        Group root = new Group();

        scene = new Scene(root, Color.ALICEBLUE);
        
        
        
        Text text = new Text();
        text.setX(150);
        text.setY(350);
        text.setFont(new Font(20));
        text.setText("Welcome JavaFX!");

        TriangleMesh mesh = new TriangleMesh();
        float points[] = {10,20,30,
                          40,20,30,
                          40,80,30
        };
        mesh.getPoints().addAll(points);
        float texCoords[] = {0.0f,0.0f};
        mesh.getTexCoords().addAll(texCoords);
        int faces[] = {2,0,  1,0,  0,0};
        mesh.getFaces().addAll(faces);
//        int smoothingGroups[] = {};
//        mesh.getFaceSmoothingGroups().addAll(smoothingGroups);
        MeshView mv=new MeshView(mesh);
        mv.setDrawMode(DrawMode.FILL);
        mv.setTranslateX(300);
        mv.setTranslateY(200);
        
        
        TriangleMesh pyramidMesh = new TriangleMesh();
        /**
         * Unfortunately, you can’t simply leave out the texture 
         * coordinates even if you don’t need them, so you must 
         * load at least one coordinate.Do that with this line of code:
         */
        pyramidMesh.getTexCoords().addAll(0,0);
        float h = 150;                    // Height
        float s = 300;                    // Side
        pyramidMesh.getPoints().addAll(
                0, 0, 0, // Point 0 - Top
                0, h, -s / 2, // Point 1 - Front
                -s / 2, h, 0, // Point 2 - Left
                s / 2, h, 0, // Point 3 - Back
                0, h, s / 2 // Point 4 - Right
        );
        pyramidMesh.getFaces().addAll(
                0, 0, 2, 0, 1, 0, // Front left face
                0, 0, 1, 0, 3, 0, // Front right face
                0, 0, 3, 0, 4, 0, // Back right face
                0, 0, 4, 0, 2, 0, // Back left face
                4, 0, 1, 0, 2, 0, // Bottom rear face
                4, 0, 3, 0, 1, 0 // Bottom front face
        );
        MeshView pyramid = new MeshView(pyramidMesh);
        pyramid.setDrawMode(DrawMode.FILL);
//        pyramid.setMaterial(blueStuff);
        pyramid.setTranslateX(200);
        pyramid.setTranslateY(100);
        pyramid.setTranslateZ(200);
        

        Box box;
        box = new Box(100, 100, 100);
        // 設定不省略描繪任何一面
        box.setCullFace(CullFace.BACK);
        // 設定以線框方式繪製
        box.setDrawMode(DrawMode.FILL);
        // 平移Box物件
        box.setLayoutX(550);
        box.setLayoutY(150);
        // 旋轉Box物件
        box.getTransforms().add(new Rotate(30, Rotate.X_AXIS));
        box.getTransforms().add(new Rotate(30, Rotate.Y_AXIS));
        box.getTransforms().add(new Rotate(30, Rotate.Z_AXIS));
        
        
        
        
        
        Box box2;
        box2 = new Box(100, 100, 100);
        // 設定省略描繪Back Face
        box2.setCullFace(CullFace.BACK);
        // 設定以填滿的方式繪製3D方塊
        box2.setDrawMode(DrawMode.FILL);
        // 設定Phong Material
        PhongMaterial material = new PhongMaterial();
        // 設定漫射光顏色
        material.setDiffuseColor(Color.RED);
        // 設定反射光顏色
        material.setSpecularColor(Color.WHITE);
        // 設定物體表面的材質
        box2.setMaterial(material);
        box2.setLayoutX(550);
        box2.setLayoutY(300);
        
        
        
        
        
        // 建立3D方塊
        Box box3;
        box3 = new Box(100, 100, 100);
        // 設定省略描繪Back Face
        box3.setCullFace(CullFace.BACK);
        // 設定以填滿的方式繪製3D方塊
        box3.setDrawMode(DrawMode.FILL);
        FileInputStream is = null;
        try {
            
            is=new FileInputStream(new File("D:\\testGirl.gif"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        Image image = new Image(is);
//        image=new Image(image.getClass().getResourceAsStream("file://D:/opencl.png"));
        // 設定Phong Material
        PhongMaterial material2 = new PhongMaterial();
        // 設定漫射貼圖 
        material2.setDiffuseMap(image);
        // 設定物體表面的材質
        box3.setMaterial(material2);
        box3.setLayoutX(400);
        box3.setLayoutY(300);
        
        Cube c;
        ArrayList<Cube> boxlist=new ArrayList<>();
        for(int i=0;i<2500;i++){
            Cube tmp=new Cube(50,Color.GREEN,1);
            
            boxlist.add(tmp);
            int row=i/50;
            int col=i%50;
            tmp.setTranslateX(50+col*50);
            tmp.setTranslateY(50+row*50);
            
//            Box tmp=new Box(100,100,100);
//            tmp.setCullFace(CullFace.BACK);
//            tmp.setDrawMode(DrawMode.FILL);
//            tmp.setMaterial(material2);
//            tmp.setLayoutX(100+100*col);
//            tmp.setLayoutY(300+100*row);
            
        }
        
        
        
        
        
        
        
        
        root.getChildren().add(text);
        root.getChildren().add(mv);
        root.getChildren().add(pyramid);
        root.getChildren().add(box);
        root.getChildren().add(box2);
        root.getChildren().add(box3);
        
        
        animation= new Timeline();
        
        boxlist.forEach((b)->{
            root.getChildren().add(b);
            
//            animation.getKeyFrames().addAll(
//                    new KeyFrame(Duration.ZERO,
//                            new KeyValue(b.ry.angleProperty(), 0d),
//                    ),
//                    new KeyFrame(Duration.seconds(1),
//                            new KeyValue(b.ry.angleProperty(), 360d),
//                    ));
            
            animation.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(b.ry.angleProperty(), 0d),
                        new KeyValue(b.rx.angleProperty(), 0d),
                        new KeyValue(b.rz.angleProperty(), 0d)
                ),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(b.ry.angleProperty(), 360d),
                        new KeyValue(b.rx.angleProperty(), 360d),
                        new KeyValue(b.rz.angleProperty(), 360d)
                ));
            
            
        });
        animation.play();
        
        
        
        
        animation.setCycleCount(Animation.INDEFINITE);
        
        scene.setOnMouseMoved((javafx.scene.input.MouseEvent e)->{
            
            // 取得滑鼠的水平座標
            double newX = e.getX();
            if (Math.abs(newX - oldX) > 5) {
                // 水平旋轉
                double degree=newX - oldX;
//                root.getChildren().forEach((c1)->{
//                    c1.getTransforms().add(new Rotate(degree, Rotate.X_AXIS));
//                });
//                box.getTransforms()
//                pyramid.getTransforms().add(new Rotate(degree, Rotate.X_AXIS));
//                mv.getTransforms().add(new Rotate(degree, Rotate.X_AXIS));
//                text.getTransforms().add(new Rotate(degree, Rotate.X_AXIS));
                oldX = newX;
                
            }

            // 取得滑鼠的垂直座標
            double newY = e.getY();
            if (Math.abs(newY - oldY) > 5) {
                // 垂直旋轉
                double degree=newY - oldY;
//                root.getChildren().forEach((c1)->{
//                    c1.getTransforms().add(new Rotate(degree, Rotate.Y_AXIS));
//                });
//                box.getTransforms().add(new Rotate(degree, Rotate.Y_AXIS));
//                pyramid.getTransforms().add(new Rotate(degree, Rotate.Y_AXIS));
//                mv.getTransforms().add(new Rotate(degree, Rotate.Y_AXIS));
//                text.getTransforms().add(new Rotate(degree, Rotate.Y_AXIS));
                oldY = newY;
            }
        });
        
        
        
        
        
        fxPanel.setScene(scene);
        return fxPanel;
    }
}
