package com.kepler.vo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "empVo")
public class EmpVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  String empId ;
    private String empName ;
    private int  depId ;
    private char sex ;
    private Date Birthday ;//出生日期
    private String Cardno ; //身份证号
    private String nation ;//籍贯
    private String Phone ;//手机号码
    private String QQcode ;//QQ号码
    private String Weixin ;//微信号码
    private String Email ;//邮箱地址
    private String married ;//婚姻状况（已婚，单身，离异，丧偶）
    private String University ;//毕业学校
    private String Major ;//专业
    private String Education ;//学历
    private String Address ;//家庭地址
    private String remark ;//说明
    private String Bank ;//银行
    private String accountName ;//账户名称
    private String bankNumber ;//银行账号
    private String alipay ;//支付宝账号(唯一）
    private Date hireDay ;//入职日期
    private Date fireDay ;//离职日期
    private String password ;//登录密码
    private int status ;//状态
    private int postId ;//职务id
    private String postName ;//职务名称


    @Override
    public String toString() {
        return "EmpVo{" +
                "empId='" + empId + '\'' +
                ", empName='" + empName + '\'' +
                ", depId=" + depId +
                ", sex=" + sex +
                ", Birthday=" + Birthday +
                ", Cardno='" + Cardno + '\'' +
                ", nation='" + nation + '\'' +
                ", Phone='" + Phone + '\'' +
                ", QQcode='" + QQcode + '\'' +
                ", Weixin='" + Weixin + '\'' +
                ", Email='" + Email + '\'' +
                ", married='" + married + '\'' +
                ", University='" + University + '\'' +
                ", Major='" + Major + '\'' +
                ", Education='" + Education + '\'' +
                ", Address='" + Address + '\'' +
                ", remark='" + remark + '\'' +
                ", Bank='" + Bank + '\'' +
                ", accountName='" + accountName + '\'' +
                ", bankNumber='" + bankNumber + '\'' +
                ", alipay='" + alipay + '\'' +
                ", hireDay=" + hireDay +
                ", fireDay=" + fireDay +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", postId=" + postId +
                ", postName='" + postName + '\'' +
                '}';
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    public String getCardno() {
        return Cardno;
    }

    public Date getHireDay() {
        return hireDay;
    }

    public void setHireDay(Date hireDay) {
        this.hireDay = hireDay;
    }

    public Date getFireDay() {
        return fireDay;
    }

    public void setFireDay(Date fireDay) {
        this.fireDay = fireDay;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setCardno(String cardno) {
        Cardno = cardno;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getQQcode() {
        return QQcode;
    }

    public void setQQcode(String QQcode) {
        this.QQcode = QQcode;
    }

    public String getWeixin() {
        return Weixin;
    }

    public void setWeixin(String weixin) {
        Weixin = weixin;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMarried() {
        return married;
    }

    public void setMarried(String married) {
        this.married = married;
    }

    public String getUniversity() {
        return University;
    }

    public void setUniversity(String university) {
        University = university;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String major) {
        Major = major;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        Education = education;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBank() {
        return Bank;
    }

    public void setBank(String bank) {
        Bank = bank;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }
}
