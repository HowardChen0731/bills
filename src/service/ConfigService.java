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

    // 初始化
    public static void init() {
        init(budget, default_budget);
        init(mysqlPath, "");
    }

    // 根据key去查找，如果不存在，就使用value的值插入一条数据。
    private static void init(String key, String value) {
        Config config = dao.getByKey(key);

        if (config == null) {
            Config newConfig = new Config();
            newConfig.setKey(key);
            newConfig.setValue(value);
            dao.add(newConfig);
        }
    }

    // 根据键获取值
    public String get(String key) {
        Config config = dao.getByKey(key);
        return config.getValue();
    }

    // 更新键对应的值
    public void update(String key, String value) {
        Config config = dao.getByKey(key);
        config.setValue(value);
        dao.update(config);
    }

    // 获取预算
    public int getIntBudget() {
        return Integer.parseInt(get(budget));
    }
}
