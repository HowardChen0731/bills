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

        // 判断是否有分类信息，没有就增加分类。
        if (0 == p.cbModel.categories.size()) {
            JOptionPane.showMessageDialog(p, "暂无消费分类，请先添加分类。");
            MainPanel.instance.workingPanel.show(CategoryPanel.instance);
            return;
        }

        // 输入金额不能为0
        if (!GUIUtil.checkZero(p.tfSpend, "输入金额")) {
            return;
        }

        // 添加消费记录
        int spend = Integer.parseInt(p.tfSpend.getText());
        Category c = p.getSelectedCategory();
        String comment = p.tfComment.getText();
        Date date = p.datepick.getDate();
        new RecordService().add(spend, c, comment, date);
        JOptionPane.showMessageDialog(p, "添加成功");

        MainPanel.instance.workingPanel.show(SpendPanel.instance); // 添加成功后切换到消费一览
        p.updateData();
    }
}
