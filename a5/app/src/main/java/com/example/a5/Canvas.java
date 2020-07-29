package com.example.a5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import com.snatik.polygon.Point;
import com.snatik.polygon.Polygon;

import androidx.annotation.Nullable;

import java.util.ArrayList;

/*
images are from
"https://zh.pngtree.com/freepng/human-body-skeleton-model_4453747.html"
 */
public class Canvas extends View {

    /* */

    private Polygon head;
    public MyShape headS;
    public Bitmap headB;

    private Polygon torso;
    public MyShape torsoS;
    public Bitmap torsoB;

    /* */

    private Polygon legl1;
    public MyShape legl1S;
    public Bitmap legl1B;

    private Polygon legl2;
    public MyShape legl2S;
    public Bitmap legl2B;

    private Polygon feetl;
    public MyShape feetlS;
    public Bitmap feetlB;


    /* */

    private Polygon legr1;
    public MyShape legr1S;
    public Bitmap legr1B;

    private Polygon legr2;
    public MyShape legr2S;
    public Bitmap legr2B;

    private Polygon feetr;
    public MyShape feetrS;
    public Bitmap feetrB;


    /* */

    private Polygon arml1;
    public MyShape arml1S;
    public Bitmap arml1B;

    private Polygon arml2;
    public MyShape arml2S;
    public Bitmap arml2B;

    private Polygon handl;
    public MyShape handlS;
    public Bitmap handlB;

    /* */

    private Polygon armr1;
    public MyShape armr1S;
    public Bitmap armr1B;

    private Polygon armr2;
    public MyShape armr2S;
    public Bitmap armr2B;

    private Polygon handr;
    public MyShape handrS;
    public Bitmap handrB;




    /* */

    private ScaleGestureDetector SGD;
    float scale = 1f;
    MyShape currentShape;
    MyShape currentShape2;
    float prevX = 0;
    float prevY = 0;


    public ArrayList<MyShape> shapes;

    public Canvas(Context context) {
        super(context);
        SGD = new ScaleGestureDetector(context, new ScaleListener());
        init(null);
        initCanvas();

    }

