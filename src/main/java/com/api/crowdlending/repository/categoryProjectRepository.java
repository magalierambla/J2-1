package com.api.crowdlending.repository;

import java.util.Optional;

import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.crowdlending.model.category_project;
import com.api.crowdlending.model.project;

import java.util.List;

@Repository
@Table(name = "list_category_project")
public interface  categoryProjectRepository extends JpaRepository<category_project, Long> {
	

	Optional<category_project> findByNom(String nomCategorie); 
	
	 @Query(value = "SELECT * FROM  list_category_project   ORDER BY nbr_projects  DESC  LIMIT 5", nativeQuery = true)
	 List<category_project> findCustumCategories(); 

}
