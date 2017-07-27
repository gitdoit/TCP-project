/*此类用来设置JDialog的各种属性
 */
package tcp.util;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class UiUtil {
    private UiUtil(){};
    
    public static void setFrameCenter(Window jf){
        Toolkit tk = Toolkit.getDefaultToolkit();

        //获取屏幕的宽和高
        Dimension d = tk.getScreenSize();
        double srceenWidth = d.getWidth();
        double srceenHeigth = d.getHeight();

        //获取窗体的宽和高
        int frameWidth = jf.getWidth();
        int frameHeight = jf.getHeight();

        //获取新的宽和高
        int width = (int) (srceenWidth - frameWidth) / 2;
        int height = (int) (srceenHeigth - frameHeight) / 2;

        //设置窗体坐标
        jf.setLocation(width, height);
    }
    
    public static void setFrameImage(JFrame jf) {
        //获取工具类对象
        //public static Toolkit getDefaultToolkit():获取默认工具包。 
        Toolkit tk = Toolkit.getDefaultToolkit();

        //根据路径获取图片 src\tcp\resource
        Image i = tk.getImage("src\\tcp\\resource\\user.jpg");

        //给窗体设置图片
        jf.setIconImage(i);
    }
    
        public static void setFrameImage(JFrame jf,String imageName) {
        //获取工具类对象
        //public static Toolkit getDefaultToolkit():获取默认工具包。 
        Toolkit tk = Toolkit.getDefaultToolkit();

        //根据路径获取图片
        Image i = tk.getImage(imageName);

        //给窗体设置图片
        jf.setIconImage(i);
    }
}
