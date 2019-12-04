package com.kepler.vo;

import javax.persistence.*;
import java.util.Date;

@Table(name = "ChatRecord")
@Entity
public class ChatRecordVo { //谈心记录
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ChatID;
	private int sayFace; //学生表外键
	private int teacher; //员工表外键
	private String addr; //地址
	private String sayContent; //谈心内容
	private Date chatDate; //时间

	public int getChatID() {
		return ChatID;
	}

	public void setChatID(int chatID) {
		ChatID = chatID;
	}

	public int getSayFace() {
		return sayFace;
	}

	public void setSayFace(int sayFace) {
		this.sayFace = sayFace;
	}

	public int getTeacher() {
		return teacher;
	}

	public void setTeacher(int teacher) {
		this.teacher = teacher;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getSayContent() {
		return sayContent;
	}

	public void setSayContent(String sayContent) {
		this.sayContent = sayContent;
	}

	public Date getChatDate() {
		return chatDate;
	}

	public void setChatDate(Date chatDate) {
		this.chatDate = chatDate;
	}

	@Override
	public String toString() {
		return "ChatRecordVo{" +
				"ChatID=" + ChatID +
				", sayFace=" + sayFace +
				", teacher=" + teacher +
				", addr='" + addr + '\'' +
				", sayContent='" + sayContent + '\'' +
				", chatDate=" + chatDate +
				'}';
	}
}