    public Canvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        SGD = new ScaleGestureDetector(context, new ScaleListener());
        init(attrs);
        initCanvas();
    }

    public Canvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        SGD = new ScaleGestureDetector(context, new ScaleListener());
        init(attrs);
        initCanvas();

    }

    public Canvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        SGD = new ScaleGestureDetector(context, new ScaleListener());
        init(attrs);
        initCanvas();
    }
    private void init (@Nullable AttributeSet set){
        System.out.println("got init");
        shapes = new ArrayList<>();
    }
    public void initCanvas(){

        /**/

        //torso
        torso = Polygon.Builder()
                .addVertex(new Point(0, 0))
                .addVertex(new Point(260, 0))
                .addVertex(new Point(260, 500))
                .addVertex(new Point(0, 500)).build();

        torsoS = new MyShape("torso", null, torso, 1150, 300);
        shapes.add(torsoS);
        torsoB = BitmapFactory.decodeResource(getResources(), R.drawable.torso);
        torsoB = Bitmap.createScaledBitmap(torsoB, 260, 500, false);
        torsoS.setBitmap(torsoB);

        //head
        head = Polygon.Builder()
                .addVertex(new Point(0, 0))
                .addVertex(new Point(120, 0))
                .addVertex(new Point(120, 150))
                .addVertex(new Point(0, 150)).build();

        headS = new MyShape("head", torsoS, head, 1220, 150, 1280, 300);
        shapes.add(headS);

        headB = BitmapFactory.decodeResource(getResources(), R.drawable.head);
        headB = Bitmap.createScaledBitmap(headB, 120, 150, false);
        headS.setBitmap(headB);


        /**/

        //legl1
        legl1 = Polygon.Builder()
                .addVertex(new Point(0, 0))
                .addVertex(new Point(100, 0))
                .addVertex(new Point(100, 250))
                .addVertex(new Point(0, 250)).build();

        legl1S = new MyShape("legl1", torsoS, legl1, 1150, 770, 1200, 770);
        shapes.add(legl1S);
        legl1B = BitmapFactory.decodeResource(getResources(), R.drawable.legl1);
        legl1B = Bitmap.createScaledBitmap(legl1B, 100, 250, false);
        legl1S.setBitmap(legl1B);

        //legl2
        legl2 = Polygon.Builder()
                .addVertex(new Point(0, 0))
                .addVertex(new Point(100, 0))
                .addVertex(new Point(100, 200))
                .addVertex(new Point(0, 200)).build();

        legl2S = new MyShape("legr2", legl1S, legl2, 1150, 1020, 1200, 1020);
        shapes.add(legl2S);
        legl2B = BitmapFactory.decodeResource(getResources(), R.drawable.legl2);
        legl2B = Bitmap.createScaledBitmap(legl2B, 100, 200, false);
        legl2S.setBitmap(legl2B);

        //feetl
        feetl = Polygon.Builder()
                .addVertex(new Point(0, 0))
                .addVertex(new Point(200, 0))
                .addVertex(new Point(200, 50))
                .addVertex(new Point(0, 50)).build();

        feetlS = new MyShape("feetl", legl2S, feetl, 1000, 1195, 1200, 1220);
        shapes.add(feetlS);
        shapes.add(feetlS);
        feetlB = BitmapFactory.decodeResource(getResources(), R.drawable.feetl);
        feetlB = Bitmap.createScaledBitmap(feetlB, 200, 50, false);
        feetlS.setBitmap(feetlB);

        /**/

        //legr1
        legr1 = Polygon.Builder()
                .addVertex(new Point(0, 0))
                .addVertex(new Point(100, 0))
                .addVertex(new Point(100, 250))
                .addVertex(new Point(0, 250)).build();

        legr1S = new MyShape("legr1", torsoS, legr1, 1310, 770, 1360, 770);
        shapes.add(legr1S);
        legr1B = BitmapFactory.decodeResource(getResources(), R.drawable.legr1);
        legr1B = Bitmap.createScaledBitmap(legr1B, 100, 250, false);
        legr1S.setBitmap(legr1B);

        //legr2
        legr2 = Polygon.Builder()
                .addVertex(new Point(0, 0))
                .addVertex(new Point(100, 0))
                .addVertex(new Point(100, 200))
                .addVertex(new Point(0, 200)).build();

        legr2S = new MyShape("legr2", legr1S, legr2, 1310, 1020, 1360, 1020);
        shapes.add(legr2S);
        legr2B = BitmapFactory.decodeResource(getResources(), R.drawable.legr2);
        legr2B = Bitmap.createScaledBitmap(legr2B, 100, 200, false);
        legr2S.setBitmap(legr2B);


        //feetr
        feetr = Polygon.Builder()
                .addVertex(new Point(0, 0))
                .addVertex(new Point(200, 0))
                .addVertex(new Point(200, 50))
                .addVertex(new Point(0, 50)).build();

        feetrS = new MyShape("feetr", legr2S, feetr, 1360, 1195, 1360, 1220);
        shapes.add(feetrS);
        feetrB = BitmapFactory.decodeResource(getResources(), R.drawable.feetr);
        feetrB = Bitmap.createScaledBitmap(feetrB, 200, 50, false);
        feetrS.setBitmap(feetrB);

        /**/

        //arml1
        arml1 = Polygon.Builder()
                .addVertex(new Point(0, 0))
                .addVertex(new Point(100, 0))
                .addVertex(new Point(100, 250))
                .addVertex(new Point(0, 250)).build();

        arml1S = new MyShape("arml1", torsoS, arml1, 1100, 380, 1150, 380);
        shapes.add(arml1S);
        arml1B = BitmapFactory.decodeResource(getResources(), R.drawable.armr1);
        arml1B = Bitmap.createScaledBitmap(arml1B, 100, 250, false);
        arml1S.setBitmap(arml1B);

        //arml2
        arml2 = Polygon.Builder()
                .addVertex(new Point(0, 0))
                .addVertex(new Point(100, 0))
                .addVertex(new Point(100, 200))
                .addVertex(new Point(0, 200)).build();

        arml2S = new MyShape("armr2", arml1S, arml2, 1100, 600, 1150, 600);
        shapes.add(arml2S);
        arml2B = BitmapFactory.decodeResource(getResources(), R.drawable.arml2);
        arml2B = Bitmap.createScaledBitmap(arml2B, 100, 200, false);
        arml2S.setBitmap(arml2B);

        //handl
        handl = Polygon.Builder()
                .addVertex(new Point(0, 0))
                .addVertex(new Point(100, 0))
                .addVertex(new Point(100, 100))
                .addVertex(new Point(0, 100)).build();

        handlS = new MyShape("handl", arml2S, handl, 1100, 830, 1150, 830);
        shapes.add(handlS);
        shapes.add(handlS);
        handlB = BitmapFactory.decodeResource(getResources(), R.drawable.handl);
        handlB = Bitmap.createScaledBitmap(handlB, 100, 100, false);
        handlS.setBitmap(handlB);

        /**/

        //armr1
        armr1 = Polygon.Builder()
                .addVertex(new Point(0, 0))
                .addVertex(new Point(100, 0))
                .addVertex(new Point(100, 250))
                .addVertex(new Point(0, 250)).build();

        armr1S = new MyShape("armr1", torsoS, armr1, 1360, 380, 1410, 380);
        shapes.add(armr1S);
        armr1B = BitmapFactory.decodeResource(getResources(), R.drawable.armr1);
        armr1B = Bitmap.createScaledBitmap(armr1B, 100, 250, false);
        armr1S.setBitmap(armr1B);

        //armr2
        armr2 = Polygon.Builder()
                .addVertex(new Point(0, 0))
                .addVertex(new Point(100, 0))
                .addVertex(new Point(100, 200))
                .addVertex(new Point(0, 200)).build();

        armr2S = new MyShape("armr2", armr1S, armr2, 1360, 630, 1410, 630);
        shapes.add(armr2S);
        armr2B = BitmapFactory.decodeResource(getResources(), R.drawable.armr2);
        armr2B = Bitmap.createScaledBitmap(armr2B, 100, 200, false);
        armr2S.setBitmap(armr2B);

        //handr
        handr = Polygon.Builder()
                .addVertex(new Point(0, 0))
                .addVertex(new Point(100, 0))
                .addVertex(new Point(100, 100))
                .addVertex(new Point(0, 100)).build();

        handrS = new MyShape("handr", armr2S, handr, 1360, 830, 1410, 830);
        shapes.add(handrS);
        handrB = BitmapFactory.decodeResource(getResources(), R.drawable.handl);
        handrB = Bitmap.createScaledBitmap(handrB, 100, 100, false);
        handrS.setBitmap(handrB);


        //init rotation
        arml1S.rotate(34);
        armr1S.rotate(-34);

    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale = detector.getScaleFactor();
            scale = Math.max(0.1f, Math.min(scale,1.3f));
            if(currentShape != null)currentShape.scale(scale);
            System.out.println("on scale run");
            postInvalidate();
            return true;
        }
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // could handle input here
        //Log.d("DEBUG", "touch " + event.getX() + "," + event.getY());
        //SGD.onTouchEvent(event);
        float x = event.getX();
        float y = event.getY();


        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                for (MyShape shape : shapes) {
                    if (shape.mcontains(event.getX(), event.getY())) {
                        currentShape = shape;
                        break;
                    }
                }
                prevX = x;
                prevY = y;
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (currentShape == null) {
                    return true;
                }
                System.out.println("currentshape is " + currentShape.part );
                if(currentShape.part == "torso") {
                    currentShape.translate(x, y, prevX, prevY);
                } else{
                    currentShape.rotate(x, y, prevX, prevY);
                }
                postInvalidate();
                prevX = x;
                prevY = y;
                break;
            }
            case MotionEvent.ACTION_UP: {
                currentShape = null;
//                return false;
            }

            case MotionEvent.ACTION_POINTER_DOWN:

                for(MyShape shape: shapes){
                    if(shape.mcontains(x, y)){
                        currentShape2 = shape;
                        break;
                    }
                }

                if(currentShape2 == null){
                    System.out.println("no second com");
                    return true;
                }

                break;

            case MotionEvent.ACTION_POINTER_UP:
                currentShape2 = null;

                break;
        }

        if(currentShape != null && currentShape2 != null && currentShape.equals(currentShape2)){
            SGD.onTouchEvent(event);
            postInvalidate();
        }
        return true;
    }

    public void reset(){
        torsoS.reset();
        postInvalidate();
    }


    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        for(MyShape shape:shapes){
            shape.draw(canvas);
        }
    }
}




