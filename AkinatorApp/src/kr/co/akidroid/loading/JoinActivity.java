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


// ȸ������ ȭ��
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
	
	    // �ؽ�Ʈ ��
		phone = (TextView) findViewById(R.id.phone);
		email = (TextView) findViewById(R.id.email);
		pw1 = (TextView) findViewById(R.id.pw1);
		pw2 = (TextView) findViewById(R.id.pw2);
		nickname = (TextView) findViewById(R.id.nickname);
		
		//��ư
		submitBtn = (Button) findViewById(R.id.submitBtn);
		backBtn = (Button) findViewById(R.id.backBtn);
		
		calc = new CalcThread(m_MainHandler);
	    //�� ���� ��Ű��
	    calc.setDaemon(true);
	    calc.start();
		
		//ȸ������ ���� Ȯ�ι�ư
		submitBtn.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            // ������ ��������
	            task = new loadJsp();
	            task.execute();
	        }
	    });
		
		//�ڷΰ��� ��ư
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
			Log.i("�����ڵ鷯","�ȿ�����");
			switch(msg.what){
			case 0:
				Toast.makeText(getBaseContext(), "ȸ������ ����", 3000).show();

				// �α��� ���ǹ���
				SessionControl.cookies = SessionControl.httpclient.getCookieStore().getCookies();//�߰� 
				
				//�о�� ��Ű���� �ѹ� ��� �Ͽ� ����. 
				Cookie cookie;
				if (!SessionControl.cookies.isEmpty())
				 
				{
				     for (int i = 0; i < SessionControl.cookies.size(); i++) 
				     {
				          cookie = SessionControl.cookies.get(i);
				          Log.v("TAG","===>>>"+ cookie.toString() );
				      }
				 }

				Log.i("ȸ������ ����","0");
				Intent intent = new Intent(getBaseContext(), TabActivity.class);
				startActivity(intent);
				break;
			case 1:
				Toast.makeText(getApplicationContext(), "����ġ ���� ������ ȸ�������� �����Ͽ����ϴ�.", 3000).show();
				Log.i("ȸ������ ����","1");
			}
			
		}
	};
	
	class loadJsp extends AsyncTask<Void,String,Void> {
        @Override
        protected Void doInBackground(Void... param) {
            try{
	            //��������
	            HttpClient httpclient = SessionControl.getHttpclient();//����

	            // jsp �ּ�
	            String postURL = "http://192.168.10.13:8080/AkinatorApp/android/regFromAndroid.jsp";
	            HttpPost post = new HttpPost(postURL);
	            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
	            
	            //�Ľ�?
	            params.add(new BasicNameValuePair("phone", phone.getText().toString()));
	            params.add(new BasicNameValuePair("nickname", nickname.getText().toString()));
	            params.add(new BasicNameValuePair("email", email.getText().toString()));
	            params.add(new BasicNameValuePair("pw1", pw1.getText().toString()));
	            
	            
	            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params,HTTP.UTF_8);
	            post.setEntity(ent);
	            
//	            HttpResponse responsePOST = client.execute(post);    
//	            HttpEntity resEntity = responsePOST.getEntity();   
	            
	            // jsp���� out.println�� �޾ƿ��°�
	            ResponseHandler<String> reshandler = new BasicResponseHandler();
	    		result = httpclient.execute(post, reshandler).trim();	//�̻��ϰ� ��� ���鵵 ���� ����ȴ�. �������Ÿ� ���� trim().
	    		Message msg = Message.obtain();
	    		if (result != null) 
	            {       
//	                Log.i("RESPONSE", EntityUtils.toString(resEntity));  
	                Log.i("RESPONSE2", result);
	                if(result.equals("success")){
	    	    		msg.what = 0;
	    	    		Log.i("�ڵ鷯��","00");
	                }
	                else if(result.equals("fail")){
	    	    		msg.what = 1;
	    	    		Log.i("�ڵ鷯��","11");
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
        
