package com.huyaoban.springmvc.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

import com.huyaoban.springmvc.exception.Error;
import com.huyaoban.springmvc.exception.SpittleNotFoundException;
import com.huyaoban.springmvc.model.Spittle;
import com.huyaoban.springmvc.repository.SpittleRepository;



//RestController注解会为控制器类的每个方法应用消息转换器，就不用每个方法都写ResponseBody注解了，但是RequestBody注解还是需要写的
//@RestController
@Controller
@RequestMapping("/spittles")
public class SpittleController {
	private static final String MAX_LONG_AS_STRING = "9223372036854775807";
	@Autowired
	private SpittleRepository spittleRepository;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	// produces会检查请求头中的accept信息
	public List<Spittle> spittles(@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
			@RequestParam(value = "count", defaultValue = "20") int count) {
		return spittleRepository.findSpittles(max, count);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	// consumes会检查请求头中的content-type信息
	public Spittle saveSpittle(@RequestBody Spittle spittle) {
		return spittleRepository.save(spittle);
	}

	// 如果根据id找不到spittle，这个处理器方法会返回一个空的body，并且http状态码是200，200表示一切正常。
	// 这种情况是不对的，应该要告知客户端出现了问题，找不到对应id的spittle，状态码应该要返回404
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Spittle spittleById(@PathVariable long id) {
		return spittleRepository.findOne(id);
	}

	// ResponseEntity作为@ResponseBody的替代方案，ResponseEntity中可以包含响应相关的元数据，
	// 如头部信息和状态码以及要转成资源表述的对象。ResponseEntity包含了@ResponseBody注解的含义，故不需要ResponseBody注解
	// 这种情况下，如果找不到spittle,状态码就会返回404,客户端根据状态码可以判断找不到对象，但是消息体还是空的，我们可能会希望返回
	// 一个消息来描述错误信息，我们可以定义个Error对象
	@RequestMapping(value = "/v1/{id}", method = RequestMethod.GET)
	public ResponseEntity<Spittle> spittleByIdV1(@PathVariable long id) {
		Spittle spittle = spittleRepository.findOne(id);
		HttpStatus status = spittle != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<Spittle>(spittle, status);
	}

	// 找不到时，返回错误消息，而不是空的消息体
	// 但是如果这样实现的话，需要在每个处理器方法中处理异常情况，导致处理器方法逻辑复杂，且返回类型中使用了泛型，
	// 需要进一步进行统一异常处理。
	@RequestMapping(value = "/v2/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> spittleByIdV2(@PathVariable long id) {
		Spittle spittle = spittleRepository.findOne(id);
		if (spittle == null) {
			Error error = new Error(4, "Spittle [" + id + "] not found");
			return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Spittle>(spittle, HttpStatus.OK);
	}

	// 统一异常处理，这样控制器的请求处理方法可以只专注于正常的情况
	// 定义异常类SpittleNotFoundException，找不到时抛出异常
	@ResponseBody
	@RequestMapping(value = "/v3/{id}", method = RequestMethod.GET)
	public Spittle spittleByIdV3(@PathVariable long id) {
		Spittle spittle = spittleRepository.findOne(id);
		if (spittle == null) {
			throw new SpittleNotFoundException(id);
		}
		return spittle;
	}

	// @ExceptionHandler注解能够用到控制器方法中，用来处理特定的异常。这里，它表明如果在控制器的任意处理方法中抛出SpittleNotFoundExeption异常，
	// 就会调用spittleNotFound()方法来处理异常
	@ResponseBody
	@ExceptionHandler(SpittleNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error spittleNotFound(SpittleNotFoundException e) {
		long spittleId = e.getSpittleId();
		return new Error(4, "Spittle [" + spittleId + "] not found");
	}

	// 创建成功时，返回201状态码，并且通过header中的location指明新资源的地址，这是最佳实践，比返回状态码200要好。
	// 在该方法中，我们使用硬编码的方式来构建location头部信息，如果应用没有部署在本地，就不适用了，需要改用UriComponentsBuilder来实现
	@RequestMapping(value = "/v1/save-spittle", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Spittle> saveSpittleV1(@RequestBody Spittle spittle) {
		Spittle newSpittle = spittleRepository.save(spittle);

		HttpHeaders headers = new HttpHeaders();
		URI locationUri = URI.create("http://localhost:8080/springmvc-demo/spittles/" + newSpittle.getId());
		headers.setLocation(locationUri);

		ResponseEntity<Spittle> responseEntity = new ResponseEntity<Spittle>(newSpittle, headers, HttpStatus.CREATED);
		return responseEntity;
	}

	// 改用UriComponentsBuilder来构建location头部信息
	@RequestMapping(value = "/v2/save-spittle", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Spittle> saveSpittleV2(@RequestBody Spittle spittle, UriComponentsBuilder ucb) {
		Spittle newSpittle = spittleRepository.save(spittle);

		HttpHeaders headers = new HttpHeaders();
		URI locationUri = ucb.path("/spittles/").path(String.valueOf(newSpittle.getId())).build().toUri();
		headers.setLocation(locationUri);

		ResponseEntity<Spittle> responseEntity = new ResponseEntity<Spittle>(newSpittle, headers, HttpStatus.CREATED);
		return responseEntity;
	}

}
