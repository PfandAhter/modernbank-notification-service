package com.modernbank.notification_service.repository;


import com.modernbank.notification_service.entity.ErrorCodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorCodeRepository extends JpaRepository<ErrorCodes, String> {

}