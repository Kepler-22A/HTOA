package com.kepler.vo;

import javax.persistence.*;

@Table(name = "testaaa")
@Entity
public class TestVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(nullable = false,length = 9999999)
    private String name;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestVo{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                '}';
    }
}
