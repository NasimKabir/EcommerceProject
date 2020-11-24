package com.nasim.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nasim.dto.UserDto;
import com.nasim.exception.Response;
import com.nasim.service.UserService;


@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class UserController {
	@Autowired
	private UserService userService;
	
	 
	 @PutMapping("/{id}")
	    public Response productUpdated(@PathVariable("id") Long id,@Valid @RequestBody UserDto userDto,BindingResult result){
		
	                return userService.updateUser(id, userDto);
	       
	    }
	 
	
	    @GetMapping("/{id}")
	    public Response get(@PathVariable("id") Long id) {
	        return userService.getUser(id);
	    }

	    @GetMapping
	    public ResponseEntity<CollectionModel<UserDto>> getAll(@RequestParam(required = false, defaultValue = "0") Integer page,
               @RequestParam(required = false, defaultValue = "3") Integer size,
               @RequestParam(required = false) String[] sort,
               @RequestParam(required = false, defaultValue = "asc") String dir) {

	        @SuppressWarnings("unchecked")
			CollectionModel<UserDto> userList = userService.getAllUserList(page, size, sort, dir);
	        if(userList != null) {
	            return ResponseEntity.ok(userList);
	        }
	        return ResponseEntity.noContent().build();
	    }

}
