package com.example.a5;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import com.snatik.polygon.Line;
import com.snatik.polygon.Point;
import com.snatik.polygon.Polygon;

import java.util.ArrayList;
import java.util.List;

/**
 * A building block for creating your own shapes that can be
 * transformed and that can respond to input. This class is
 * provided as an example; you will likely need to modify it
 * to meet the assignment requirements.
 *
 * Michael Terry & Jeff Avery
 */
public class MyShape {

    Polygon poly;
    float left;
    float top;
    public float centerX;
    public float centerY;
    public float oldX;
    public float oldY;
//    float originX, originY;
    String part;
    public Matrix transform;

    private MyShape parent = null;                                       // Pointer to our parent
    private ArrayList<MyShape> children = new ArrayList<MyShape>();             // Holds all of our children
    private Bitmap bitmap;

    float degree = 0;



    public MyShape(String part) {
        this.part = part;
        transform.postTranslate(left, top);
    }

    public MyShape(String part, MyShape parent) {
        this.part = part;
        if (parent != null) {
            parent.addChild(this);
        }
        transform.postTranslate(left, top);
    }
    public MyShape(String part, MyShape parent, Polygon poly) {
        this.part = part;
        this.poly = poly;
        if (parent != null) {
            parent.addChild(this);
        }
        transform.postTranslate(left, top);
    }
    public MyShape(String part, MyShape parent, Polygon poly, float left, float top) {
        this.part = part;
        this.poly = poly;
        this.left = left;
        this.top = top;
        if (parent != null) {
            parent.addChild(this);
        }
        transform = new Matrix();
        transform.postTranslate(left, top);
    }
    public MyShape(String part, MyShape parent, Polygon poly, float left, float top, float centerX, float centerY) {
        this.part = part;
        this.poly = poly;
        this.left = left;
        this.top = top;
        this.centerX= centerX;
        this.centerY= centerY;
        this.oldX= centerX;
        this.oldY= centerY;
        if (parent != null) {
            parent.addChild(this);
        }
        transform = new Matrix();
        transform.postTranslate(left, top);
    }


    public void addChild(MyShape s) {
        children.add(s);
        s.setParent(this);
    }

    public MyShape getParent() {
        return parent;
    }

    private void setParent(MyShape s) {
        this.parent = s;
    }

    public void translate(float x, float y, float prevX, float prevY){

        transform.postTranslate(x - prevX, y - prevY);

        if(part != "TORSO") {
            centerX += x - prevX;
            centerY += y - prevY;
        }

        for(int i = 0; i < children.size(); i++){
            children.get(i).translate(x, y, prevX, prevY);
        }
    }
    public void rotate(float x, float y, float prevX, float prevY){
        if(part != "torso") {

                System.out.println(centerX);
                System.out.println(centerY);
                double offsetX = x - centerX;
                double offsetY = y - centerY;

                double offsetPrevX = prevX - centerX;
                double offsetPrevY = prevY - centerY;

                double a1 = Math.atan2(offsetY, offsetX);
                double a2 = Math.atan2(offsetPrevY, offsetPrevX);

                double a_res = a1 - a2;
                a_res = Math.toDegrees(a_res);
            if(Math.abs(a_res) <=7 &&
                    ((part == "head" &&
                            (degree <=50 || degree >=310 || (degree < 60 && a_res<=0) || (degree >= 300 && a_res >=0))) ||
                    (part == "arml1" || part == "armr1") ||
                    ((part == "arml2" || part == "armr2") &&
                            (degree <= 135 || degree >= 225 || (degree < 145 && a_res<=0) || (degree >= 215 && a_res>= 0))) ||
                    ((part == "handl" || part == "handr" || part == "feetl" || part == "feetr") &&
                            (degree <= 35 || degree >= 325 || (degree <45 && a_res<= 0) || (degree >315 && a_res>= 0))) ||
                    ((part == "legl1" || part == "legr1" || part == "legl2" || part == "legr2") &&
                            (degree <=90 || degree >=270 || (degree < 100 && a_res<=0) || (degree >= 260 && a_res >=0))))){
                System.out.println("degree" + degree);
                System.out.println("a_res degree" + a_res);
                degree = (float)(degree + a_res)%360;
                if(degree < 0) degree += 360;
                if(degree > 360) degree -= 360;


                transform.postTranslate((float) -centerX, (float) -centerY);
                transform.postRotate((float) a_res);
                transform.postTranslate((float) centerX, (float) centerY);

                List<Point> pts = getPoints(transformAffine(poly));
                System.out.println(pts);

                float[] newChildCenter = {(float) (pts.get(2).x + pts.get(3).x) / 2,
                        (float) (pts.get(2).y + pts.get(3).y) / 2};
//            transform.mapPoints(newChildCenter);
                if (children != null) {
                    for (MyShape child : children) {
                        float oldx = child.centerX;
                        float oldy = child.centerY;
                        child.centerX = newChildCenter[0];
                        child.centerY = newChildCenter[1];
                        child.transform.postTranslate(child.centerX - oldx, child.centerY - oldy);
                        child.rotate(a_res);
                    }
                }
            }
        }
    }
    public void rotate(double rdegree){

        transform.postTranslate((float) -centerX, (float) -centerY);
        transform.postRotate((float) rdegree);
        transform.postTranslate((float) centerX, (float) centerY);

        List<Point> pts = getPoints(transformAffine(poly));
        System.out.println(pts);

        float[] newChildCenter = {(float)(pts.get(2).x+pts.get(3).x)/2,
                (float)(pts.get(2).y+pts.get(3).y)/2 };

        if(children != null){
            for(MyShape child:children){
                float oldx = child.centerX;
                float oldy = child.centerY;
                child.centerX = newChildCenter[0];
                child.centerY = newChildCenter[1];
                child.transform.postTranslate(child.centerX-oldx, child.centerY-oldy);
                child.rotate(rdegree);
            }
        }
    }

