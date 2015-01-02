package kr.co.akidroid.loading;

import java.io.IOException;
import java.util.ArrayList;

import kr.co.akidroid.akinatorapp.R;
import kr.co.akidroid.akinatorapp.TabActivity;
import kr.co.akidroid.loading.MainActivity.loadJsp;
import kr.co.akidroid.session.SessionControl;
import kr.co.akidroid.thread.CalcThread;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


// 회원가입 화면
public class JoinActivity extends Activity {
	TextView phone,email,pw1,pw2,nickname;
	Button submitBtn, backBtn;
	loadJsp task;
	private String result;
	CalcThread calc;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.join_activity);
	
	    // 텍스트 뷰
		phone = (TextView) findViewById(R.id.phone);
		email = (TextView) findViewById(R.id.email);
		pw1 = (TextView) findViewById(R.id.pw1);
		pw2 = (TextView) findViewById(R.id.pw2);
		nickname = (TextView) findViewById(R.id.nickname);
		
		//버튼
		submitBtn = (Button) findViewById(R.id.submitBtn);
		backBtn = (Button) findViewById(R.id.backBtn);
		
		calc = new CalcThread(m_MainHandler);
	    //앱 종료 시키기
	    calc.setDaemon(true);
	    calc.start();
		
		//회원가입 최종 확인버튼
		submitBtn.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            // 저장을 눌렀을때
	            task = new loadJsp();
	            task.execute();
	        }
	    });
		
		//뒤로가기 버튼
		backBtn.setOnClickListener( 
				new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(getBaseContext(), MainActivity.class);
						startActivity(intent);
					}
				}
			);
	}
	public Handler m_MainHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			Log.i("메인핸들러","안오겟지");
			switch(msg.what){
			case 0:
				Toast.makeText(getBaseContext(), "회원가입 성공", 3000).show();

				// 로그인 세션문제
				SessionControl.cookies = SessionControl.httpclient.getCookieStore().getCookies();//추가 
				
				//읽어온 쿠키값을 한번 출력 하여 보자. 
				Cookie cookie;
				if (!SessionControl.cookies.isEmpty())
				 
				{
				     for (int i = 0; i < SessionControl.cookies.size(); i++) 
				     {
				          cookie = SessionControl.cookies.get(i);
				          Log.v("TAG","===>>>"+ cookie.toString() );
				      }
				 }

				Log.i("회원가입 성공","0");
				Intent intent = new Intent(getBaseContext(), TabActivity.class);
				startActivity(intent);
				break;
			case 1:
				Toast.makeText(getApplicationContext(), "예상치 못한 오류로 회원가입이 실패하였습니다.", 3000).show();
				Log.i("회원가입 실패","1");
			}
			
		}
	};
	
	class loadJsp extends AsyncTask<Void,String,Void> {
        @Override
        protected Void doInBackground(Void... param) {
            try{
	            //세션유지
	            HttpClient httpclient = SessionControl.getHttpclient();//변경

	            // jsp 주소
	            String postURL = "http://192.168.10.13:8080/AkinatorApp/android/regFromAndroid.jsp";
	            HttpPost post = new HttpPost(postURL);
	            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
	            
	            //파싱?
	            params.add(new BasicNameValuePair("phone", phone.getText().toString()));
	            params.add(new BasicNameValuePair("nickname", nickname.getText().toString()));
	            params.add(new BasicNameValuePair("email", email.getText().toString()));
	            params.add(new BasicNameValuePair("pw1", pw1.getText().toString()));
	            
	            
	            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params,HTTP.UTF_8);
	            post.setEntity(ent);
	            
//	            HttpResponse responsePOST = client.execute(post);    
//	            HttpEntity resEntity = responsePOST.getEntity();   
	            
	            // jsp에서 out.println을 받아오는곳
	            ResponseHandler<String> reshandler = new BasicResponseHandler();
	    		result = httpclient.execute(post, reshandler).trim();	//이상하게 계속 공백도 같이 저장된다. 공백제거를 위해 trim().
	    		Message msg = Message.obtain();
	    		if (result != null) 
	            {       
//	                Log.i("RESPONSE", EntityUtils.toString(resEntity));  
	                Log.i("RESPONSE2", result);
	                if(result.equals("success")){
	    	    		msg.what = 0;
	    	    		Log.i("핸들러됨","00");
	                }
	                else if(result.equals("fail")){
	    	    		msg.what = 1;
	    	    		Log.i("핸들러됨","11");
	                }
	            }
	    		calc.m_BackHandler.sendMessage(msg);
	            
            
            }catch(IOException e){
                e.printStackTrace();
            }

            return null;
        }
	}
}
        
