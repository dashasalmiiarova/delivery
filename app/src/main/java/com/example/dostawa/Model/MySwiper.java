//package com.example.dostawa.Model;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Point;
//import android.graphics.Rect;
//import android.graphics.RectF;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.view.GestureDetector;
//import android.view.MotionEvent;
//import android.view.View;
//
//import androidx.annotation.NonNull;
//import androidx.core.content.ContextCompat;
//import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.recyclerview.widget.ItemTouchHelper;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.room.Query;
//
//import com.example.dostawa.Callback.MyButtonClickListener;
//import com.firebase.ui.auth.data.model.Resource;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Queue;
//
//import static com.makeramen.roundedimageview.RoundedDrawable.drawableToBitmap;
//
//public class MySwiper extends ItemTouchHelper.SimpleCallback {
//    int buttonWidth;
//    private RecyclerView recyclerView;
//    private List<MyButton> buttonList;
//    private GestureDetector gestureDetector;
//    private int swipePosition = -1;
//    private float swipeThreshold=0.5f;
//    private Map<Integer,List<MyButton>> buttonBuffer;
//    private Queue<Integer> removeQueue;
//    private GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener(){
//        @Override
//        public boolean onSingleTapUp(MotionEvent e) {
//            for (MyButton button:buttonList)
//                if (button.onClick(e.getX(),e.getY()))
//                    break;
//
//            return true;
//        }
//    };
//    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            if (swipePosition < 0) return false;
//            Point point = new Point((int)event.getRawX(), (int)event.getRawY());
//            RecyclerView.ViewHolder swipeViewHolder = recyclerView.findViewHolderForAdapterPosition(swipePosition);
//            View swipedItem = swipeViewHolder.itemView;
//            Rect react = new Rect();
//            swipedItem.getGlobalVisibleRect(react);
//            if (event.getAction() == event.ACTION_DOWN || event.getAction() == event.ACTION_UP ||
//                    event.getAction() == event.ACTION_MOVE) {
//                if (react.top < point.y && react.bottom > point.y)
//                {
//                    gestureDetector.onTouchEvent(event);
//                }
//                else {
//                    removeQueue.add(swipePosition);
//                    swipePosition = -1;
//                }
//                return false;
//            }
//        }
//    }
//
//    private class MyButton {
//        private String text;
//        private int imageResId, textSize, color, pos;
//        private RectF clickRegion;
//        private MyButtonClickListener listener;
//        private Context context;
//        private Resource resource;
//
//        public MyButton(String text, int imageResId, int textSize, int color, MyButtonClickListener listener, Context context) {
//            this.text = text;
//            this.imageResId = imageResId;
//            this.textSize = textSize;
//            this.color = color;
//            this.listener = listener;
//            this.context = context;
//        }
//        public boolean onClick(float x, float y){
//            if (clickRegion != null && clickRegion.contains(x,y)){
//                listener.onClick(pos);
//                return true;
//            }
//            return false;
//        }
//
//        public void onDraw(Canvas c, RectF rectF, int pos){
//            Paint p = new Paint();
//            p.setColor(color);
//            c.drawRect(rectF,p);
//
//            p.setColor(Color.WHITE);
//            p.setTextSize(textSize);
//
//            Rect r = new Rect();
//            float cHeight = rectF.height();
//            float cWidth = rectF.width();
//            p.setTextAlign(Paint.Align.LEFT);
//            p.getTextBounds(text, 0,text.length(),r);
//            float x=0, y=0;
//            if (imageResId == 0)
//            {
//                x = cWidth/2f-r.width()/2f-r.left;
//                y = cHeight/2f + r.height()/2f - r.bottom;
//                c.drawText(text,rectF.left+x,rectF.top+y,p);
//            } else
//            {
//                Drawable d = ContextCompat.getDrawable(context,imageResId);
//                Bitmap bitmap = drawableToBitma(d);
//                c.drawBitmap(bitmap, (rectF.left+rectF.right)/2, (rectF.top+rectF.bottom)/2,p);
//            }
//        }
//    }
//
//    private Bitmap drawableToBitma(Drawable d) {
//        if (d instanceof BitmapDrawable)
//            return ((BitmapDrawable)d).getBitmap();
//        Bitmap bitmap = Bitmap.createBitmap(d.getIntrinsicWidth(),d.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        d.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
//        d.draw(canvas);
//        return bitmap;
//    }
//
//    @Override
//    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//        return false;
//    }
//
//    @Override
//    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//
//    }
//
//
//}
