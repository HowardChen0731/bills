package gui.listener;

import entity.Category;
import gui.panel.CategoryPanel;
import gui.panel.MainPanel;
import gui.panel.RecordPanel;
import gui.panel.SpendPanel;
import service.RecordService;
import util.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class RecordListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        RecordPanel p = RecordPanel.instance;

        // �ж��Ƿ��з�����Ϣ��û�о����ӷ��ࡣ
        if (0 == p.cbModel.categories.size()) {
            JOptionPane.showMessageDialog(p, "�������ѷ��࣬������ӷ��ࡣ");
            MainPanel.instance.workingPanel.show(CategoryPanel.instance);
            return;
        }

        // �������Ϊ0
        if (!GUIUtil.checkZero(p.tfSpend, "������")) {
            return;
        }

        // ������Ѽ�¼
        int spend = Integer.parseInt(p.tfSpend.getText());
        Category c = p.getSelectedCategory();
        String comment = p.tfComment.getText();
        Date date = p.datepick.getDate();
        new RecordService().add(spend, c, comment, date);
        JOptionPane.showMessageDialog(p, "��ӳɹ�");

        MainPanel.instance.workingPanel.show(SpendPanel.instance); // ��ӳɹ����л�������һ��
        p.updateData();
    }
}
