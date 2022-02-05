package com.byrc.user.managment.mappers;

/**
 * 
 * @ClassName: Mapper
 * @author Breymer Robles
 * @since: 2022-02-05
 * @version 1.0
 * @param <I>
 * @param <O>
 */
public interface Mapper<I, O> {
	I mapToDomainModel(O object);

	public O mapToEntity(I object);
}
