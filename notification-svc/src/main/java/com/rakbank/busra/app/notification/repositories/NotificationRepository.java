package com.rakbank.busra.app.notification.repositories;


import com.rakbank.busra.app.notification.models.Notification;
import com.rakbank.busra.app.notification.models.NotificationStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByStatus(@NonNull NotificationStatus status, Pageable pageable);
}
