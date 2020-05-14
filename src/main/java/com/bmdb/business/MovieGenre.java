package com.bmdb.business;

import javax.persistence.*;

@Entity
public class MovieGenre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "MovieID")
	private Movie movie;
	@ManyToOne
	@JoinColumn(name = "GenreID")
	private Genre genre;
	public MovieGenre() {
		super();
	}

	public MovieGenre(int id, Genre genre, Movie movie) {
		super();
		this.id = id;
		this.genre = genre;
		this.movie = movie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	@Override
	public String toString() {
		return "MovieGenre [id=" + id + ", genre=" + genre + ", movie=" + movie + "]";
	}
}