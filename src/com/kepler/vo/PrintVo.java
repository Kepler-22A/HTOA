package com.kepler.vo;

import javax.persistence.*;

@Table(name = "PrintTable")
@Entity
public class PrintVo {//打印数据表，有框打印，无框打印
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int printId;
    private int state;//状态  1:ON打开 2：OFF关闭

    public int getPrintId() {
        return printId;
    }

    public void setPrintId(int printId) {
        this.printId = printId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "PrintVo{" +
                "printId=" + printId +
                ", state=" + state +
                '}';
    }
}
