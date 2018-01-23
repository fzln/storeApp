package entity;

import java.util.List;

public class DescriptionList {
    private List<Description> list;

    public DescriptionList() {
    }

    public DescriptionList(List<Description> list) {
        this.list = list;
    }

    public List<Description> getList() {
        return list;
    }

    public void setList(List<Description> list) {
        this.list = list;
    }
}
