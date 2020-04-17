package com.bmdb.business;

import javax.persistence.*;

@Entity
public class Credit {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String actorId;
	private String movieId;
	private String role;
	public Credit() {
		super();
	}
	public Credit(int id, String actorid, String movieid, String role) {
		super();
		this.id = id;
		this.actorId = actorid;
		this.movieId = movieid;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getActorid() {
		return actorId;
	}
	public void setActorid(String actorid) {
		this.actorId = actorid;
	}
	public String getMovieid() {
		return movieId;
	}
	public void setMovieid(String movieid) {
		this.movieId = movieid;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Credit [id=" + id + ", actorid=" + actorId + ", movieid=" + movieId + ", role=" + role + "]";
	}
	
}
