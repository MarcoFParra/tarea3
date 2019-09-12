package mp.tareas.tarea3;

import android.animation.StateListAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager mPager1,mPager2,mPager3;
    CustomPagerAdapter2 mAdapter,mAdapter2,mAdapter3;
    int page =0;

    private Boolean isFabOpen = false;
    private FloatingActionButton fab,fab1,fab2,fab3,fab4;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward,win_anim_open,win_anim_close;

    TextView tv_notif;

    int CREDITOS = 0;

    CheckBox chk_20, chk_10, chk_5, chk_2;

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    static class CustomPagerAdapter2 extends PagerAdapter {

        List<Integer>  imgs;
        Activity context;
        public CustomPagerAdapter2(List<Integer> imgs, Activity context) {

            super();
            this.imgs = imgs;
            this.context = context;
        }

        @SuppressLint("NewApi")
        @Override
        public void finishUpdate(ViewGroup container) {
            // TODO Auto-generated method stub
            super.finishUpdate(container);

        }

        @Override
        public int getCount() {
            return imgs.size();
        }

        @Override
        public boolean isViewFromObject(View collection, Object object) {

            return collection == ((View) object);
        }

        @Override
        public Object instantiateItem(View collection, int position) {

            // Inflating layout
            LayoutInflater inflater = (LayoutInflater) collection.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // Setting view you want to display as a row element
            View view = inflater.inflate(R.layout.fragment_content, null);

            view.setRotation(-90f);
            ImageView itemimg =  view.findViewById(R.id.image);



            try {

                itemimg.setImageDrawable(context.getDrawable(imgs.get(position)));

            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            ((ViewPager) collection).addView(view, 0);
            return view;

        }

        @Override
        public void destroyItem(View collection, int position, Object view) {
            ((ViewPager) collection).removeView((View) view);

        }

    }



    ArrayList<Integer> outValues = new ArrayList<>();
    ArrayList<Integer> outOptions = new ArrayList<>();

    int counter = 0;
    int counter2 = 0;
    int counter3 = 0;

    private Button btngivemeTheLoot;
    private LinearLayout bottomSheet;



    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomSheet = (LinearLayout)findViewById(R.id.bottomSheet);

        final BottomSheetBehavior bsb = BottomSheetBehavior.from(bottomSheet);

        btngivemeTheLoot = (Button)findViewById(R.id.btngivemeTheLoot);
        btngivemeTheLoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bsb.setState(BottomSheetBehavior.STATE_EXPANDED);
                givemeTheLoot();
            }
        });


        tv_notif = findViewById(R.id.tv_notif);

        layout = (LinearLayout)findViewById(R.id.ll_loot);


        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        fab3 = (FloatingActionButton)findViewById(R.id.fab3);
        fab4 = (FloatingActionButton)findViewById(R.id.fab4);

        chk_2 = findViewById(R.id.chk_2);
        chk_5 = findViewById(R.id.chk_5);
        chk_10 =findViewById(R.id.chk_10);
        chk_20 =findViewById(R.id.chk_20);

        checkArray();
        chk_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkArray();
            }
        });

        chk_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkArray();

            }
        });

        chk_10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkArray();

            }
        });

        chk_20.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkArray();

            }
        });




        fab1.setImageBitmap(textAsBitmap("1", 40, Color.WHITE,Color.TRANSPARENT));
        fab2.setImageBitmap(textAsBitmap("2", 40, Color.WHITE,Color.TRANSPARENT));
        fab3.setImageBitmap(textAsBitmap("5", 40, Color.WHITE,Color.TRANSPARENT));
        fab4.setImageBitmap(textAsBitmap("10", 40, Color.WHITE,Color.TRANSPARENT));

        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);

        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);

        win_anim_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        win_anim_close =  AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);

        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
        fab4.setOnClickListener(this);




        mPager1 = (ViewPager) findViewById(R.id.pager1);
        mPager1.setRotation(90);

        mPager2 = (ViewPager) findViewById(R.id.pager2);
        mPager2.setRotation(90);


        mPager3 = (ViewPager) findViewById(R.id.pager3);
        mPager3.setRotation(90);


        List<Integer> fragments = new ArrayList<>();
        fragments.add(R.drawable.ic_img_1);
        fragments.add(R.drawable.ic_img_2);
        fragments.add(R.drawable.ic_img_3);
        fragments.add(R.drawable.ic_img_4);


        mAdapter = new CustomPagerAdapter2(fragments,this);
        mAdapter2 = new CustomPagerAdapter2(fragments,this);
        mAdapter3 = new CustomPagerAdapter2(fragments,this);

        mPager1.setAdapter(mAdapter);
        mPager2.setAdapter(mAdapter2);
        mPager3.setAdapter(mAdapter3);

        findViewById(R.id.btn).setOnClickListener(this);

    }




    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.fab:
                animateFAB();
                break;
            case R.id.fab1:
                CREDITOS+=1;
                updateCreditsUI();
                break;
            case R.id.fab2:
                CREDITOS+=2;
                updateCreditsUI();
                break;
            case R.id.fab3:
                CREDITOS+=5;
                updateCreditsUI();
                break;
            case R.id.fab4:
                CREDITOS+=10;
                updateCreditsUI();
                break;
            case  R.id.btn:
                start();
                break;

        }
    }


    void checkArray() {
        outOptions.clear();
        if (chk_20.isChecked())
            outOptions.add(20);
        if (chk_10.isChecked())
            outOptions.add(10);
        if (chk_5.isChecked())
            outOptions.add(5);
        if (chk_2.isChecked())
            outOptions.add(2);
        outOptions.add(1);
    }

    public void givemeTheLoot()
    {
        outValues.clear();
        int index =0;
        while (CREDITOS != 0)
        {
            if (outOptions.get(index)<= CREDITOS)
            {
                CREDITOS-= outOptions.get(index);
                outValues.add(outOptions.get(index));
            }
            else index++;
        }
        showTheLoot();

    }

    ImageView imageview;
    private void showTheLoot() {

        LinearLayout.LayoutParams params = new LinearLayout
                .LayoutParams(100, 100);
        params.setMargins(15,0,15,0);
        for (int i =0; i<outValues.size(); i++) {
            imageview = new ImageView(MainActivity.this);
            imageview.setImageBitmap(textAsBitmap(
                    outValues.get(i)<10 ? "  " + outValues.get(i).toString() +"  ": " " +outValues.get(i).toString() +" "
                    , 80, Color.WHITE ,getResources().getColor(R.color.colorPrimary)));
            imageview.setLayoutParams(params);
            //imageview.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

            layout.addView(imageview);
        }
        updateCreditsUI();

    }

    int valorActual=0;
    private void updateCreditsUI() {
        final Timer time = new Timer();
        final Handler handler = new Handler();



        if (CREDITOS <0)
            CREDITOS=0;

                TimerTask timer = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        if (((EditText)findViewById(R.id.et_credits)).getText().toString().length()==0)
                        {
                            valorActual=0;
                        }
                        else
                        valorActual = Integer.valueOf(((EditText)findViewById(R.id.et_credits)).getText().toString());

                        if (valorActual <CREDITOS)
                        {
                            valorActual +=1;
                            ((EditText)findViewById(R.id.et_credits)).setText(String.valueOf(valorActual));

                        }
                        else if (valorActual > CREDITOS)
                        {
                            valorActual -=1;
                            ((EditText)findViewById(R.id.et_credits)).setText(String.valueOf(valorActual));

                        }
                        else if( valorActual == CREDITOS) {
                            ((EditText)findViewById(R.id.et_credits)).setText(String.valueOf(valorActual));
                            time.cancel();

                        }
                    }
                });
            }
        };
        time.purge();
        time.schedule(timer, 0, 50);
    }

    public void animateFAB(){

        if(isFabOpen){

            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab3.startAnimation(fab_close);
            fab4.startAnimation(fab_close);


            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);
            fab4.setClickable(false);

            isFabOpen = false;

        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab3.startAnimation(fab_open);
            fab4.startAnimation(fab_open);

            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);
            fab4.setClickable(true);

            isFabOpen = true;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    final int val = ThreadLocalRandom.current().nextInt(30, 40);
    void start()
    {
        layout.removeAllViews();
        if (CREDITOS < 7) {
            Snackbar.make(findViewById(R.id.root), "No hay creditos suficientes", Snackbar.LENGTH_SHORT)
                    .show();
            return;
        }
        CREDITOS-=7;
        updateCreditsUI();

        final Timer time = new Timer();
        final Handler handler = new Handler();

        final Timer time1 = new Timer();
        final Handler handler1 = new Handler();


        final Timer time2 = new Timer();
        final Handler handler2 = new Handler();

        counter = counter2 = counter3 = 0;

        TimerTask timer = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        int numPages = mPager1.getAdapter().getCount();
                        page = (page + 1) % numPages;
                        mPager1.setCurrentItem(page);

                        if(++counter == val) {
                            time.cancel();
                            getCredits();

                        }
                    }
                });
            }
        };
        time.purge();
        time.schedule(timer, 0, ThreadLocalRandom.current().nextInt(225, 250));

        final int val2 = ThreadLocalRandom.current().nextInt(20, 30);
        TimerTask timer1 = new TimerTask() {
            @Override
            public void run() {
                handler1.post(new Runnable() {

                    @Override
                    public void run() {
                        int numPages = mPager1.getAdapter().getCount();
                        page = (page + 1) % numPages;
                        mPager2.setCurrentItem(page);

                        if(++counter2 == val2) {
                            time1.cancel();

                        }
                    }
                });
            }
        };
        time1.purge();
        time1.schedule(timer1, 500, ThreadLocalRandom.current().nextInt(225, 250));

        final int val3 = ThreadLocalRandom.current().nextInt(10, 20);
        TimerTask timer2 = new TimerTask() {
            @Override
            public void run() {
                handler2.post(new Runnable() {

                    @Override
                    public void run() {
                        int numPages = mPager1.getAdapter().getCount();
                        page = (page + 1) % numPages;
                        mPager3.setCurrentItem(page);

                        if(++counter3 == val3) {
                            time2.cancel();
                        }
                    }
                });
            }
        };
        time2.schedule(timer2, 1000, ThreadLocalRandom.current().nextInt(225, 250));
    }

    private void getCredits() {
        int count =0;
        int pos1 = mPager1.getCurrentItem();
        int pos2 = mPager2.getCurrentItem();
        int pos3 = mPager3.getCurrentItem();

        if (pos1 == pos2 && pos1 == pos3)
            count=2;
        else if (pos1 == pos2 || pos1 == pos3 || pos2 == pos3)
            count= 1;


        switch (count)
        {
            case 1:
                CREDITOS+=101;
                tv_notif.setText("+101");
                tv_notif.setTextColor( getResources().getColor(android.R.color.holo_blue_dark));
                win_anim_open.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        tv_notif.setClickable(false);
                        tv_notif.startAnimation(win_anim_close);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                tv_notif.startAnimation(win_anim_open);
                tv_notif.setClickable(true);

                updateCreditsUI();

                break;
            case 2:
                CREDITOS+=175;
                tv_notif.setText("+175");
                tv_notif.setTextColor( getResources().getColor(android.R.color.holo_blue_bright));
                win_anim_open.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        tv_notif.setClickable(false);
                        tv_notif.startAnimation(win_anim_close);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                tv_notif.startAnimation(win_anim_open);

                tv_notif.setClickable(true);
                updateCreditsUI();

                break;

                default:

                    break;

        }
    }

    public static Bitmap textAsBitmap(String text, float textSize, int textColor,int colorback) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);

        Paint paint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint3.setTextSize(textSize);
        paint3.setColor(colorback);
        paint3.setTextAlign(Paint.Align.LEFT);

        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.0f); // round
        int height = (int) (baseline + paint.descent() + 0.0f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(image);
        canvas.drawCircle(width/2 , height/2,height/2 ,paint3);
        canvas.drawText(text, 0, baseline, paint);
        return image;
    }

}
