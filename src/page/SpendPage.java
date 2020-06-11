package page;

public class SpendPage {
    public String monthSpend; // ��������
    public String todaySpend; // ��������
    public String avgSpendPerDay; // �վ�����
    public String monthAvailable; // ����ʣ��Ԥ��
    public String dayAvgAvailable; // �վ�����Ԥ��
    public String monthLeftDay; // ������ĩ
    public int usagePercentage; // ʹ�ñ���
    public boolean isOverSpend = false; // �Ƿ�֧

    public SpendPage(int monthSpend, int todaySpend, int avgSpendPerDay, int monthAvailable, int dayAvgAvailable, int monthLeftDay,
                     int usagePercentage) {
        this.monthSpend = "��" + monthSpend;
        this.todaySpend = "��" + todaySpend;
        this.avgSpendPerDay = "��" + avgSpendPerDay;

        if (monthAvailable < 0) {
            isOverSpend = true;
        }

        if (! isOverSpend) {
            this.monthAvailable = "��" + monthAvailable;
            this.dayAvgAvailable = "��" + dayAvgAvailable;
        } else {
            this.monthAvailable = "��֧" + (0 - monthAvailable);
            this.dayAvgAvailable = "��0";
        }

        this.monthLeftDay = monthLeftDay + "��";
        this.usagePercentage = usagePercentage;
    }
}
