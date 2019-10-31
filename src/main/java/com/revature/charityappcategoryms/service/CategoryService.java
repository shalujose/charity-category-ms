package com.revature.charityappcategoryms.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.charityappcategoryms.dto.UserDto;
import com.revature.charityappcategoryms.dto.CategoryDTO;
import com.revature.charityappcategoryms.dto.MessageConstant;
import com.revature.charityappcategoryms.exception.ServiceException;
import com.revature.charityappcategoryms.exception.ValidatorException;
import com.revature.charityappcategoryms.model.Category;
import com.revature.charityappcategoryms.repository.CategoryRepository;
import com.revature.charityappcategoryms.validator.CategoryValidator;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CategoryValidator categoryvalidator;
	@Autowired
	private UserService userservice;

	/**
	 * This method is used to add new categories 
	 * @param categoryDTO as the 
	 * takes createdBy parameter as integer 
	 * takes categoryName parameter as String 
	 * takes active parameter as boolean 
	 * takes createdDate and ModifiedDate parameter as LocalDateTime
	 * @throws ServiceException in case of invalid values
	 * @throws ValidatorException 
	 */
	@Transactional
	public void addCategory(CategoryDTO categoryDTO) throws ServiceException, ValidatorException {
		Category category = new Category();
		category.setCreatedBy(categoryDTO.getCreatedBy());
		category.setCategoryName(categoryDTO.getCategoryName());

		categoryvalidator.categoryValidation(category);
		category.setActive(true);
		category.setCreatedDate(LocalDateTime.now());
		category.setModifiedDate(LocalDateTime.now());
		try {
			categoryRepository.save(category);
		} catch (Exception e) {
			throw new ServiceException(MessageConstant.INVALID_CATEGORY);
		}


	}

	/**
	 * This method list all the categories
	 * @return list this returns category list
	 * @throws ServiceException 
	 */
	@Transactional
	public List<CategoryDTO> listCategory() throws ServiceException {
		List<Category> catelist= categoryRepository.findAll();
		List<CategoryDTO> listDto=new ArrayList<CategoryDTO>();
	       for (Category category : catelist) {
	    	   CategoryDTO dto = new CategoryDTO();
	           dto.setId(category.getId());
	           dto.setCategoryName(category.getCategoryName());
	           dto.setCreatedBy(category.getCreatedBy());
	           dto.setCreatedDate(category.getCreatedDate());
	           
	           UserDto user= userservice.getUserId(category.getCreatedBy());
	           if ( user != null) {
	              dto.setCreatedByname(user.getName());
	           }
	           listDto.add(dto);
	       }
	       if (listDto.isEmpty()) {
	           throw new ServiceException(MessageConstant.UNABLE_TO_LIST_NAME);
	       }
	       return listDto;
	       
	}

	
	/**
	 * This method used to display the active categories
	 * @return list this returns active categories
	 * @throws ServiceException on output error
	 */
	@Transactional
	public List<Category> listActiveCategory() throws ServiceException {
		List<Category> catelist=null;
		catelist= categoryRepository.findActiveCategories();
		if(catelist.isEmpty()) {
			throw new ServiceException(MessageConstant.INVALID_LIST);
		}
		
		return catelist;
	}

	/**
	 * This method is used for delete category
	 * @param categoryId is the parameter to deleteCategory method
	 * @throws ServiceException in case of invalid categoryId
	 */
	
	@Transactional
	public void deleteCategory( int categoryId) throws ServiceException {
		Category category =new Category();
		category.setId(categoryId);
		
		try {
			Boolean active = false;
			categoryRepository.updateStatus(categoryId, active);
		} catch (Exception e) {
			throw new ServiceException(MessageConstant.CATEGORY_DELETE);		
			}
	}

	/**
	 * This method display the searched category details
	 * @param categoryId is the parameter to viewCategory method
	 * @return list this returns category data
	 * @throws ServiceException in case of invalid input
	 */
	public Category viewCategory(Integer categoryId) throws ServiceException {
		
		Optional<Category> findById = categoryRepository.findById(categoryId);
		if (findById.isPresent()) {
			return findById.get();
		}
		else {
			throw new ServiceException("Invalid id");
		}
		
		
	}
}

