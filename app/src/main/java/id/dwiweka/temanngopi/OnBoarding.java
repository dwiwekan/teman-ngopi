package id.dwiweka.temanngopi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class OnBoarding extends AppCompatActivity {

    private OnBoardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private MaterialButton buttonOnBoardingScreenAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        layoutOnboardingIndicators = findViewById(R.id.layoutOnboarding);
        buttonOnBoardingScreenAction = findViewById(R.id.buttonOnBoardingScreen);

        setupOnboardingItems();

        ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setupOnboardingIndicators();
        setCurrentOnboardingIndicator(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
            @Override
            public void onPageSelected(int position){
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });

        buttonOnBoardingScreenAction.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View view){
               if (onboardingViewPager.getCurrentItem()+1<onboardingAdapter.getItemCount()){
                   onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem()+1);
               }else{
                   startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                   finish();
               }
           }
        });

    }

    private void setupOnboardingItems(){

        List<OnBoardingItem> onboardingItems = new ArrayList<>();

        OnBoardingItem itemPayOnline = new OnBoardingItem();
        itemPayOnline.setTitle("Tempat Kopi");
        itemPayOnline.setDescription("Tempat kopi yang asik memang mantap");
        itemPayOnline.setImage(R.drawable.onboard1);

        OnBoardingItem itemOnTheWay = new OnBoardingItem();
        itemOnTheWay.setTitle("Nongkrong Bareng");
        itemOnTheWay.setDescription("Emang paling enak sih sama temen, \nsama orang asing juga\nngga masalah");
        itemOnTheWay.setImage(R.drawable.onboard2);

        OnBoardingItem itemReady = new OnBoardingItem();
        itemReady.setTitle("Ayo bergabung");
        itemReady.setDescription("Klik next nih, habistu join dah");
        itemReady.setImage(R.drawable.logo);

        onboardingItems.add(itemPayOnline);
        onboardingItems.add(itemOnTheWay);
        onboardingItems.add(itemReady);

        onboardingAdapter = new OnBoardingAdapter(onboardingItems);
    }

    private void setupOnboardingIndicators(){
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for(int i=0;i<indicators.length;i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicators.addView(indicators[i]);
        }

    }

    private void setCurrentOnboardingIndicator(int index){
        int childCount = layoutOnboardingIndicators.getChildCount();
        for (int i=0;i<childCount;i++){
            ImageView imageView = (ImageView) layoutOnboardingIndicators.getChildAt(i);
            if(i==index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_active));

            }else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_inactive));
            }

        }
        if (index == onboardingAdapter.getItemCount()-1){
            buttonOnBoardingScreenAction.setText("Start");
        }else{
            buttonOnBoardingScreenAction.setText("Next");
        }
    }


}