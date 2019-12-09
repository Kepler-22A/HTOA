package com.kepler.vo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Student")//学生基本信息表
public class StudentVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Studid;//学生主键
    private String password;//登入密码1
    private String stuname;//学生姓名1
    private String middleschool;//毕业学校1
    private String sex;//性别1
    private String age;//年龄1
    private Date birthday;//出生年月1
    private String phone;//学生电话1
    private String addr;//家庭地址1
    private int clazz;//班级外键id
    private int huor;//宿舍外键id
    private Date entertime;//入学时间1
    private String introduretech;//介绍老师1
    private int stat;//学生状态
    private String nation;//民族1
    private String natives;//籍贯1
    private int residence;//户口性质1            1.农业  2.非农
    private String cardid;//身份证号1
    private String professional;//就读专业1
    private int prolevel;//专业类别1             1.中技  2.高技  3.3+2
    private int studytype;//学习类别1
    private String parents;//家长姓名1
    private String parentsphone;//家长电话1
    private String intrphone;//老师电话1
    private String audition;//面试人1
    private String auditionoption;//面试人意见1
    private int isvocational;//是否中专1          1.否   2.是
    private String vocationalsch;//中专学校1
    private int vocationalflag;//中专学籍1    1.已退 2.保留
    private int enrollno;//省录取号
    private Float qkMoney;//欠款金额
    private Float score;//录取成绩1
    private String dibao;//是否低保
    private String sourceType;//生源类型
    private String guarantee;//担保人
    private String soldier;//是否当兵
    private String registration;//报名号
    private String zhuxiao;//是否住校1
    private String remark;//备注1
    private String tuixue;//退学
    private String xiuxue;//休学
    private String stuno;//学号
    private String computer;//是否送电脑1
    private String collar;//是否领用1
    private String grants;//助学金1


    @Override
    public String toString() {
        return "StudentVo{" +
                "Studid=" + Studid +
                ", password='" + password + '\'' +
                ", stuname='" + stuname + '\'' +
                ", middleschool='" + middleschool + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", addr='" + addr + '\'' +
                ", clazz=" + clazz +
                ", huor=" + huor +
                ", entertime=" + entertime +
                ", introduretech='" + introduretech + '\'' +
                ", stat=" + stat +
                ", nation='" + nation + '\'' +
                ", natives='" + natives + '\'' +
                ", residence=" + residence +
                ", cardid='" + cardid + '\'' +
                ", professional='" + professional + '\'' +
                ", prolevel=" + prolevel +
                ", studytype=" + studytype +
                ", parents='" + parents + '\'' +
                ", parentsphone='" + parentsphone + '\'' +
                ", intrphone='" + intrphone + '\'' +
                ", audition='" + audition + '\'' +
                ", auditionoption='" + auditionoption + '\'' +
                ", isvocational=" + isvocational +
                ", vocationalsch='" + vocationalsch + '\'' +
                ", vocationalflag=" + vocationalflag +
                ", enrollno=" + enrollno +
                ", qkMoney=" + qkMoney +
                ", score=" + score +
                ", dibao='" + dibao + '\'' +
                ", sourceType='" + sourceType + '\'' +
                ", guarantee='" + guarantee + '\'' +
                ", soldier='" + soldier + '\'' +
                ", registration='" + registration + '\'' +
                ", zhuxiao='" + zhuxiao + '\'' +
                ", remark='" + remark + '\'' +
                ", tuixue='" + tuixue + '\'' +
                ", xiuxue='" + xiuxue + '\'' +
                ", stuno='" + stuno + '\'' +
                ", computer='" + computer + '\'' +
                ", collar='" + collar + '\'' +
                ", grants='" + grants + '\'' +
                '}';
    }

    public int getStudid() {
        return Studid;
    }

    public void setStudid(int studid) {
        Studid = studid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getMiddleschool() {
        return middleschool;
    }

    public void setMiddleschool(String middleschool) {
        this.middleschool = middleschool;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getClazz() {
        return clazz;
    }

    public void setClazz(int clazz) {
        this.clazz = clazz;
    }

    public int getHuor() {
        return huor;
    }

    public void setHuor(int huor) {
        this.huor = huor;
    }

    public Date getEntertime() {
        return entertime;
    }

    public void setEntertime(Date entertime) {
        this.entertime = entertime;
    }

    public String getIntroduretech() {
        return introduretech;
    }

    public void setIntroduretech(String introduretech) {
        this.introduretech = introduretech;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNatives() {
        return natives;
    }

    public void setNatives(String natives) {
        this.natives = natives;
    }

    public int getResidence() {
        return residence;
    }

    public void setResidence(int residence) {
        this.residence = residence;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public int getProlevel() {
        return prolevel;
    }

    public void setProlevel(int prolevel) {
        this.prolevel = prolevel;
    }

    public int getStudytype() {
        return studytype;
    }

    public void setStudytype(int studytype) {
        this.studytype = studytype;
    }

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }

    public String getParentsphone() {
        return parentsphone;
    }

    public void setParentsphone(String parentsphone) {
        this.parentsphone = parentsphone;
    }

    public String getIntrphone() {
        return intrphone;
    }

    public void setIntrphone(String intrphone) {
        this.intrphone = intrphone;
    }

    public String getAudition() {
        return audition;
    }

    public void setAudition(String audition) {
        this.audition = audition;
    }

    public String getAuditionoption() {
        return auditionoption;
    }

    public void setAuditionoption(String auditionoption) {
        this.auditionoption = auditionoption;
    }

    public int getIsvocational() {
        return isvocational;
    }

    public void setIsvocational(int isvocational) {
        this.isvocational = isvocational;
    }

    public String getVocationalsch() {
        return vocationalsch;
    }

    public void setVocationalsch(String vocationalsch) {
        this.vocationalsch = vocationalsch;
    }

    public int getVocationalflag() {
        return vocationalflag;
    }

    public void setVocationalflag(int vocationalflag) {
        this.vocationalflag = vocationalflag;
    }

    public int getEnrollno() {
        return enrollno;
    }

    public void setEnrollno(int enrollno) {
        this.enrollno = enrollno;
    }

    public Float getQkMoney() {
        return qkMoney;
    }

    public void setQkMoney(Float qkMoney) {
        this.qkMoney = qkMoney;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getDibao() {
        return dibao;
    }

    public void setDibao(String dibao) {
        this.dibao = dibao;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    public String getSoldier() {
        return soldier;
    }

    public void setSoldier(String soldier) {
        this.soldier = soldier;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getZhuxiao() {
        return zhuxiao;
    }

    public void setZhuxiao(String zhuxiao) {
        this.zhuxiao = zhuxiao;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTuixue() {
        return tuixue;
    }

    public void setTuixue(String tuixue) {
        this.tuixue = tuixue;
    }

    public String getXiuxue() {
        return xiuxue;
    }

    public void setXiuxue(String xiuxue) {
        this.xiuxue = xiuxue;
    }

    public String getStuno() {
        return stuno;
    }

    public void setStuno(String stuno) {
        this.stuno = stuno;
    }

    public String getComputer() {
        return computer;
    }

    public void setComputer(String computer) {
        this.computer = computer;
    }

    public String getCollar() {
        return collar;
    }

    public void setCollar(String collar) {
        this.collar = collar;
    }

    public String getGrants() {
        return grants;
    }

    public void setGrants(String grants) {
        this.grants = grants;
    }
}
