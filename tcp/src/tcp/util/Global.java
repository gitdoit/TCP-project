package tcp.util;

public class Global {
	//用来更新界面进度条,默认发送与接收不能同时进行.所以发送功能与接收功能共用一个全局变量.要在完成或者异常时将此变量置0
	//由于更新进度条是一个单独的线程所以要涉及到多线程之间的通信问题,这是此变量 的由来
	public static int rate = 0;

}
