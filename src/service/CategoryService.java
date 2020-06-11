package service;

import dao.CategoryDAO;
import dao.RecordDAO;
import entity.Category;
import entity.Record;

import java.util.List;
import java.util.Collections;

public class CategoryService {

    CategoryDAO categoryDAO = new CategoryDAO();
    RecordDAO recordDAO = new RecordDAO();

    // 查询出所有的Category，然后根据每种分类，再把分类对应的消费记录总数查出来，并且设置在对应分类实例的recordNumer上。
    // 最后再根据recordNumer进行倒排序，让消费频率高的分类放在前面。
    public List<Category> list() {
        List<Category> cs = categoryDAO.list();

        for (Category c : cs) {
            List<Record> rs = recordDAO.list(c.id);
            c.recordNumber = rs.size();
        }

        Collections.sort(cs, (c1, c2) -> c2.recordNumber - c1.recordNumber);

        return cs;
    }

    // 增加一种分类
    public void add(String name) {
        Category c = new Category();
        c.setName(name);
        categoryDAO.add(c);
    }

    public void update(int id, String name) {
        Category c = new Category();
        c.setName(name);
        c.setId(id);
        categoryDAO.update(c);
    }

    public void delete(int id) {
        categoryDAO.detele(id);
    }
}
