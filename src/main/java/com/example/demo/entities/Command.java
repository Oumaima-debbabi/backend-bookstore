package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Command {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( updatable = false)
    private Date creationDate = new Date();
    private boolean windedUp = false;
    @ManyToOne
    private User user;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public boolean isWindedUp() {
		return windedUp;
	}
	public void setWindedUp(boolean windedUp) {
		this.windedUp = windedUp;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
    

}
