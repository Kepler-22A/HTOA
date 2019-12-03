package com.kepler.vo;

import javax.persistence.*;

@Table(name = "post")
@Entity
public class PostVo {//岗位设置
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    private String postName;//岗位名称
    private String remark;//备注
    private int depId;//关联部门表主键

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    @Override
    public String toString() {
        return "PostVo{" +
                "postId=" + postId +
                ", postName='" + postName + '\'' +
                ", remark='" + remark + '\'' +
                ", depId=" + depId +
                '}';
    }
}
