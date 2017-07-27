
/*
本类是执行上传操作的实体
*/
package tcp.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import tcp.util.Global;
import tcp.util.NumberTool;
import tcp.view.UpLoadDialog;

public class UploadFile extends Thread{
	private String ip = null;
	private int port = 0;
	private File file = null;
	private UpLoadDialog dialog = null;
	public UploadFile(String ip,int port,File file,UpLoadDialog dialog) {
		this.ip = ip;
		this.port = port;
		this.file = file;
		this.dialog = dialog;
	}
	
	@Override
	public void run() {
		try {
			//执行上传方法
			uploadFile();
			//从上传方法中返回之后提示消息
			JOptionPane.showMessageDialog(dialog, "文件上传成功!");
		}
		catch(Exception e) {
			//若上传方法出异常则提示消息
			JOptionPane.showMessageDialog(dialog, e.getMessage());
		}
		finally {
			//无论哪种结果都要将按钮设置可点击
			dialog.ChooseFileBut.setEnabled(true);
			dialog.UpLoadBut.setEnabled(true);
		}
	}
	//上传方法,执行从本地文件读,向接收端写.
    public void uploadFile()throws IOException{
        Socket s = new Socket(ip,port);
        if(s.isConnected()) {
        	dialog.staLable.setText("已连接!");
        }
        //待发送文件长度
        long fileSize = file.length();
        //已发送字节
        long currentSize = 0;
        //建立管道
        BufferedOutputStream fileOut = new BufferedOutputStream(s.getOutputStream());
        //指定需要上传的文件,需要关闭
        BufferedInputStream fileIn = new BufferedInputStream(new FileInputStream(file));
        try{
             //先传送文件名
            byte[] name = file.getName().getBytes();
            fileOut.write(name,0,name.length);
            fileOut.flush();
            //再传文件长度
            byte[] size = NumberTool.longToBytes(fileSize);
            fileOut.write(size,0,size.length);
            fileOut.flush();
            //再传送文件
            byte[] data = new byte[1024];
            int len = 0; 
            //此方法会新开一个线程,每0.2秒更新一次进度条
            ProgressBar.setBar(dialog.jProgressBar1);
            //上传,边从文件读数据,边发送出去.如果这两个操作是异步的话应该会提高速度
            while((len = fileIn.read(data)) != -1){
                currentSize += len;
                Global.rate = (int)((1.0 * currentSize/fileSize) * 100);
                fileOut.write(data, 0, len);
                fileOut.flush();
            }
            //通知对方写入完毕
            s.shutdownOutput();
        }
        catch(IOException e){//异常抛出,无法在本层处理
            throw e;
        }
        finally{//关闭资源
        	Global.rate = 0;
        	if(fileIn != null)
        		fileIn.close();
        	if(s != null)
        		s.close();
         }
       
       
        
    }
}
