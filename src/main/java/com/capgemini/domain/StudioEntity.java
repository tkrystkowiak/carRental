package com.capgemini.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Builder;
import lombok.NonNull;

@Entity
@Table(name = "STUDIOS")
@Builder
public class StudioEntity {
	
	@Version 
	public int version;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@Column(nullable = false)
	private String name;
	
	@NonNull
	@Column(nullable = false)
	private String country;
	
	@NonNull
	@OneToMany(mappedBy = "studio")
	private List<MovieEntity> movies;
	
	@NonNull
	@OneToMany(mappedBy = "studio")
	private List<ActorEntity> actors;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<MovieEntity> getMovies() {
		return movies;
	}

	public void setMovies(List<MovieEntity> movies) {
		this.movies = movies;
	}

	public List<ActorEntity> getActors() {
		return actors;
	}

	public void setActors(List<ActorEntity> actors) {
		this.actors = actors;
	}
	
	
}
