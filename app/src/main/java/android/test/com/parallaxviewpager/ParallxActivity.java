package android.test.com.parallaxviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

public class ParallxActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager pager;
    private HorizontalScrollView scroll;
    private HorizontalScrollView scroll2;
    private ImageView image;
    private ImageView image2;
    private ViewPageAdapter pageAdapter;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallax);
        frameLayout = (FrameLayout)findViewById(R.id.frame_layout);
        pager = (ViewPager) findViewById(R.id.pager);
        scroll = (HorizontalScrollView) findViewById(R.id.scroll);
        scroll2 = (HorizontalScrollView) findViewById(R.id.scroll2);
        image = (ImageView) findViewById(R.id.image);
        image2 = (ImageView)findViewById(R.id.image2);
        pageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        pager.setAdapter(pageAdapter);
        pager.addOnPageChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        scrollImage(image.getWidth()/2, position, positionOffsetPixels, scroll);
        scrollImage(frameLayout.getWidth(), position, positionOffsetPixels, scroll2);
    }

    public void scrollImage(int imageWidth, int position, int offset, HorizontalScrollView scrollView) {
        float widthOfPagers = pager.getWidth() * pageAdapter.getCount();
        float moveWidthOfPagers = widthOfPagers - pager.getWidth();
        float moveWidthOfScroll = imageWidth - pager.getWidth();
        float ratio = moveWidthOfScroll / moveWidthOfPagers;
        float currentPosOfPager = position * pager.getWidth() + offset;
        scrollView.scrollTo((int) (currentPosOfPager * ratio), scroll.getScrollY());
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class ViewPageAdapter extends FragmentStatePagerAdapter {

        public ViewPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return BlankFragment.newInstance();
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
