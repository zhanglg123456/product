package com.fh.util;

import java.io.Serializable;

public class Page implements Serializable {


    private static final long serialVersionUID = -5454478680396744379L;
    private int draw;
    private int start;
    private int length;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
