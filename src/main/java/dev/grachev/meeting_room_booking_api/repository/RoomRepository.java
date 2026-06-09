package dev.grachev.meeting_room_booking_api.repository;

import dev.grachev.meeting_room_booking_api.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("""
        SELECT r FROM Room r
        WHERE (:name IS NULL OR r.name = :name)
    """)
    Page<Room> findAll(@Param("name") String name, Pageable pageable);
    Boolean existsByNameAndIdNot(String name, Long id);
}
