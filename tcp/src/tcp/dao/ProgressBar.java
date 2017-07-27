package tcp.dao;
/*
 * 本类用来执行进度条的更新操作
 */
import javax.swing.JProgressBar;
import tcp.util.Global;

public class ProgressBar extends Thread {
	
	private JProgressBar jpb = null;
	
	private ProgressBar(JProgressBar jpb) {
		this.jpb = jpb;
	}
	@Override
	public void run() {
		try {
			while(Global.rate != 100) {
				jpb.setValue(Global.rate);
				Thread.currentThread().sleep(200);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			Global.rate = 0;
		}
	}
	//此方法会生成本类的一个Thread并开启.
	public static void setBar(JProgressBar jpb) {
		new ProgressBar(jpb).start();
	}
}
