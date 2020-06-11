package service;

import dao.RecordDAO;
import entity.Record;
import page.SpendPage;
import util.DateUtil;

import java.util.List;

public class SpendService {

    // 返回SpendPage 用于界面显示
    public SpendPage getSpendPage() {
        RecordDAO dao = new RecordDAO();

        List<Record> thisMonthRecords = dao.listThisMonth(); // 本月数据
        List<Record> todayRecords = dao.listToday(); // 当天数据
        int thisMonthTotalDay = DateUtil.thisMonthTotalDay(); // 本月总天数

        int monthSpend = 0;
        int todaySpend = 0;
        int avgSpendPerDay = 0;
        int monthAvailable = 0;
        int dayAvgAvailable = 0;
        int monthLeftDay = 0;
        int usagePercentage = 0;

        int monthBudget = new ConfigService().getIntBudget();  // 本月预算

        // 统计本月消费
        for (Record record : thisMonthRecords) {
            monthSpend += record.getSpend();
        }

        // 统计今日消费
        for (Record record : todayRecords) {
            todaySpend += record.getSpend();
        }

        avgSpendPerDay = monthSpend / thisMonthTotalDay; // 日均消费
        monthAvailable = monthBudget - monthSpend; // 本月剩余预算
        monthLeftDay = DateUtil.thisMonthLeftDay(); // 本月剩余天数
        dayAvgAvailable = monthAvailable / monthLeftDay; // 剩余日均预算
        usagePercentage = monthSpend * 100 / monthBudget; // 使用比例

        return new SpendPage(monthSpend, todaySpend, avgSpendPerDay, monthAvailable, dayAvgAvailable, monthLeftDay, usagePercentage);
    }
}
