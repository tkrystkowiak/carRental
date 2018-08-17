package com.capgemini.mappers;

import com.capgemini.domain.PositionEntity;
import com.capgemini.types.PositionTO;

public class PositionMapper {
	
	public static PositionTO onTO(PositionEntity positionEntity){
		
		if (positionEntity == null)
			return null;
		
		return new PositionTO(positionEntity.getId(),positionEntity.getTitle());
	}
	
	public static PositionEntity onEntity(PositionTO positionTO){
		
		if (positionTO == null)
			return null;
		
		PositionEntity mapped = new PositionEntity(positionTO.getTitle());
		mapped.setId(positionTO.getId());
		
		return mapped;
	}
	
}
