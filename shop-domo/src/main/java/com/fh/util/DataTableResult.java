package com.fh.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataTableResult<T> implements Serializable {
    private static final long serialVersionUID = -2819456992627407096L;

    private Integer draw;

    private Integer recordsTotal;
    private Integer recordsFiltered;

    private List<T> data = new ArrayList<>();

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
