package gui.panel;

import page.SpendPage;
import service.SpendService;
import util.CircleProgressBar;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class SpendPanel extends WorkingPanel {

    // ����Ƥ��
    static {
        GUIUtil.useLNF();
    }

    public static SpendPanel instance = new SpendPanel(); // �������������������ȡ���

    // ����������Ϊpublic���ԣ������������ȡ���
    JLabel lMonthSpend = new JLabel("��������");
    JLabel lTodaySpend = new JLabel("��������");
    JLabel lAvgSpendPerDay = new JLabel("�վ�����");
    JLabel lMonthLeft = new JLabel("����ʣ��");
    JLabel lDayAvgAvailable = new JLabel("�վ�����");
    JLabel lMonthLeftDay = new JLabel("������ĩ");

    JLabel vMonthSpend = new JLabel("$2300");
    JLabel vTodaySpend = new JLabel("$25");
    JLabel vAvgSpendPerDay = new JLabel("$120");
    JLabel vMonthAvailable = new JLabel("$2084");
    JLabel vDayAvgAvailable = new JLabel("$389");
    JLabel vMonthLeftDay = new JLabel("15��");

    CircleProgressBar bar = new CircleProgressBar(); // ���ν�����

    private SpendPanel() {
        this.setLayout(new BorderLayout());
        bar.setBackgroundColor(ColorUtil.blueColor);

        // �����������ɫ����
        GUIUtil.setColor(ColorUtil.grayColor, lMonthSpend, lTodaySpend, lAvgSpendPerDay, lMonthLeft, lDayAvgAvailable, lMonthLeftDay,
                vAvgSpendPerDay, vMonthAvailable, vDayAvgAvailable, vMonthLeftDay);
        GUIUtil.setColor(ColorUtil.blueColor, vMonthSpend, vTodaySpend);
        vMonthSpend.setFont(new Font("΢���ź�", Font.BOLD, 23));
        vTodaySpend.setFont(new Font("΢���ź�", Font.BOLD, 23));

        this.add(center(), BorderLayout.CENTER);
        this.add(south(), BorderLayout.SOUTH);
    }

    // ���ֹ滮
    // center
    private JPanel center() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(west(), BorderLayout.WEST);
        p.add(center2(), BorderLayout.CENTER);
        return p;
    }

    private Component center2() {
        return bar;
    }

    private Component west() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4, 1));
        p.add(lMonthSpend);
        p.add(vMonthSpend);
        p.add(lTodaySpend);
        p.add(vTodaySpend);
        return p;
    }

    // south
    private JPanel south() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2, 4));
        p.add(lAvgSpendPerDay);
        p.add(lMonthLeft);
        p.add(lDayAvgAvailable);
        p.add(lMonthLeftDay);
        p.add(vAvgSpendPerDay);
        p.add(vMonthAvailable);
        p.add(vDayAvgAvailable);
        p.add(vMonthLeftDay);
        return p;
    }

    public static void main(String[] args) {

        GUIUtil.showPanel(SpendPanel.instance);
    }

    @Override
    public void updateData() {
        SpendPage spendPage = new SpendService().getSpendPage();

        // ��ҳ������ֵ���µ��������
        vMonthSpend.setText(spendPage.monthSpend);
        vTodaySpend.setText(spendPage.todaySpend);
        vAvgSpendPerDay.setText(spendPage.avgSpendPerDay);
        vMonthAvailable.setText(spendPage.monthAvailable);
        vDayAvgAvailable.setText(spendPage.dayAvgAvailable);
        vMonthLeftDay.setText(spendPage.monthLeftDay);

        bar.setProgress(spendPage.usagePercentage);

        // ��֧�����óɺ�ɫ
        if (spendPage.isOverSpend) {
            vMonthAvailable.setForeground(ColorUtil.warningColor);
            vMonthSpend.setForeground(ColorUtil.warningColor);
            vTodaySpend.setForeground(ColorUtil.warningColor);
        } else {
            vMonthAvailable.setForeground(ColorUtil.grayColor);
            vMonthSpend.setForeground(ColorUtil.grayColor);
            vTodaySpend.setForeground(ColorUtil.grayColor);
        }

        bar.setForegroundColor(ColorUtil.getByPercentage(spendPage.usagePercentage));
        addListener();
    }

    @Override
    public void addListener() {

    }
}
