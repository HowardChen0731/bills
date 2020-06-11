package gui.model;

import entity.Category;
import service.CategoryService;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class CategoryTableModel extends AbstractTableModel {

    String[] columnNames = new String[]{"��������", "���Ѵ���"};
    public List<Category> categories = new CategoryService().list(); // ʹ�ô�Service���ص�List��ΪTableModel������

    @Override
    public int getRowCount() {
        return categories.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    } // ��Ԫ���Ƿ����޸�

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Category c = categories.get(rowIndex);

        if (columnIndex == 0) {
            return c.name;
        }

        if (columnIndex == 1) {
            return c.recordNumber;
        }

        return null;
    }
}
