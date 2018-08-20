package com.capgemini.mappers;

import org.springframework.stereotype.Component;

import com.capgemini.domain.PositionEntity;
import com.capgemini.types.PositionTO;

@Component
public class PositionMapper {
	
	public PositionTO onTO(PositionEntity positionEntity){
		
		if (positionEntity == null)
			return null;
		
		return new PositionTO(positionEntity.getId(),positionEntity.getTitle());
	}
	
	public PositionEntity onEntity(PositionTO positionTO){
		
		if (positionTO == null)
			return null;
		
		PositionEntity mapped = new PositionEntity(positionTO.getTitle());
		mapped.setId(positionTO.getId());
		
		return mapped;
	}
	
}
