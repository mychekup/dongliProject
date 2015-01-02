package kr.co.akidroid.loading;

import kr.co.akidroid.akinatorapp.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;


// �ε�ȭ��
public class SplashActivity extends Activity {
	   Handler h;

	   /** Called when the activity is first created. */
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      requestWindowFeature(Window.FEATURE_NO_TITLE);// Ÿ��Ʋ�� ���ֱ�
	      setContentView(R.layout.splash_activity);

	      h = new Handler();
	      h.postDelayed(run, 2000); // 4�ʵ��� ��Ʈ��
	   }	

	   Runnable run = new Runnable() {

	      @Override
	      public void run() {
	         Intent intent = new Intent(SplashActivity.this, MainActivity.class);
	         startActivity(intent);
	         finish();
	         // fade in���� �����ؼ� fade out���� ��Ʈ�� ȭ���� ����
	         overridePendingTransition(android.R.anim.fade_in,
	               android.R.anim.fade_out);

	      }
	   };

	   // ��Ʈ�� ȭ�� �߰��� �ڷΰ��� ��ư�� ������ ������ �� 4���� ������������ �ߴ� ���� ����
	   @Override
	   public void onBackPressed() {
	      super.onBackPressed();
	      h.removeCallbacks(run);
	   }

	}