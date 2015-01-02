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
	    //�� ���� ��Ű��
	    calc.setDaemon(true);
	    calc.start();
	    
	    
//	    textview.setText(setText1);
	    
	    btnSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // ������ ��������
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
				Toast.makeText(getBaseContext(), "�α��μ���", 3000).show();

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

				Log.i("�α��μ���","0");
				Intent intent = new Intent(getApplicationContext(), TabActivity.class);
				startActivity(intent);
				break;
			case 1:
				Toast.makeText(getApplicationContext(), "�α��ν���", 3000).show();
				Log.i("�α��ν���","1");
			}
			
		}
	};
	
	class loadJsp extends AsyncTask<Void,String,Void> {
        @Override
        protected Void doInBackground(Void... param) {
            // TODO Auto-generated method stub     
            try{
            	//���� �ʿ������ ��� 
	            //HttpClient client = new DefaultHttpClient();
	            
	            //���������ҋ� �̰ž�����?
	            HttpClient httpclient = SessionControl.getHttpclient();//����

	            // jsp �ּ�
	            String postURL = "http://192.168.10.13:8080/AkinatorApp/android/dataReceiver.jsp";
	            HttpPost post = new HttpPost(postURL);
	            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
	            
	            //�Ľ�?
	            params.add(new BasicNameValuePair("userId",userId.getText().toString()));
	            params.add(new BasicNameValuePair("pass",pass.getText().toString()));
	            
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
        

//�ڵ鷯 ����� ���� ������
class CalcThread extends Thread{
	private Handler m_MainHandler;
	
	// �����ڸ� ���Ͽ� ���� ����
	public CalcThread(Handler handler){
		m_MainHandler = handler;
	}
	
	@Override
	public void run() {
		// ������� ���� ������ֱ�
		Looper.prepare();
		Looper.loop();
	}
	
	// �޼��� ��ü�� ���� ���� �޴� �ڵ鷯
	// �̰��� ���ۿ� ���ؼ� �ڵ����� ȣ���� ������ ���ε�
	// ���ο��� �ڵ����� ���۰� ������ 
	// �ٸ� Ŭ���������� ���۸� ���� ����� �־���Ѵ�.
	// ���۸� run()�� �����.
	public Handler m_BackHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// �����忡�� ������� ����Ŭ������ �����ֱ�
			Message rtnMsg = Message.obtain();
			// what���� ������ ó������ ������ �ֱ�
			if(msg.what ==0){
//				//Log.d("CalcThread",String.valueOf(msg.arg1));
//				int result = msg.arg1 * msg.arg1;
				rtnMsg.what=0;
//				rtnMsg.arg1=result;
			}
			else if(msg.what ==1){
//				double result = Math.sqrt((double)msg.arg1);
				rtnMsg.what=1;
//				// arg�� int���� ���� �� �־ obj�� ������ �Ѵ�.
//				// ���⼭ result�� object���� �ƴϱ� ������ ����Ŭ����(new ~~)�� ���μ� ������ �Ѵ�.
//				rtnMsg.obj=new Double(result);
			}
			
			m_MainHandler.sendMessage(rtnMsg);
			
		}
		
	};*/
}