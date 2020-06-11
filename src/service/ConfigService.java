package service;

import dao.ConfigDAO;
import entity.Config;

public class ConfigService {
    public static final String budget = "budget";
    public static final String mysqlPath = "mysqlPath";
    public static final String default_budget = "500";
    static ConfigDAO dao = new ConfigDAO();

    static {
        init();
    }

    // ��ʼ��
    public static void init() {
        init(budget, default_budget);
        init(mysqlPath, "");
    }

    // ����keyȥ���ң���������ڣ���ʹ��value��ֵ����һ�����ݡ�
    private static void init(String key, String value) {
        Config config = dao.getByKey(key);

        if (config == null) {
            Config newConfig = new Config();
            newConfig.setKey(key);
            newConfig.setValue(value);
            dao.add(newConfig);
        }
    }

    // ���ݼ���ȡֵ
    public String get(String key) {
        Config config = dao.getByKey(key);
        return config.getValue();
    }

    // ���¼���Ӧ��ֵ
    public void update(String key, String value) {
        Config config = dao.getByKey(key);
        config.setValue(value);
        dao.update(config);
    }

    // ��ȡԤ��
    public int getIntBudget() {
        return Integer.parseInt(get(budget));
    }
}
