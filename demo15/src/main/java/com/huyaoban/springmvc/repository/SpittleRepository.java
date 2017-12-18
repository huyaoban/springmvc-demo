package com.huyaoban.springmvc.repository;

import java.util.List;

import com.huyaoban.springmvc.model.Spittle;

public interface SpittleRepository {
	public List<Spittle> findSpittles(long max, int count);

	public Spittle save(Spittle spittle);

	public Spittle findOne(long id);
}
