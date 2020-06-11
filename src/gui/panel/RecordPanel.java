package gui.panel;

import entity.Category;
import gui.listener.RecordListener;
import gui.model.CategoryComboBoxModel;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import javax.swing.border.Border;

import org.jdesktop.swingx.JXDatePicker;

import java.awt.*;
import java.util.Date;

public class RecordPanel extends WorkingPanel {

    static {
        GUIUtil.useLNF();
    }

    public static RecordPanel instance = new RecordPanel(); // ������

    JLabel lSpend = new JLabel("���ѣ�����");
    JLabel lCategory = new JLabel("����");
    JLabel lComment = new JLabel("��ע");
    JLabel lDate = new JLabel("����");

    public JTextField tfSpend = new JTextField("0"); // �ı���
    public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
    public JComboBox<Category> cbCategory = new JComboBox<>(cbModel); // ������
    public JTextField tfComment = new JTextField(); // �ı���
    public JXDatePicker datepick = new JXDatePicker(new Date()); // ���ڿؼ�

    JButton bSubmit = new JButton("����"); // ��ť

    public RecordPanel() {
        // ��ť�ͱ�ǩ��ɫ
        GUIUtil.setColor(ColorUtil.grayColor, lCategory, lComment, lDate, lSpend);
        GUIUtil.setColor(ColorUtil.blueColor, bSubmit);

        JPanel pInput = new JPanel();
        JPanel pSubmit = new JPanel();
        int gap = 40;
        pInput.setLayout(new GridLayout(4, 2, gap, gap));
        pInput.add(lSpend);
        pInput.add(tfSpend);
        pInput.add(lCategory);
        pInput.add(cbCategory);
        pInput.add(lComment);
        pInput.add(tfComment);
        pInput.add(lDate);
        pInput.add(datepick);
        pSubmit.add(bSubmit);

        this.setLayout(new BorderLayout());
        this.add(pInput, BorderLayout.NORTH);
        this.add(pSubmit, BorderLayout.CENTER);

        addListener();
    }

    // ��ȡ��ǰѡ�еķ������
    public Category getSelectedCategory() {
        return (Category) cbCategory.getSelectedItem();
    }

    // �����������еķ�����Ϣ�������ø����������Ϣ���ã��Լ��ý���ͣ���ڽ���������ϡ�
    @Override
    public void updateData() {
        cbModel.categories = new CategoryService().list();
        cbCategory.updateUI();
        resetInput();
        tfSpend.grabFocus();
    }

    public void resetInput() {
        tfSpend.setText("0");
        tfComment.setText("");

        if (0 != cbModel.categories.size()) {
            cbCategory.setSelectedIndex(0);
        }

        datepick.setDate(new Date());
    }

    @Override
    public void addListener() {
        RecordListener listener = new RecordListener();
        bSubmit.addActionListener(listener);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(RecordPanel.instance);
    }
}
