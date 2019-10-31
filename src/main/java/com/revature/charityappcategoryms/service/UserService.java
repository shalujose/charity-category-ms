package com.revature.charityappcategoryms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.revature.charityappcategoryms.dto.UserDto;

@Service
public class UserService {

	@Autowired
    RestTemplate restTemplate;
    
    public UserDto getUserId(final Integer userId) {
    	System.out.println("User Id:" + userId);
    	
        String apiUrl="https://userapp-v1.herokuapp.com";
        UserDto result = null;
		try {
			ResponseEntity <UserDto> postForEntity = restTemplate.getForEntity(apiUrl + "/admin/" + userId,UserDto.class);
			result = postForEntity.getBody();
		} catch (RestClientException e) {
			e.printStackTrace();
		}
        return result;
        
    }
}
