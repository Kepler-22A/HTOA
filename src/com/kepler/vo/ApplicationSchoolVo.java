package com.kepler.vo;

import javax.persistence.*;

@Table(name = "ApplicationSchool")
@Entity
public class ApplicationSchoolVo {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private   int appId;
        private String schoolName;  //学校名称
        private String remark;                //说明

        @Override
        public String toString() {
                return "ApplicationSchoolVo{" +
                        "appId=" + appId +
                        ", schoolName='" + schoolName + '\'' +
                        ", remark='" + remark + '\'' +
                        '}';
        }

        public int getAppId() {
                return appId;
        }

        public void setAppId(int appId) {
                this.appId = appId;
        }

        public String getSchoolName() {
                return schoolName;
        }

        public void setSchoolName(String schoolName) {
                this.schoolName = schoolName;
        }

        public String getRemark() {
                return remark;
        }

        public void setRemark(String remark) {
                this.remark = remark;
        }
}
