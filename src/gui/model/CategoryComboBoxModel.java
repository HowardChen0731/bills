package gui.model;

import entity.Category;
import service.CategoryService;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.List;

public class CategoryComboBoxModel implements ComboBoxModel<Category> {
    public List<Category> categories = new CategoryService().list(); // ������Ϣ
    public Category c; // ��ѡ�з���

    // ���췽��
    public CategoryComboBoxModel() {
        if (!categories.isEmpty()) {
            c = categories.get(0);
        }
    }

    // ��ȡ������Ĵ�С
    public int getSize() {
        return categories.size();
    }

    // ��ȡָ��λ�õ�����
    public Category getElementAt(int index) {
        return categories.get(index);
    }

    public void addListDataListener(ListDataListener l) {

    }

    public void removeListDataListener(ListDataListener l) {

    }

    // ѡ��ĳһ��ʱ���ô˷���
    public void setSelectedItem(Object anItem) {
        c = (Category) anItem;
    }

    // ��ȡ��ѡ�е�����
    public Object getSelectedItem() {
        if (!categories.isEmpty()) {
            return c;
        }

        return null;
    }
}
