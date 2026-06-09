package dev.grachev.meeting_room_booking_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "room")
@SQLRestriction("deleted = false")
public class Room {

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

    @Builder.Default
    @OneToMany(mappedBy = "room")
    private List<Booking> bookings = new ArrayList<>();


    //Основные поля

    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @Positive
    @Column(nullable = false)
    @NotNull
    private Integer capacity;

    @Column(nullable = false, length = 100)
    @NotBlank
    private String location;

    @Column(length = 200)
    private String description;
}
