package com.iwlabs.customer.mapper;

import java.util.List;

public interface GeneralMapper<Dto, Entity> {

	public Dto entityToDto(Entity entity);

	public Entity dtoToEntity(Dto dto);

	public void mapRequestedFieldsForUpdate(Entity entity, Dto dto);

}
