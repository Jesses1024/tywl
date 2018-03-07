package com.puxintech.tywl.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.puxintech.tywl.model.sys.User;
import com.puxintech.tywl.service.sys.UserService;
import com.puxintech.tywl.util.Page;
import com.puxintech.tywl.util.PageRequest;

@RestController
@RequestMapping("/sys/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/:username")
	public User getUser(@PathVariable("username") String username) {
		return userService.findOne(username);
	}

	@GetMapping
	public Page<User> listUser(@RequestParam(required = false) String filter, PageRequest page) {
		return userService.findByPage(filter, page);
	}
}
