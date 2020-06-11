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

        // �ж�MySQL��װ·���Ƿ�����
        // ���û���þ���ת��ConfigPanel
        if (0 == mysqlPath.length()) {
            JOptionPane.showMessageDialog(p, "����ǰ��������mysql��·��");
            MainPanel.instance.workingPanel.show(ConfigPanel.instance);
            ConfigPanel.instance.tfMysqlPath.grabFocus();
            return;
        }

        // ���ļ�ѡ������ָ��Ҫ������ļ�
        // �ļ���Ĭ������Ϊbills.sql
        // �Ժ�׺��.sql�����ļ�
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
            // ���������ļ�û����.sql��β���Զ�����.sql��
            System.out.println(file);
            if (!file.getName().toLowerCase().endsWith(".sql")) {
                file = new File(file.getParent(), file.getName() + ".sql");
            }
            System.out.println(file);

            // ����MysqlUtil���б���
            try {
                MysqlUtil.backup(mysqlPath, file.getAbsolutePath());
                JOptionPane.showMessageDialog(p, "���ݳɹ����ļ�λ�ڣ�\r\n" + file.getAbsolutePath());
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(p, "����ʧ��\r\n������\r\n" + e1.getMessage());
            }
        }
    }
}
