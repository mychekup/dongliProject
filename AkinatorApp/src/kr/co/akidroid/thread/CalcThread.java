package kr.co.akidroid.thread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

//핸들러 사용을 위한 스레드
public class CalcThread extends Thread{
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
				rtnMsg.obj = msg.obj;
				Log.i("백핸들러","쓰레드오냐?");
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
		
	};
}