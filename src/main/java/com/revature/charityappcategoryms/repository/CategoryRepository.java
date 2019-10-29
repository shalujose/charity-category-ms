package com.revature.charityappcategoryms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.charityappcategoryms.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Modifying
	//@Query("DELETE from Category where id = :id")
	@Query("UPDATE  Category set active = :active where id = :id")
	void updateStatus(@Param("id") int id, @Param("active") Boolean active);

	@Query("from Category where active= 1")
	List<Category> findActiveCategories();
	
}
