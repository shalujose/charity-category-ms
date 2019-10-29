package com.revature.charityappcategoryms.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<Category> listCategory() throws ServiceException {
		List<Category> catelist=null;
		catelist= categoryRepository.findAll();
		if(catelist.isEmpty()) {
			throw new ServiceException(MessageConstant.INVALID_LIST);
		}
		
		return catelist;
	}


	@Transactional
	public void deleteCategory( int categoryId) throws ServiceException {
		Category category =new Category();
		category.setId(categoryId);
		
		try {
			categoryRepository.deleteById(categoryId);
		} catch (Exception e) {
			throw new ServiceException(MessageConstant.CATEGORY_DELETE);		
			}
	}
}

