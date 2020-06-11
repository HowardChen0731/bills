package service;

import dao.RecordDAO;
import entity.Record;
import page.SpendPage;
import util.DateUtil;

import java.util.List;

public class SpendService {

    // ����SpendPage ���ڽ�����ʾ
    public SpendPage getSpendPage() {
        RecordDAO dao = new RecordDAO();

        List<Record> thisMonthRecords = dao.listThisMonth(); // ��������
        List<Record> todayRecords = dao.listToday(); // ��������
        int thisMonthTotalDay = DateUtil.thisMonthTotalDay(); // ����������

        int monthSpend = 0;
        int todaySpend = 0;
        int avgSpendPerDay = 0;
        int monthAvailable = 0;
        int dayAvgAvailable = 0;
        int monthLeftDay = 0;
        int usagePercentage = 0;

        int monthBudget = new ConfigService().getIntBudget();  // ����Ԥ��

        // ͳ�Ʊ�������
        for (Record record : thisMonthRecords) {
            monthSpend += record.getSpend();
        }

        // ͳ�ƽ�������
        for (Record record : todayRecords) {
            todaySpend += record.getSpend();
        }

        avgSpendPerDay = monthSpend / thisMonthTotalDay; // �վ�����
        monthAvailable = monthBudget - monthSpend; // ����ʣ��Ԥ��
        monthLeftDay = DateUtil.thisMonthLeftDay(); // ����ʣ������
        dayAvgAvailable = monthAvailable / monthLeftDay; // ʣ���վ�Ԥ��
        usagePercentage = monthSpend * 100 / monthBudget; // ʹ�ñ���

        return new SpendPage(monthSpend, todaySpend, avgSpendPerDay, monthAvailable, dayAvgAvailable, monthLeftDay, usagePercentage);
    }
}
