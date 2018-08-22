package com.capgemini.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Builder;
import lombok.NonNull;

@Entity
@Table(name = "ACTORS")
@Builder
public class ActorEntity {
	
	@Version 
	public int version;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@Column(name="first_name", nullable = false, length = 50)
	private String firstName;
	
	@NonNull
	@Column(name="last_name", nullable = false, length = 50)
	private String lastName;
	
	@NonNull
	@Column(name="birth_date",nullable = false)
	private Date birthDate;
	
	@NonNull
	@Column(nullable = false)
	private String country;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private StudioEntity studio;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "cast")
	private List<MovieEntity> movies;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public StudioEntity getStudio() {
		return studio;
	}

	public void setStudio(StudioEntity studio) {
		this.studio = studio;
	}

	public List<MovieEntity> getMovies() {
		return movies;
	}

	public void setMovies(List<MovieEntity> movies) {
		this.movies = movies;
	}
	
}
