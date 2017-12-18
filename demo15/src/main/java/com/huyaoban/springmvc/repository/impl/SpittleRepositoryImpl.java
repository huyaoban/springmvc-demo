package com.huyaoban.springmvc.repository.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.huyaoban.springmvc.model.Spittle;
import com.huyaoban.springmvc.repository.SpittleRepository;

@Service
public class SpittleRepositoryImpl implements SpittleRepository {
	private static Map<Long, Spittle> spittles;

	public SpittleRepositoryImpl() {
		spittles = new HashMap<Long, Spittle>();

		Spittle sp1 = new Spittle(1L, "jerry.hu", "test1", "jerry", "hu");
		Spittle sp2 = new Spittle(2L, "jerry.hu1", "test2", "jerry1", "hu");

		spittles.put(1L, sp1);
		spittles.put(2L, sp2);
	}

	@Override
	public List<Spittle> findSpittles(long max, int count) {
		return Arrays.asList(spittles.values().toArray(new Spittle[0]));
	}

	@Override
	public Spittle save(Spittle spittle) {
		spittles.put(spittle.getId(), spittle);
		return spittle;
	}

	@Override
	public Spittle findOne(long id) {
		return spittles.get(id);
	}

}
