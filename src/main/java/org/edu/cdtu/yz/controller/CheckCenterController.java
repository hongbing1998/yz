package org.edu.cdtu.yz.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@Api("客服接口")
@Controller
@RequestMapping("/checkcenter")
public class CheckCenterController {

	
	@ApiOperation(value="获取客服", notes="根据cid获取客服")
    @ApiImplicitParam(name = "cid", value = "客户id", required = true, dataType = "String")
	@ResponseBody
	@GetMapping("/getCenter")
	public Map<String,String>getCenter(String cid) {
		Map<String,String> map=new LinkedHashMap<String,String>();
		map.put("cid",cid);
		map.put("name","客服");
		return map;
	}
	
	@ApiOperation(value="获取客服", notes="根据cid获取客服")
    @ApiImplicitParam(name = "cid", value = "客户id", required = true, dataType = "String")
	@ResponseBody
	@PostMapping("/getCenter/{cid}")
	public Map<String,String> getCenter2(@PathVariable String cid) {
		Map<String,String> map=new LinkedHashMap<String,String>();
		map.put("cid",cid);
		map.put("name","客服");
		return map;
	}
}