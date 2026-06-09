package dev.grachev.meeting_room_booking_api.repository;

import dev.grachev.meeting_room_booking_api.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("""
        SELECT u FROM User u
        WHERE (:username IS NULL OR u.username = :username)
    """)
    Page<User> findAll(@Param("username") String username, Pageable pageable);
}
