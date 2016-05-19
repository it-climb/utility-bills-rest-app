package com.ubr.repository;

import com.ubr.model.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "admin", path = "admins")
public interface UserRepository extends PagingAndSortingRepository<Admin, Long>{

    List<Admin> findByName(@Param("name") String name);

    @Query("SELECT a.name from Admin a where a.id = :id")
    Admin findNameById(@Param("id") Long id);


}
