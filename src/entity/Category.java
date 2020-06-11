package entity;

public class Category {
    public int id;
    public String name;
    public int recordNumber; // 消费记录数，这个属性并不会出现在数据库中，它是根据这种分类在消费记录表Record中有多少条对应信息，临时查出来的。

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }

    public String toString() {
        return name;
    }
}
