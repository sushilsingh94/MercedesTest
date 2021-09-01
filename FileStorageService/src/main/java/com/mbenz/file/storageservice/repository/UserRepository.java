package com.mbenz.file.storageservice.repository;

import com.mbenz.file.storageservice.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource()
public interface UserRepository extends JpaRepository<Users, Integer> {

}
