package gui.listener;

import gui.panel.BackupPanel;
import gui.panel.ConfigPanel;
import gui.panel.MainPanel;
import service.ConfigService;
import util.MysqlUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class BackupListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        BackupPanel p = BackupPanel.instance;
        String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);

        // 判断MySQL安装路径是否配置
        // 如果没配置就跳转到ConfigPanel
        if (0 == mysqlPath.length()) {
            JOptionPane.showMessageDialog(p, "备份前请先配置mysql的路径");
            MainPanel.instance.workingPanel.show(ConfigPanel.instance);
            ConfigPanel.instance.tfMysqlPath.grabFocus();
            return;
        }

        // 打开文件选择器，指定要保存的文件
        // 文件名默认设置为bills.sql
        // 以后缀名.sql过滤文件
        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(new File("bills.sql"));
        fc.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".sql");
            }

            @Override
            public String getDescription() {
                return ".sql";
            }
        });

        int returnVal = fc.showSaveDialog(p);
        File file = fc.getSelectedFile();
        System.out.println(file);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            // 如果保存的文件没有以.sql结尾，自动加上.sql。
            System.out.println(file);
            if (!file.getName().toLowerCase().endsWith(".sql")) {
                file = new File(file.getParent(), file.getName() + ".sql");
            }
            System.out.println(file);

            // 调用MysqlUtil进行备份
            try {
                MysqlUtil.backup(mysqlPath, file.getAbsolutePath());
                JOptionPane.showMessageDialog(p, "备份成功，文件位于：\r\n" + file.getAbsolutePath());
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(p, "备份失败\r\n，错误：\r\n" + e1.getMessage());
            }
        }
    }
}
