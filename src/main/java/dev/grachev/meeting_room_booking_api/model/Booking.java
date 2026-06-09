package dev.grachev.meeting_room_booking_api.model;

import dev.grachev.meeting_room_booking_api.enums.BookingStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Builder
@Entity
@Table(name = "bookings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLRestriction("deleted = false")
public class Booking {

    //Служебные поля

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "update_at", nullable = false)
    private Instant updatedAt;

    @Version
    @Column(nullable = false)
    private Long version = 0L;

    @Column(nullable = false)
    private boolean deleted = false;


    //Основные поля

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    @NotNull
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @Column(name = "start_time", nullable = false)
    @Future
    @NotNull
    private Instant startTime;

    @Column(name = "end_time", nullable = false)
    @Future
    @NotNull
    private Instant endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    @NotNull
    private BookingStatus status;

}
