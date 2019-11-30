package com.amdocs.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amdocs.crm.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
