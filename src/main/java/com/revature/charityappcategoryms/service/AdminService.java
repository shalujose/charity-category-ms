package com.revature.charityappcategoryms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdminService {
	
	@Autowired
    RestTemplate restTemplate;
    public Boolean validateId(final Integer userId) {
        String apiUrl = "https://userapp-v1.herokuapp.com";
        ResponseEntity<Boolean> postForEntity = restTemplate.getForEntity(apiUrl + "/admin/"+  userId + "/validate",
                Boolean.class);
        Boolean result = postForEntity.getBody();
        System.out.println(result);
        return result;
    }

}
