package dev.grachev.meeting_room_booking_api.repository;

import dev.grachev.meeting_room_booking_api.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("""
            SELECT b FROM Booking b
            WHERE (:roomId IS NULL OR b.room.id = :roomId)
                AND (:userId IS NULL OR b.user.id = :userId)
            """)
    Page<Booking> findAll(@Param("roomId") Long roomId,
                          @Param("userId") Long userId,
                          Pageable pageable);
}
