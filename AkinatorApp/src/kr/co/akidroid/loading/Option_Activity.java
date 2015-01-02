package kr.co.akidroid.loading;

import kr.co.akidroid.akinatorapp.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Option_Activity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.option_activitiy);
	
	    Button op1btn = (Button) findViewById(R.id.op1btn);
	    Button op2btn = (Button) findViewById(R.id.op2btn);
	    
	    //공지 버튼
	    op1btn.setOnClickListener(    
	    		new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(getBaseContext(), Notice_Activity.class);
						startActivity(intent);
						
					}
				}
	    	);
	    //운영정책
	    op2btn.setOnClickListener(    
	    	new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(getBaseContext(), Operate_Activity.class);
					startActivity(intent);
				}
			}
	    );
	    //
	}

}