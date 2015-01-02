package kr.co.akidroid.akinatorapp;

import android.app.Activity;
import android.os.Bundle;

public class sub2Activity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_sub2);
	}
/*	TextView userId,textview,pass;
	Button btnSave;
	loadJsp task;
	private static String setText1;
	private String result;
	CalcThread calc;
	
	*//** Called when the activity is first created. *//*
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_sub2);
	    // TODO Auto-generated method stub
	    userId = (TextView)findViewById(R.id.userId);
	    pass =  (TextView)findViewById(R.id.pass);
	    textview = (TextView)findViewById(R.id.tv1);
	    btnSave = (Button)findViewById(R.id.btnSave);
	    
	    
	    calc = new CalcThread(m_MainHandler);
	    //앱 종료 시키기
	    calc.setDaemon(true);
	    calc.start();
	    
	    
//	    textview.setText(setText1);
	    
	    btnSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 저장을 눌렀을때
                task = new loadJsp();
                task.execute();
            }
        });
	}
	public Handler m_MainHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case 0:
				Toast.makeText(getBaseContext(), "로그인성공", 3000).show();

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

				Log.i("로그인성공","0");
				Intent intent = new Intent(getApplicationContext(), TabActivity.class);
				startActivity(intent);
				break;
			case 1:
				Toast.makeText(getApplicationContext(), "로그인실패", 3000).show();
				Log.i("로그인실패","1");
			}
			
		}
	};
	
	class loadJsp extends AsyncTask<Void,String,Void> {
        @Override
        protected Void doInBackground(Void... param) {
            // TODO Auto-generated method stub     
            try{
            	//세션 필요없을땐 요거 
	            //HttpClient client = new DefaultHttpClient();
	            
	            //세션유지할떈 이거쓰려나?
	            HttpClient httpclient = SessionControl.getHttpclient();//변경

	            // jsp 주소
	            String postURL = "http://192.168.10.13:8080/AkinatorApp/android/dataReceiver.jsp";
	            HttpPost post = new HttpPost(postURL);
	            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
	            
	            //파싱?
	            params.add(new BasicNameValuePair("userId",userId.getText().toString()));
	            params.add(new BasicNameValuePair("pass",pass.getText().toString()));
	            
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
        

//핸들러 사용을 위한 스레드
class CalcThread extends Thread{
	private Handler m_MainHandler;
	
	// 생성자를 통하여 값을 전달
	public CalcThread(Handler handler){
		m_MainHandler = handler;
	}
	
	@Override
	public void run() {
		// 연결고리인 루퍼 만들어주기
		Looper.prepare();
		Looper.loop();
	}
	
	// 메세지 객체가 보낸 것을 받는 핸들러
	// 이것은 루퍼에 의해서 자동으로 호출이 가능한 것인데
	// 메인에는 자동으로 루퍼가 있지만 
	// 다른 클래스에서는 루퍼를 따로 만들어 주어야한다.
	// 루퍼를 run()에 만든다.
	public Handler m_BackHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// 스레드에서 결과값을 메인클래스로 보내주기
			Message rtnMsg = Message.obtain();
			// what으로 무엇을 처리할지 구분해 주기
			if(msg.what ==0){
//				//Log.d("CalcThread",String.valueOf(msg.arg1));
//				int result = msg.arg1 * msg.arg1;
				rtnMsg.what=0;
//				rtnMsg.arg1=result;
			}
			else if(msg.what ==1){
//				double result = Math.sqrt((double)msg.arg1);
				rtnMsg.what=1;
//				// arg는 int형만 보낼 수 있어서 obj로 보내야 한다.
//				// 여기서 result는 object형이 아니기 때문에 래퍼클래스(new ~~)로 감싸서 보내야 한다.
//				rtnMsg.obj=new Double(result);
			}
			
			m_MainHandler.sendMessage(rtnMsg);
			
		}
		
	};*/
}