package com.capgemini.mappers;

import org.springframework.stereotype.Component;

import com.capgemini.domain.PositionEntity;
import com.capgemini.types.PositionTO;

/**
 * Maps position objects
 * 
 * @author TKRYSTKO
 *
 */
@Component
public class PositionMapper {
	
	/**
	 * Maps single Entity on TO
	 * 
	 * @param positionEntity
	 * @return mapped position transfer object
	 */
	public PositionTO onTO(PositionEntity positionEntity){
		
		if (positionEntity == null)
			return null;
		
		return new PositionTO(positionEntity.getId(),positionEntity.getTitle());
	}
	
	/**
	 * Maps single TO on Entity
	 * 
	 * @param positionTO
	 * @return mapped position entity
	 */
	public PositionEntity onEntity(PositionTO positionTO){
		
		if (positionTO == null)
			return null;
		
		PositionEntity mapped = new PositionEntity(positionTO.getTitle());
		mapped.setId(positionTO.getId());
		
		return mapped;
	}
	
}
