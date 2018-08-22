package com.capgemini.mappers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.domain.ActorEntity;
import com.capgemini.domain.StudioEntity;
import com.capgemini.types.ActorTO;

@Component
public class ActorMapper {
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	MovieMapper movieMapper;
	
	public ActorTO mapOnTO(ActorEntity mappedFrom){
		ActorTO mappedOn = ActorTO.builder()
				.id(mappedFrom.getId())
				.firstName(mappedFrom.getFirstName())
				.lastName(mappedFrom.getLastName())
				.birthDate(mappedFrom.getBirthDate())
				.country(mappedFrom.getCountry())
				.studio(mappedFrom.getStudio().getId())
				.movies(movieMapper.mapOnIds(mappedFrom.getMovies()))
				.build();
		return mappedOn;
	}
	
	public ActorEntity mapOnEntity(ActorTO mappedFrom){
		ActorEntity mappedOn = ActorEntity.builder()
				.id(mappedFrom.getId())
				.firstName(mappedFrom.getFirstName())
				.lastName(mappedFrom.getLastName())
				.birthDate(mappedFrom.getBirthDate())
				.country(mappedFrom.getCountry())
				.studio(em.getReference(StudioEntity.class, mappedFrom.getStudio()))
				.movies(movieMapper.mapOnEntities(mappedFrom.getMovies()))
				.build();
		return mappedOn;
	}
	
	public List<Long> mapOnIds(List<ActorEntity> entities){
		List<Long> ids = new ArrayList<Long>();
		entities.forEach(entity -> ids.add(entity.getId()));
		return ids;
	}
	
	public List<ActorEntity> mapOnEntities(List<Long> ids){
		List<ActorEntity> entities = new ArrayList<ActorEntity>();
		entities.forEach(id -> entities.add(em.getReference(ActorEntity.class, id)));
		return entities;
	}
}
