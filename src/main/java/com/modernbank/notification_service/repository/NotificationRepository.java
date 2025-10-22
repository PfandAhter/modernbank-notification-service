package com.modernbank.notification_service.repository;

import com.modernbank.notification_service.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {

    @Query("SELECT n FROM Notification n WHERE n.userId =:userId AND n.isRead = false AND n.isDeleted = false")
    Optional<List<Notification>> findAllByUserIdAndIsReadFalse(String userId);
}