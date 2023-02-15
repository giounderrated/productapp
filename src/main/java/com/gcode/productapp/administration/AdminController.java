package com.gcode.productapp.administration;

import com.gcode.productapp.api.JSend;
import com.gcode.productapp.api.Success;
import com.gcode.productapp.users.domain.UserNotAllDetails;
import com.gcode.productapp.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AdminController {
	private static final String ADMIN_USERS_PATH = "/admin/users";

	@Autowired
	private UserService userService;

	@GetMapping(ADMIN_USERS_PATH)
	public final JSend<List<UserNotAllDetails>> getAllUsers(){
		List<UserNotAllDetails> users = userService.getAllUsers();
		return Success.<List<UserNotAllDetails>>builder().data(users).build();
	}

}
