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

    public static RecordPanel instance = new RecordPanel(); // 单例化

    JLabel lSpend = new JLabel("花费（￥）");
    JLabel lCategory = new JLabel("分类");
    JLabel lComment = new JLabel("备注");
    JLabel lDate = new JLabel("日期");

    public JTextField tfSpend = new JTextField("0"); // 文本框
    public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
    public JComboBox<Category> cbCategory = new JComboBox<>(cbModel); // 下拉框
    public JTextField tfComment = new JTextField(); // 文本框
    public JXDatePicker datepick = new JXDatePicker(new Date()); // 日期控件

    JButton bSubmit = new JButton("记账"); // 按钮

    public RecordPanel() {
        // 按钮和标签上色
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

    // 获取当前选中的分类对象
    public Category getSelectedCategory() {
        return (Category) cbCategory.getSelectedItem();
    }

    // 更新下拉框中的分类信息，并且让各个输入框信息重置，以及让焦点停留在金额的输入框上。
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
