package com.revature.charityappcategoryms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.charityappcategoryms.dto.CategoryDTO;
import com.revature.charityappcategoryms.dto.Message;
import com.revature.charityappcategoryms.exception.ServiceException;
import com.revature.charityappcategoryms.exception.ValidatorException;
import com.revature.charityappcategoryms.model.Category;
import com.revature.charityappcategoryms.service.CategoryService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

 /**
 * This method is used to add a new category
 * takes object as parameters
 * returns the success or failure message
 * @throws ValidatorException on input error
 * 
 */
	@PostMapping()
	@ApiOperation(value = "categoryAPI")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Category added", response = String.class),
			@ApiResponse(code = 400, message = "Invalid Category", response = Category.class) })

	public ResponseEntity<?> addCategory(@RequestBody CategoryDTO categoryDTO) throws ValidatorException {

		try {
			categoryService.addCategory(categoryDTO);
			return new ResponseEntity<>(HttpStatus.CREATED);

		} catch (ServiceException e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * This method shows the list of different categories
	 * It does not takes input parameters
	 * returns the category list
	 * @throws ServiceException in case of empty data
	 * 
	 */
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<CategoryDTO> viewCategory() throws ServiceException {
		List<CategoryDTO> viewResponse = categoryService.listCategory();
		return viewResponse;
		
	}
	
	
	/**
	 * This method shows only active category details
	 * @return list this returns active categories
	 * @throws ServiceException on input error
	 */
	@GetMapping("/active")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Category> viewActiveCategory() throws ServiceException {
		List<Category> viewResponse = categoryService.listActiveCategory();
		return viewResponse;

	}
	
	
	/**
	 * This method delete category data
	 * @param categoryId is the parameter to deleteByCategory
	 * @return response success or failure
	 * @throws ServiceException on input error
	 */
	@DeleteMapping("{id}")
	@ApiOperation(value = "categoryAPI")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Category deleted", response = String.class),
			@ApiResponse(code = 400, message = "Invalid Category", response = Category.class) })
	
	public ResponseEntity<?> deleteByCategory(@PathVariable("id") Integer categoryId) throws ServiceException {

		try {
			categoryService.deleteCategory(categoryId);
			return new ResponseEntity<>(HttpStatus.CREATED);

		} catch (ServiceException e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}

	}
	

	/**
	 * This method search a category 
	 * @param categoryId is the parameter to view method
	 * @return response success or failure
	 * @throws ServiceException on input error
	 */
	@GetMapping("{id}")
	@ApiOperation(value = "categoryAPI")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Category detail", response = String.class),
			@ApiResponse(code = 400, message = "Invalid Category", response = Category.class) })
	
	public ResponseEntity<?> view(@PathVariable("id") Integer categoryId) throws ServiceException {

		
		try {
			Category category = categoryService.viewCategory(categoryId);
			return new ResponseEntity<>(category, HttpStatus.OK);

		} catch (ServiceException e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}

	}
}