    public void reset(){
        degree = 0;
        transform = new Matrix();
        transform.postTranslate(left,top);
        centerX = oldX;
        centerY = oldY;
        for(MyShape child:children){
            child.reset();
        }
        if(part == "arml1") this.rotate(34);
        if(part == "armr1") this.rotate(-34);

    }

    private List<Point> getPoints(Polygon p){

        List<Line> sides = p.getSides();
        List<Point> points = new ArrayList<Point>();

        for(int i = 0; i < sides.size(); i++){
            points.add(sides.get(i).getStart());
        }
        return points;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void scale(float sy){
        List<Point> pts = getPoints(poly);
            if (part == "legl1" || part == "legr1") {
                transform.postTranslate(-centerX, -centerY);
                transform.postScale(1f, sy);
                transform.postTranslate(centerX,centerY);
               // transform.postScale(1, sy, centerX, centerY);
                for (MyShape child : children) {
                    child.scale(sy);
                    //recenter
                    this.rotate(0);
                }
            }
            if (part == "legl2" || part == "legr2") {
                transform.postTranslate(-centerX, -centerY);
                transform.postScale(1f, sy);
                transform.postTranslate(centerX,centerY);
                for (MyShape child : children) {
                    //recenter
                    this.rotate(0);
                }
                //transform.postScale(1, sy, centerX, centerY);
            }
    }


    public boolean mcontains(float x, float y){
        //System.out.println("Transform, preset: "+transform);

        Matrix inverseT = new Matrix();
        inverseT.set(transform);
        inverseT.invert(inverseT);

        float[] clickPos = {x, y};
        inverseT.mapPoints(clickPos);

        System.out.println("click points");
        System.out.println(clickPos[0] + " " + clickPos[1]);

        List<Point> pts = getPoints(poly);
        System.out.println("polygon's points");
        System.out.println(pts);

        if(poly.contains(new Point(clickPos[0], clickPos[1]))){
            System.out.println("hit some shape");
            return true;
        }
        else{
            return false;
        }
    }

    public Polygon transformAffine(Polygon s){
        List<Point> pts = getPoints(s);

        float[] fpts = new float[pts.size() * 2];
        for(int i = 0; i < pts.size(); i++){
            fpts[i * 2] = (float) pts.get(i).x;
            fpts[i * 2 + 1] = (float) pts.get(i).y;
        }

        transform.mapPoints(fpts);

        List<Point> tpts = new ArrayList<>();
        for(int i = 0; i < pts.size(); i++){
            tpts.add(new Point(fpts[i * 2], fpts[i * 2 + 1]));
        }

        Polygon.Builder pbuilder = new Polygon.Builder();
        for(int i = 0; i < tpts.size(); i++){
            pbuilder.addVertex(tpts.get(i));
        }

        Polygon p = pbuilder.build();
        return p;
    }

    public void draw(Canvas canvas) {
        Polygon p = transformAffine(poly);
        List<Line> lines = p.getSides();
        Paint paint = new Paint();
        paint.setColor(Color.TRANSPARENT);

        for(int i = 0; i < lines.size(); i++){
            canvas.drawLine((float) lines.get(i).getStart().x,
                    (float) lines.get(i).getStart().y,
                    (float) lines.get(i).getEnd().x,
                    (float) lines.get(i).getEnd().y,
                    paint);
        }
        paint.setColor(Color.BLACK);
        if(bitmap != null) canvas.drawBitmap(bitmap, transform, paint);
    }
}