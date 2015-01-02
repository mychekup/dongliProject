package kr.co.akidroid.akinatorapp;
/*
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TabHost;
import android.widget.ViewAnimator;

public class MainActivity extends android.app.TabActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);

		TabHost host = getTabHost();
		Drawable draw = null;
		Resources res = getResources(); // res안에있는 리소스 가져오기

		TabHost.TabSpec spec = host.newTabSpec("tab01"); // 1번째 탭 생성
		Intent intent = new Intent(getApplicationContext(), sub1Activity.class);
		spec.setContent(intent);
		draw = res.getDrawable(R.drawable.food);
		spec.setIndicator("SubPage01", draw);// 만들어진 탭에 대한 실제 내용
		host.addTab(spec); // 탭추가

		spec = host.newTabSpec("tab02");
		intent = new Intent(getApplicationContext(), sub2Activity.class);
		spec.setContent(intent);
		draw = res.getDrawable(R.drawable.garden);
		spec.setIndicator("SubPage01", draw);
		spec.setIndicator("SubPage02");
		host.addTab(spec);

		spec = host.newTabSpec("tab03");
		intent = new Intent(getApplicationContext(), sub3Activity.class);
		spec.setContent(intent);
		draw = res.getDrawable(R.drawable.school);
		spec.setIndicator("SubPage01", draw);
		spec.setIndicator("SubPage03");
		host.addTab(spec);

		host.setCurrentTab(0);
	}
}

*/

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.ViewFlipper;

public class TabActivity extends android.app.TabActivity implements OnTouchListener {
	/** ViewFlipper 컴포넌트 객체 */
	private ViewFlipper m_viewFlipper;
	/** ViewFilpper 안에서 터치된 X축의 좌표 */
	private int m_nPreTouchPosX = 0;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);

		TabHost host = getTabHost();
		Drawable draw = null;
		Resources res = getResources(); // res안에있는 리소스 가져오기

		
		TabHost.TabSpec spec = host.newTabSpec("tab01"); // 1번째 탭 생성
		Intent intent = new Intent(getApplicationContext(), sub1Activity.class);
		spec.setContent(intent);
		draw = res.getDrawable(R.drawable.food);
		spec.setIndicator("물품리스트", draw);// 만들어진 탭에 대한 실제 내용
		host.addTab(spec); // 탭추가

		spec = host.newTabSpec("tab02");
		intent = new Intent(getApplicationContext(), sub2Activity.class);
		spec.setContent(intent);
		draw = res.getDrawable(R.drawable.garden);
		spec.setIndicator("로그인", draw);
		spec.setIndicator("SubPage02");
		host.addTab(spec);

		spec = host.newTabSpec("tab03");
		intent = new Intent(getApplicationContext(), sub3Activity.class);
		spec.setContent(intent);
		draw = res.getDrawable(R.drawable.school);
		spec.setIndicator("SubPage01", draw);
		spec.setIndicator("SubPage03");
		host.addTab(spec);

		host.setCurrentTab(0);
		
		m_viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper1);
		m_viewFlipper.setOnTouchListener(this);
		//Log.d("메인실행전","메인실행전");
		
		// ViewFlipper에 서브 레이아웃 추가
		LinearLayout sub1 = (LinearLayout) View.inflate(getBaseContext(),
				R.layout.activity_sub1test, null);
//		GridView sub1 = (GridView) View.inflate(getBaseContext(),
//				R.layout.activity_sub1, null);
		LinearLayout sub2 = (LinearLayout) View.inflate(getBaseContext(),
				R.layout.activity_sub2, null);
		LinearLayout sub3 = (LinearLayout) View.inflate(getBaseContext(),
				R.layout.activity_sub3, null);
		m_viewFlipper.addView(sub1);
		m_viewFlipper.addView(sub2);
		m_viewFlipper.addView(sub3);
	}

	private void MoveNextView() {
		m_viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this,
				R.anim.right_in));
		m_viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,
				R.anim.left_out));
		m_viewFlipper.showNext();
	}

	private void MovewPreviousView() {
		m_viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this,
				R.anim.left_in));
		m_viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,
				R.anim.right_out));
		m_viewFlipper.showPrevious();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			m_nPreTouchPosX = (int) event.getX();
		}

		if (event.getAction() == MotionEvent.ACTION_UP) {
			int nTouchPosX = (int) event.getX();

			if (nTouchPosX < m_nPreTouchPosX) {
				MoveNextView();
			} else if (nTouchPosX > m_nPreTouchPosX) {
				MovewPreviousView();
			}

			m_nPreTouchPosX = nTouchPosX;
		}

		return true;
	}
}