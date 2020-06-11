package gui.listener;

import entity.Category;
import gui.panel.CategoryPanel;
import jdk.nashorn.internal.scripts.JO;
import service.CategoryService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        CategoryPanel p = CategoryPanel.instance;
        JButton b = (JButton) e.getSource();

        if (b == p.bAdd) {
            String name = JOptionPane.showInputDialog(null);

            if (0 == name.length()) {
                JOptionPane.showMessageDialog(p, "�������Ʋ���Ϊ��");
                return;
            }

            new CategoryService().add(name);
        }

        if (b == p.bEdit) {
            Category c = p.getSelectedCategory();
            int id = c.id;
            String name = JOptionPane.showInputDialog("�޸ķ�������", c.name);

            if (0 == name.length()) {
                JOptionPane.showMessageDialog(p, "�������Ʋ���Ϊ��");
                return;
            }

            new CategoryService().update(id, name);
        }

        if (b == p.bDelete) {
            Category c = p.getSelectedCategory();

            if (0 != c.recordNumber) {
                JOptionPane.showMessageDialog(p, "�����������Ѽ�¼������ɾ��");
                return;
            }

            if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "ȷ��Ҫɾ����")) {
                return;
            }

            int id = c.id;
            new CategoryService().delete(id);
        }

        p.updateData();
    }
}
