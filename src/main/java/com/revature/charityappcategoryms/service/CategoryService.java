package com.revature.charityappcategoryms.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.charityappcategoryms.dto.CategoryDTO;
import com.revature.charityappcategoryms.dto.MessageConstant;
import com.revature.charityappcategoryms.exception.ServiceException;
import com.revature.charityappcategoryms.model.Category;
import com.revature.charityappcategoryms.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * This method is used to add new categories 
	 * @param categoryDTO as the 
	 * takes createdBy parameter as integer 
	 * takes categoryName parameter as String 
	 * takes active parameter as boolean 
	 * takes createdDate and ModifiedDate parameter as LocalDateTime
	 * @throws ServiceException in case of invalid values
	 */
	@Transactional
	public void addCategory(CategoryDTO categoryDTO) throws ServiceException {
		Category category = new Category();
		category.setCreatedBy(categoryDTO.getCreatedBy());
		category.setCategoryName(categoryDTO.getCategoryName());

		category.setActive(true);
		category.setCreatedDate(LocalDateTime.now());
		category.setModifiedDate(LocalDateTime.now());
		try {
			categoryRepository.save(category);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(MessageConstant.INVALID_CATEGORY);
		}


	}

	/**
	 * This method list all the categories
	 * @return list this returns all the categories
	 */
	@Transactional
	public List<Category> listCategory() {
		return categoryRepository.findAll();

	}

}
