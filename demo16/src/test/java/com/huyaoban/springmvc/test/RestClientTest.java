package com.huyaoban.springmvc.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.huyaoban.springmvc.model.Spittle;

public class RestClientTest {
	@Test
	public void testRestClient() {
		RestTemplate rest = new RestTemplate();
		int id = 3;
		Spittle spittle = rest.getForObject("http://localhost:8080/springmvc-demo/spittles/v3/{id}", Spittle.class, id);
		Assert.assertNotNull(spittle);
		System.out.println(spittle);
	}

	@Test
	public void testRestClient1() {
		RestTemplate rest = new RestTemplate();
		int id = 2;
		ResponseEntity<Spittle> response = rest.getForEntity("http://localhost:8080/springmvc-demo/spittles/v3/{id}",
				Spittle.class, id);
		Assert.assertNotNull(response.getBody());
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
	}

	// 没有实现PUT操作，所以先忽略
	@Ignore
	public void testRestClient2() {
		RestTemplate rest = new RestTemplate();
		int id = 2;
		Spittle spittle = rest.getForObject("http://localhost:8080/springmvc-demo/spittles/v3/{id}", Spittle.class, id);
		Assert.assertNotNull(spittle);
		System.out.println(spittle);

		spittle.setFirstName("xfdfewewr");
		spittle.setUsername("fdstewrew");

		rest.put("http://localhost:8080/springmvc-demo/spittles/v3/{id}", spittle, spittle.getId());
	}

	@Test
	public void testRestClient3() {
		RestTemplate rest = new RestTemplate();
		int id = 2;
		Spittle spittle = rest.getForObject("http://localhost:8080/springmvc-demo/spittles/v3/{id}", Spittle.class, id);
		Assert.assertNotNull(spittle);
		System.out.println(spittle);

		spittle.setId(4);
		spittle.setFirstName("xfdfewewr");
		spittle.setUsername("fdstewrew");

		rest.postForObject("http://localhost:8080/springmvc-demo/spittles", spittle, Spittle.class);
	}
}
