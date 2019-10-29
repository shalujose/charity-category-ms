package com.revature.charityappcategoryms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.charityappcategoryms.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("form Category DELETE where id = :id")
	void deleteById(int id);
	
}
