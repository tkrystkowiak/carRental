package com.capgemini.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Builder;
import lombok.NonNull;

@Entity
@Table(name = "MOVIES")
@Builder
public class MovieEntity {
	
	@Version 
	public int version;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@Column(nullable = false)
	private String title;
	
	@NonNull
	@Column(nullable = false)
	private String genre;
	
	@NonNull
	@Column(nullable = false)
	private String type;
	
	@NonNull
	@Column(nullable = false)
	private Integer length;
	
	@NonNull
	@Column(nullable = false)
	private Date dateOfPremiere;
	
	@NonNull
	@Column(nullable = false)
	private String country;
	
	@NonNull
	@Column(nullable = false)
	private Boolean is3D;
	
	@NonNull
	@Column(nullable = false)
	private Integer budget;
	
	@NonNull
	@Column(nullable = false)
	private Integer totalRevenue;
	
	@NonNull
	@Column(nullable = false)
	private Integer firstWeekRevenue;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "movie_actor", joinColumns = { @JoinColumn(name = "movie_id") },inverseJoinColumns = { @JoinColumn(name = "actor_id") })
	private List<ActorEntity> cast;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "studio_id")
	private StudioEntity studio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Date getDateOfPremiere() {
		return dateOfPremiere;
	}

	public void setDateOfPremiere(Date dateOfPremiere) {
		this.dateOfPremiere = dateOfPremiere;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Boolean getIs3D() {
		return is3D;
	}

	public void setIs3D(Boolean is3d) {
		is3D = is3d;
	}

	public Integer getBudget() {
		return budget;
	}

	public void setBudget(Integer budget) {
		this.budget = budget;
	}

	public Integer getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(Integer totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public Integer getFirstWeekRevenue() {
		return firstWeekRevenue;
	}

	public void setFirstWeekRevenue(Integer firstWeekRevenue) {
		this.firstWeekRevenue = firstWeekRevenue;
	}

	public List<ActorEntity> getCast() {
		return cast;
	}

	public void setCast(List<ActorEntity> cast) {
		this.cast = cast;
	}

	public StudioEntity getStudio() {
		return studio;
	}

	public void setStudio(StudioEntity studio) {
		this.studio = studio;
	}
	
}
