package kr.co.akidroid.loading;

import kr.co.akidroid.akinatorapp.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;


// 로딩화면
public class SplashActivity extends Activity {
	   Handler h;

	   /** Called when the activity is first created. */
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      requestWindowFeature(Window.FEATURE_NO_TITLE);// 타이틀바 없애기
	      setContentView(R.layout.splash_activity);

	      h = new Handler();
	      h.postDelayed(run, 2000); // 4초동안 인트로
	   }	

	   Runnable run = new Runnable() {

	      @Override
	      public void run() {
	         Intent intent = new Intent(SplashActivity.this, MainActivity.class);
	         startActivity(intent);
	         finish();
	         // fade in으로 시작해서 fade out으로 인트로 화면이 끝남
	         overridePendingTransition(android.R.anim.fade_in,
	               android.R.anim.fade_out);

	      }
	   };

	   // 인트로 화면 중간에 뒤로가기 버튼을 눌러서 꺼졌을 시 4초후 메인페이지가 뜨는 것을 방지
	   @Override
	   public void onBackPressed() {
	      super.onBackPressed();
	      h.removeCallbacks(run);
	   }

	}