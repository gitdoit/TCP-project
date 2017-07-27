/*
本类是接收数据并写入文件的实体,由于socket操作造成线程阻塞所以必须用线程处理
*/
package tcp.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import tcp.util.Global;
import tcp.util.NumberTool;
import tcp.view.DownloadDialog;

public class DownloadFile extends Thread{
    private int port = 0;
    private File file = null;
    DownloadDialog dialog = null;
    public DownloadFile(int port,File file,DownloadDialog dialog){
        this.port = port;
        this.file = file;
        this.dialog = dialog;
    }
    @Override
    public void run(){
        
        try{
            downloadFile();
            JOptionPane.showMessageDialog(dialog, "文件下载成功!");
            
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(dialog, e.getMessage());
        }
    }
    
    //此方法会从指定端口接收数据写入本地文件
    public  void downloadFile()throws IOException{
        //用来指定本地文件
        File newFile = null;
        //接收文件名
        String fileName = null;
        
        //文件长度,当前接收长度
        long fileSize = 0;
        String fileSizes = null;
        long currentSize = 0;
        int len = 0;
        Integer rate = new Integer(0);
        byte[] data = new byte[1024];
        //开启服务器
        ServerSocket ss = new ServerSocket(port);
        dialog.SerStaLabel.setText("服务器已开启");
        Socket s = ss.accept();
        dialog.SerStaLabel.setText("正在传送数据!");
        BufferedOutputStream fileWriter = null;
        try{
        	//建立通道
            BufferedInputStream input = new BufferedInputStream(s.getInputStream());
            //先接收文件名,再根据选定文件夹和传过来的文件名创建文件,
            len = input.read(data);
            fileName = new String(data,0,len);
            newFile = new File(file,fileName);
            if(!newFile.createNewFile()) {
            	throw new IOException("无法新建文件!");
            }
            fileWriter = new BufferedOutputStream(new FileOutputStream(newFile));
            //再接收文件长度
            byte[] size = new byte[8];
            len = input.read(size);
            fileSize =NumberTool.bytesToLong(size);
            //开启新线程,更新进度条
            ProgressBar.setBar(dialog.jProgressBar2);
            //向文件内写数据,这里用内存流中转一下并作异步处理,传输速度可能会增加
            while((len = input.read(data)) != -1){
                currentSize += len;
                Global.rate = (int)((1.0 * currentSize/fileSize) * 100);
                fileWriter.write(data, 0, len);
                fileWriter.flush();
            }
        }
        catch(IOException e){
            
            throw e;
        }
        finally{
        	Global.rate = 0;
        	if(fileWriter != null)
        		fileWriter.close();
        	if(s != null)
        		s.close();
        	if(ss != null)
        		ss.close();
            dialog.SerStaLabel.setText("服务器已关闭");
            dialog.OKBut.setEnabled(true);
        }
        
    }
}
