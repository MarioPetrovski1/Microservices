package com.iwlabs.fraud.mapper;

public interface GeneralMapper<Dto, Entity> {

	public Dto entityToDto(Entity entity);

	public Entity dtoToEntity(Dto dto);

}
