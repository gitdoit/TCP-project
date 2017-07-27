
package tcp.view;
import javax.swing.JFrame;
import javax.swing.UIManager;

import tcp.util.LocalMes;
import tcp.util.MyLookAndFeel;
import tcp.util.UiUtil;
public class MainFrame extends JFrame {

    public MainFrame() {
        initComponents();
        init();
    }
    public void init(){
        this.PortText.setText(LocalMes.getLocalIPAddress());
        UiUtil.setFrameCenter(this);
        UiUtil.setFrameImage(this);
    }
    //初始化窗体
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        UpBut = new javax.swing.JButton();
        DownBut = new javax.swing.JButton();
        PortText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        UpBut.setText("上传");
        UpBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpButActionPerformed(evt);
            }
        });

        DownBut.setText("接收");
        DownBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DownButActionPerformed(evt);
            }
        });

        PortText.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        PortText.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        jLabel1.setText("本机IP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(UpBut)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DownBut, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(PortText, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)))
                .addGap(59, 59, 59))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PortText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UpBut)
                    .addComponent(DownBut))
                .addGap(44, 44, 44))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //上传按钮执行方法,开启线程,创建Frame
    private void UpButActionPerformed(java.awt.event.ActionEvent evt) {
        new Thread(new Runnable(){
             public void run() {
               new UpLoadDialog().setVisible(true);
            }
        }).start();
        
    }
    //下载按钮执行方法,开启线程,创建Frame
    private void DownButActionPerformed(java.awt.event.ActionEvent evt) {
        new Thread(new Runnable(){
             public void run() {
               new DownloadDialog().setVisible(true);
            }
        }).start();
    }

    public static void main(String args[]) throws Exception{
    	//设置窗口主题
        UIManager.setLookAndFeel(MyLookAndFeel.JTATTOO_NOIRE);
        //生成主窗口
        new Thread(new Runnable(){
             public void run() {
              new MainFrame().setVisible(true); 
            }
        }).start();
        
    }

 
    private javax.swing.JButton DownBut;
    private javax.swing.JTextField PortText;
    private javax.swing.JButton UpBut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;

}
