package dev.grachev.meeting_room_booking_api.enums;

public enum BookingStatus {
    PENDING,
    CONFIRMED,
    CANCELLED,
    COMPLETED;

    public boolean canTransitionTo(BookingStatus newStatus){
        return switch (this){
            case PENDING -> newStatus == CONFIRMED || newStatus == CANCELLED;
            case CONFIRMED -> newStatus == CANCELLED || newStatus == CONFIRMED;
            case CANCELLED, COMPLETED -> false;
        };
    }
}
