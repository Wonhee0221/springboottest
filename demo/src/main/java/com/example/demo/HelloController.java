package com.example.demo;

import java.util.HashMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController //json 객체를 json 형태로 바꿔준다
@RequestMapping(value = "")
// url이 /hello/~~ 으로된것들을 이 컨트롤러가 다 처리하게 해준다.
public class HelloController {
	//단축기 ctrl + shift + o

	@GetMapping("/getUserInfo")
	public HashMap<String, String> getUserInfo(){
		HashMap<String, String> map = new HashMap<String, String>();
		// 컬렉션클라스, 배열, Map, json, SortedList
		// 배열의 요소는 index 를 통해 읽고 쓰기가 가능하다.
		// Hashmap, Dictionay, json 은 동일한 구조이다.
		// key와 value 쌍으로 구성되는 데이터를 저장해서
		// 데이터를 읽고 쓸때 키값을 찾아서 읽고 쓰기한다.
		// ("name": "홍길동","phone": "010-4440-1223","address":"서울시 관악구")
		map.put("name", "홍길동");
		map.put("phone", "010-4440-1223");
		map.put("address", "서울시 관악구");
		
		return map;
	}
	//정보를 주고 받는 방식 - get 방식
	// /getUserInfo?user_id=test6&user_name=홍길동 //이게 기본적인 get방식.
	// 새로운 GET 방식이 나옴 : /getUserInfo/test/홍길동 => 이런식으로 바뀜. 둘다 get방식임.
	
	// POST방식 : Form 태그의 method="POST"으로 바꿔야 POST방식을 사용가능하다.
	// add1?x=5&y=7 => {x:5,y:7,result=12} //GET방식
	// add2/5/7 => {x:5,y:7,result=12}//GET방식
	// add3=> {x:5,y:7,result=12} //POST방식
	
	@GetMapping("/add1")
	public HashMap<String, Object> add1(HttpServletRequest request, @RequestParam("x") int x, @RequestParam("y") int y){
		HashMap<String, Object> map = new HashMap<String, Object>();
		// HttpServletRequest 객체에 담아온다.
		map.put("x", x);
		map.put("y", y);
		map.put("result", x+y);
		
		return map;
	
	};
	//새로운 get 방식
	//add2/5/7 이런식으로 보내야함.
	@GetMapping("/add2/{x}/{y}")
	public HashMap<String, Object> add2(HttpServletRequest request, @PathVariable("x") int x, @PathVariable("y") int y){
		HashMap<String, Object> map = new HashMap<String, Object>();
		// HttpServletRequest 객체에 담아온다.
		map.put("x", x);
		map.put("y", y);
		map.put("result", x+y);
		
		return map;
	
	};
	//POST방식
	@PostMapping("/add3")
	public HashMap<String, Object> add3(HttpServletRequest request, @RequestParam("x") int x, @RequestParam("y") int y){
		HashMap<String, Object> map = new HashMap<String, Object>();
		// HttpServletRequest 객체에 담아온다.
		map.put("x", x);
		map.put("y", y);
		map.put("result", x+y);
		
		return map;
	
	};
	@PostMapping("/add4")
	// @RequestBody - JSON으로 받아라 라는 뜻.
	public HashMap<String, Object> add4(@RequestBody HashMap<String, Object> map)
	{
		HashMap<String, Object> resultMap = new HashMap<String, Object>();		
		int x = Integer.parseInt(map.get("x").toString());
		int y = Integer.parseInt(map.get("y").toString());
		
		resultMap.put("x", x);
		resultMap.put("y", y);
		resultMap.put("result", x+y);
		
		return resultMap;
	
	};
	
	// @RequestBody - JSON으로 받아라 라는 뜻.
	// 데이터를 클라이언트가 JSON형태로 보낼 때 json데이터를 받아서 자바객체로 전환과정을 거친다.
	// Hashmap이나 DTO(Data transfer Object)클래스 로 만드는게 좋다.
	// DB테이블과 거의 1:1이다.
	// 3개의 테이블을 조인해서 필요한 필드만큼 만들수 있다.
	
	// name="홍길동"&age=12 ==> {name:"홍길동", "age":12}
	// Restful API - 데이터주고받을때 표준 xml 이나 json이다.
	// xml - 실제 데이터를 가져오는 Parsing 과정이 별도로 필요하다. 
	// xml은 점점 시장이 자리를 잃고있다. 
	@PostMapping("/weekpay")
	public HashMap<String, Object> weekpay(
			@RequestBody HashMap <String, Object> param)
	{
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String user_name = param.get("name").toString();
		int user_time = Integer.parseInt(param.get("time").toString());
		int time_pay = Integer.parseInt(param.get("pay").toString());
		
		int pay = user_time*time_pay;
//		resultMap.put("name", user_name);
//		resultMap.put("time", user_time);
//		resultMap.put("pay", time_pay);
//		resultMap.put("result",pay);
		
		resultMap.put("result", "OK");
		resultMap.put("msg", String.format("%s님의 주급은 %d입니다.",user_name,pay));
		return resultMap;
	
	};

}
