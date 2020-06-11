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

    // ��ѯ�����е�Category��Ȼ�����ÿ�ַ��࣬�ٰѷ����Ӧ�����Ѽ�¼��������������������ڶ�Ӧ����ʵ����recordNumer�ϡ�
    // ����ٸ���recordNumer���е�����������Ƶ�ʸߵķ������ǰ�档
    public List<Category> list() {
        List<Category> cs = categoryDAO.list();

        for (Category c : cs) {
            List<Record> rs = recordDAO.list(c.id);
            c.recordNumber = rs.size();
        }

        Collections.sort(cs, (c1, c2) -> c2.recordNumber - c1.recordNumber);

        return cs;
    }

    // ����һ�ַ���
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
