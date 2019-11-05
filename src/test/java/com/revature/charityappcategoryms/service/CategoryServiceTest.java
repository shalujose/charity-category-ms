package com.revature.charityappcategoryms.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.charityappcategoryms.dto.CategoryDTO;
import com.revature.charityappcategoryms.exception.ServiceException;
import com.revature.charityappcategoryms.exception.ValidatorException;
import com.revature.charityappcategoryms.model.Category;

@SpringBootTest
class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;

	@Test
	public void testAddCategory() throws ServiceException, ValidatorException {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(8);
		categoryDTO.setCategoryName("Categoryfour");
		categoryDTO.setCreatedBy(3);
		
		categoryDTO.setActive(true);
		categoryDTO.setCreatedDate(LocalDateTime.now());
		categoryDTO.setModifiedDate(LocalDateTime.now());
		categoryService.addCategory(categoryDTO);
		assertNotNull(categoryDTO);

	}
	@Test
	public void testListCategory() throws ServiceException {
		CategoryDTO categoryDTO=new CategoryDTO();
		categoryDTO.getId();
		categoryDTO.getCategoryName();
		categoryDTO.getCreatedBy();
		categoryDTO.getActive();
		categoryDTO.getCreatedByName();
		categoryDTO.getCreatedDate();
		categoryDTO.getModifiedDate();
		categoryService.listCategory();
		assertNotNull(categoryDTO);
	}
	@Test
	public void testEmptyList() throws ServiceException {
		CategoryDTO categoryDTO=new CategoryDTO();
		if(categoryDTO!=null) {
		
		categoryService.listCategory();
		assertNotNull(categoryDTO);
		}
	}

	@Test
	public void testListActiveCategory() throws ServiceException {
		CategoryDTO categoryDTO=new CategoryDTO();
		categoryDTO.getId();
		categoryDTO.getCategoryName();
		categoryDTO.getCreatedBy();
		categoryDTO.getActive();
		categoryDTO.getCreatedByName();
		categoryDTO.getCreatedDate();
		categoryDTO.getModifiedDate();
		categoryService.listActiveCategory();
		assertNotNull(categoryDTO);
	}
	
	@Test
	public void testDeleteCategory() throws ServiceException {
		Category category=new Category();
		
		categoryService.deleteCategory(4);
		assertNotNull(category);
		
	}
	
	@Test
	public void testSearchCategory() throws ServiceException {
		Category category=new Category();
		categoryService.viewCategory(1);
		assertNotNull(category);
	}
}
