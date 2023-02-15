package com.gcode.productapp.users;

import com.gcode.productapp.api.JSend;
import com.gcode.productapp.api.Success;
import com.gcode.productapp.users.domain.User;
import com.gcode.productapp.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	private static final String USER_PATH_WITH_ID = "/users/{id}";

	@Autowired
	private UserService userService;

	@GetMapping(USER_PATH_WITH_ID)
	public final JSend<User> getUser(@PathVariable("id") final String id){
		long user_id= Long.parseLong(id);
		final User user = userService.getUserById(user_id);
		return Success.<User>builder().data(user).build();
	}
}
