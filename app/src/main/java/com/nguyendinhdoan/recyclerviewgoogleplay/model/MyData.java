package com.nguyendinhdoan.recyclerviewgoogleplay.model;

import java.util.List;

public class MyData {
    private List<ListItem> listItem;
    private String headerTitle;

    public List<ListItem> getListItem() {
        return listItem;
    }

    public void setListItem(List<ListItem> listItem) {
        this.listItem = listItem;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }
}
