package com.ispan.springbootdemo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="work_messages")
public class WorkMessages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="text", columnDefinition = "nvarchar(200)")
	private String text;
	
	@DateTimeFormat(pattern ="yyyy/MM/dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="added", columnDefinition = "datetime")
	private Date added;

	
	public WorkMessages() {
	}

	@PrePersist   // 再轉換到 Persist 狀態以前去做以下方法
	public void onCreate() {
		if(added == null) {
			added = new Date();
		}
	}		


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}

	@Override
	public String toString() {
		return "WorkMessages [id=" + id + ", text=" + text + ", added=" + added + "]";
	}
	
	
}