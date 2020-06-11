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

    // 最大值
    public static int max(double[] sampleValues) {
        int max = 0;

        for (double v : sampleValues) {
            if (v > max) {
                max = (int) v;
            }
        }

        return max;
    }

    // 标签文本
    private static String[] sampleLabels(List<Record> rs) {
        String[] sampleLabels = new String[rs.size()];

        for (int i = 0; i < sampleLabels.length; i++) {
            if (0 == i % 5) {
                sampleLabels[i] = String.valueOf(i + 1 + "日");
            }
        }

        return sampleLabels;
    }

    // 柱状图
    public static Image getImage(List<Record> rs, int width, int height) {
        double[] sampleValues = sampleValues(rs); // 根据消费记录得到的样本数据
        String[] sampleLabels = sampleLabels(rs); // 根据消费记录得到的下面显示的文字
        int max = max(sampleValues); // 样本中的最大值
        Color[] sampleColors = new Color[] {ColorUtil.blueColor}; // 数据颜色
        BarChart chart = new BarChart(); // 柱状图

        chart.setSampleCount(sampleValues.length); // 设置样本个数
        chart.setSampleValues(0, sampleValues); // 设置样本数据
        chart.setSampleLabels(sampleLabels); // 设置文字
        chart.setSampleColors(sampleColors); // 设置样本颜色
        chart.setRange(0, max * 1.2); // 设置取值范围
        chart.setValueLinesOn(true); // 显示背景横线
        chart.setSampleLabelsOn(true); // 显示文字
        chart.setSampleLabelStyle(Chart.BELOW); // 把文字显示在下方

        chart.setFont("rangeLabelFont", new Font("Arial", Font.BOLD, 12)); // 设置样本字体
        chart.setLegendOn(true); // 显示图例说明
        chart.setLegendPosition(Chart.LEFT); // 把图例说明放在左侧
        chart.setLegendLabels(new String[] {"月消费报表"}); // 图例说明的文字
        chart.setFont("legendFont", new Font("Dialog", Font.BOLD, 13)); // 图例说明的字体
        chart.setFont("sampleLabelFont", new Font("Dialog", Font.BOLD, 13)); // 下方文字的字体
        chart.setChartBackground(ColorUtil.backgroundColor); // 图表中间背景颜色

        Image im = chart.getImage(width, height); // 把图表转换为Image类型
        return im;
    }

    // 每日消费值
    private static double[] sampleValues(List<Record> rs) {
        double[] sampleValues = new double[rs.size()];

        for (int i = 0; i < sampleValues.length; i++) {
            sampleValues[i] = rs.get(i).spend;
        }

        return sampleValues;
    }
}
