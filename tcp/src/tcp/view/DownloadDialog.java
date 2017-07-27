
package tcp.view;

import java.io.File;
import javax.swing.*;
import tcp.dao.DownloadFile;
import tcp.util.UiUtil;

public class DownloadDialog extends JDialog {
    private String port = null;
    private File file = null;
    public DownloadDialog() {
        this.setModal(true);
        initComponents();
        init();
    }
    public void init(){
        //设置窗体居中
        UiUtil.setFrameCenter(this);
    }
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        SelBut = new javax.swing.JButton();
        SelText = new javax.swing.JTextField();
        OKBut = new javax.swing.JButton();
        jProgressBar2 = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        portLabel = new javax.swing.JLabel();
        portText = new javax.swing.JTextField();
        SerStaLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        SelBut.setText("选择存储位置");
        SelBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelButActionPerformed(evt);
            }
        });

        SelText.setEnabled(false);

        OKBut.setText("确定");
        OKBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKButActionPerformed(evt);
            }
        });

        jProgressBar2.setStringPainted(true);

        jLabel2.setText("进度");

        portLabel.setText("侦听端口");

        portText.setText("8888");

        SerStaLabel.setForeground(new java.awt.Color(255, 51, 0));
        SerStaLabel.setText("服务器未开启");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(OKBut)
                        .addGap(92, 92, 92))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(SelBut)
                            .addComponent(jLabel2)
                            .addComponent(portLabel))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jProgressBar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(SelText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(92, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(portText, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SerStaLabel)
                                .addGap(33, 33, 33))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portLabel)
                    .addComponent(portText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SerStaLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SelBut)
                    .addComponent(SelText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(OKBut)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addGap(22, 22, 22))
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
    //下载按钮事件
    private void OKButActionPerformed(java.awt.event.ActionEvent evt) {
        String regexPort = "\\d{1,5}";
        port = portText.getText().trim();
        if(!port.matches(regexPort)){
            JOptionPane.showMessageDialog(this, "端口不合法!");
            portText.requestFocus(true);
            return;
        }
        if(file == null){
            JOptionPane.showMessageDialog(this, "请选择存储位置");
            return;
        }
        //执行接收线程,每个socket都要新开一个线程,否则若是在此线程执行socket会造成此线程阻塞,界面无法操作
        new DownloadFile(Integer.parseInt(port),file,this).start(); 
        this.OKBut.setEnabled(false);
        
        
    }
    //选择文件夹按钮事件
    private void SelButActionPerformed(java.awt.event.ActionEvent evt) {
    
                this.setVisible(false);
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                jfc.showDialog(new JLabel(),"选择");
                jfc.setFocusCycleRoot(true);
                file = jfc.getSelectedFile();
                if(file != null)
                    SelText.setText(file.getAbsolutePath());
                this.setVisible(true);
    }
    public javax.swing.JButton OKBut;
    private javax.swing.JButton SelBut;
    private javax.swing.JTextField SelText;
    public javax.swing.JLabel SerStaLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JLabel portLabel;
    private javax.swing.JTextField portText;

}
