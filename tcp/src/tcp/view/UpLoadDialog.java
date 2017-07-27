
package tcp.view;
import javax.swing.*;
import java.io.*;

import tcp.dao.DownloadFile;
import tcp.dao.UploadFile;
import tcp.util.LocalMes;
import tcp.util.UiUtil;
public class UpLoadDialog extends javax.swing.JDialog {
    //本机端口,目标IP,目标端口
    private String localPort = null;
    private String localIP = null;
    private String tarIP = null;
    private String tarPort = null;
    private String filePath = null;
    private String fileName = null;
    private File file = null;

    //构造方法 
    public UpLoadDialog() {
        this.setModal(true);
        initComponents();
        init();
        
    }
    public void init(){
        //设置窗体居中
        UiUtil.setFrameCenter(this);
        //显示本机IP
        this.LocalIPText.setText(LocalMes.getLocalIPAddress());
    }

    //窗体初始化
    private void initComponents() {

        MainPanel = new javax.swing.JPanel();
        LocalIPText = new javax.swing.JTextField();
        LocalIPLabel = new javax.swing.JLabel();
        TarIPText = new javax.swing.JTextField();
        TarIPLabel = new javax.swing.JLabel();
        TarPortLabel = new javax.swing.JLabel();
        TarPortText = new javax.swing.JTextField();
        ChooseFileText = new javax.swing.JTextField();
        ChooseFileBut = new javax.swing.JButton();
        UpLoadBut = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        staLable = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);

        LocalIPText.setEnabled(false);
        
        LocalIPLabel.setText("本机IP");


        TarIPLabel.setText("目标IP");

        TarPortLabel.setText("目标端口");

        TarPortText.setText("8888");

        ChooseFileBut.setText("选择文件");
        ChooseFileBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChooseFileButActionPerformed(evt);
            }
        });

        UpLoadBut.setText("上传");
        UpLoadBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpLoadButActionPerformed(evt);
            }
        });

        jProgressBar1.setStringPainted(true);

        jLabel1.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("上传前请先确定对方服务已开启");

        staLable.setText("未连接");

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(LocalIPLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LocalIPText, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TarIPLabel)
                            .addComponent(TarPortLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TarPortText)
                            .addComponent(TarIPText, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ChooseFileBut)
                    .addComponent(staLable))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(UpLoadBut)
                                .addGap(142, 142, 142))
                            .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 113, Short.MAX_VALUE))
                    .addComponent(ChooseFileText))
                .addContainerGap())
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LocalIPText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LocalIPLabel))
                .addGap(105, 105, 105)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TarIPText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TarIPLabel))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TarPortLabel)
                    .addComponent(TarPortText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChooseFileText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChooseFileBut))
                .addGap(18, 18, 18)
                .addComponent(UpLoadBut)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(staLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    //文件选择按钮
    private void ChooseFileButActionPerformed(java.awt.event.ActionEvent evt) {
                this.setVisible(false);
                JFileChooser jfc = new JFileChooser();
                
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                jfc.showDialog(new JLabel(),"选择");
                jfc.setFocusCycleRoot(true);
                file = jfc.getSelectedFile();
                if(file != null)
                    ChooseFileText.setText(file.getAbsolutePath());
                this.setVisible(true);
    }
    //上传按钮
    private void UpLoadButActionPerformed(java.awt.event.ActionEvent evt) {
        //上传之前判断,并弹窗警告
        String regexIP = "[\\d]{1,3}\\.[\\d]{1,3}\\.[\\d]{1,3}\\.[\\d]{1,3}";
        String regexPort = "\\d{1,5}";
        tarIP = TarIPText.getText().trim();
        tarPort = TarPortText.getText().trim();
        if(!tarIP.matches(regexIP)){//判断IP
            JOptionPane.showMessageDialog(this, "目标IP不合法!");
            TarIPText.requestFocus(true);
            return;
        }
        if(!tarPort.matches(regexPort)){
            JOptionPane.showMessageDialog(this, "目标端口不合法!");
            TarPortText.requestFocus(true);
            return;
        }
        if(file == null){
            JOptionPane.showMessageDialog(this, "请选择文件!");
            return;
        }
        //先将按钮设置不可点击,再开启新线程执行上传
        ChooseFileBut.setEnabled(false);
        UpLoadBut.setEnabled(false);
        new UploadFile(tarIP,Integer.parseInt(tarPort),file,this).start();
           

        
        
    }


    
    public JButton ChooseFileBut;
    private JTextField ChooseFileText;
    private JLabel LocalIPLabel;
    private JTextField LocalIPText;
    private JPanel MainPanel;
    private JLabel TarIPLabel;
    private JTextField TarIPText;
    private JLabel TarPortLabel;
    private JTextField TarPortText;
    public JButton UpLoadBut;
    private JLabel jLabel1;
    public JProgressBar jProgressBar1;
    public JLabel staLable;
}
