package gui.panel;

import gui.listener.ToolBarListener;
import util.CenterPanel;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    // 必须放在最前面才能生效，静态初始化块能够保证优先执行
    static {
        GUIUtil.useLNF();
    }

    public static MainPanel instance = new MainPanel(); // 单例模式

    public JToolBar tb = new JToolBar(); // 工具栏

    public JButton bSpend = new JButton(); // 消费一览
    public JButton bRecord = new JButton(); // 记账
    public JButton bCategory = new JButton(); // 分类
    public JButton bReport = new JButton(); // 报表
    public JButton bConfig = new JButton(); // 设置
    public JButton bBackup = new JButton(); // 备份
    public JButton bRecover = new JButton(); // 恢复

    public CenterPanel workingPanel; // 显示不同功能的面板

    private MainPanel() {

        // 设置图标的图片、文字和提示
        GUIUtil.setImageIcon(bSpend, "home.png", "消费一览");
        GUIUtil.setImageIcon(bRecord, "record.png", "记账");
        GUIUtil.setImageIcon(bCategory, "category2.png", "消费分类");
        GUIUtil.setImageIcon(bReport, "report.png", "月消费报表");
        GUIUtil.setImageIcon(bConfig, "config.png", "设置");
        GUIUtil.setImageIcon(bBackup, "backup.png", "备份");
        GUIUtil.setImageIcon(bRecover, "restore.png", "恢复");

        // 按钮添加到工具栏
        tb.add(bSpend);
        tb.add(bRecord);
        tb.add(bCategory);
        tb.add(bReport);
        tb.add(bConfig);
        tb.add(bBackup);
        tb.add(bRecover);
        tb.setFloatable(false); // 禁止工具栏拖动

        workingPanel = new CenterPanel(0.8);
        setLayout(new BorderLayout());
        add(tb, BorderLayout.NORTH);
        add(workingPanel, BorderLayout.CENTER);
        addListener();
    }

    private void addListener() {
        ToolBarListener listener = new ToolBarListener();

        bSpend.addActionListener(listener);
        bRecord.addActionListener(listener);
        bCategory.addActionListener(listener);
        bReport.addActionListener(listener);
        bConfig.addActionListener(listener);
        bBackup.addActionListener(listener);
        bRecover.addActionListener(listener);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(MainPanel.instance, 1);
    }
}
