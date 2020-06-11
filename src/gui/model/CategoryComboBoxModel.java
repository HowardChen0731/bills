package gui.model;

import entity.Category;
import service.CategoryService;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.List;

public class CategoryComboBoxModel implements ComboBoxModel<Category> {
    public List<Category> categories = new CategoryService().list(); // 分类信息
    public Category c; // 被选中分类

    // 构造方法
    public CategoryComboBoxModel() {
        if (!categories.isEmpty()) {
            c = categories.get(0);
        }
    }

    // 获取下拉框的大小
    public int getSize() {
        return categories.size();
    }

    // 获取指定位置的数据
    public Category getElementAt(int index) {
        return categories.get(index);
    }

    public void addListDataListener(ListDataListener l) {

    }

    public void removeListDataListener(ListDataListener l) {

    }

    // 选中某一项时调用此方法
    public void setSelectedItem(Object anItem) {
        c = (Category) anItem;
    }

    // 获取被选中的数据
    public Object getSelectedItem() {
        if (!categories.isEmpty()) {
            return c;
        }

        return null;
    }
}
