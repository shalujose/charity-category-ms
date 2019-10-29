package com.revature.charityappcategoryms.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.charityappcategoryms.dto.MessageConstant;
import com.revature.charityappcategoryms.exception.ValidatorException;
import com.revature.charityappcategoryms.model.Category;
import com.revature.charityappcategoryms.service.AdminService;

@Component
public class CategoryValidator {
	
	public void categoryValidation(Category category) throws ValidatorException {
       
        String categoryName=category.getCategoryName();
        int creatorId = category.getCreatedBy(); 
        
        if (categoryName == null){
            throw new ValidatorException(MessageConstant.CATEGORY_NAME_VALIDATOR);
        }
        if (creatorId == 0) {
            throw new ValidatorException(MessageConstant.CATEGORY_CREATORID);
        }
        
        validateUser(creatorId);
        
    }
    
    @Autowired
    private AdminService adminservice;
    
    public void validateUser(Integer userId) throws ValidatorException {
        
        Boolean validateId = adminservice.validateId(userId);
        if ( validateId == null || !validateId) {
            throw new ValidatorException(MessageConstant.CATEGORY_CREATORID);
        }
    }
	

}
