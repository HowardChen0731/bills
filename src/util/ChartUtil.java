package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.objectplanet.chart.BarChart;
import com.objectplanet.chart.Chart;
import entity.Record;

public class ChartUtil {

    // ���ֵ
    public static int max(double[] sampleValues) {
        int max = 0;

        for (double v : sampleValues) {
            if (v > max) {
                max = (int) v;
            }
        }

        return max;
    }

    // ��ǩ�ı�
    private static String[] sampleLabels(List<Record> rs) {
        String[] sampleLabels = new String[rs.size()];

        for (int i = 0; i < sampleLabels.length; i++) {
            if (0 == i % 5) {
                sampleLabels[i] = String.valueOf(i + 1 + "��");
            }
        }

        return sampleLabels;
    }

    // ��״ͼ
    public static Image getImage(List<Record> rs, int width, int height) {
        double[] sampleValues = sampleValues(rs); // �������Ѽ�¼�õ�����������
        String[] sampleLabels = sampleLabels(rs); // �������Ѽ�¼�õ���������ʾ������
        int max = max(sampleValues); // �����е����ֵ
        Color[] sampleColors = new Color[] {ColorUtil.blueColor}; // ������ɫ
        BarChart chart = new BarChart(); // ��״ͼ

        chart.setSampleCount(sampleValues.length); // ������������
        chart.setSampleValues(0, sampleValues); // ������������
        chart.setSampleLabels(sampleLabels); // ��������
        chart.setSampleColors(sampleColors); // ����������ɫ
        chart.setRange(0, max * 1.2); // ����ȡֵ��Χ
        chart.setValueLinesOn(true); // ��ʾ��������
        chart.setSampleLabelsOn(true); // ��ʾ����
        chart.setSampleLabelStyle(Chart.BELOW); // ��������ʾ���·�

        chart.setFont("rangeLabelFont", new Font("Arial", Font.BOLD, 12)); // ������������
        chart.setLegendOn(true); // ��ʾͼ��˵��
        chart.setLegendPosition(Chart.LEFT); // ��ͼ��˵���������
        chart.setLegendLabels(new String[] {"�����ѱ���"}); // ͼ��˵��������
        chart.setFont("legendFont", new Font("Dialog", Font.BOLD, 13)); // ͼ��˵��������
        chart.setFont("sampleLabelFont", new Font("Dialog", Font.BOLD, 13)); // �·����ֵ�����
        chart.setChartBackground(ColorUtil.backgroundColor); // ͼ���м䱳����ɫ

        Image im = chart.getImage(width, height); // ��ͼ��ת��ΪImage����
        return im;
    }

    // ÿ������ֵ
    private static double[] sampleValues(List<Record> rs) {
        double[] sampleValues = new double[rs.size()];

        for (int i = 0; i < sampleValues.length; i++) {
            sampleValues[i] = rs.get(i).spend;
        }

        return sampleValues;
    }
}
