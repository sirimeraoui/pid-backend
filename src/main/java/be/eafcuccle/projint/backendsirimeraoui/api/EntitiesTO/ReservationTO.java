package be.eafcuccle.projint.backendsirimeraoui.api.EntitiesTO;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import be.eafcuccle.projint.backendsirimeraoui.Entities.ReservationStatus;

@Entity
public class ReservationTO {
    @Id
    private long id;

    @Column
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reservationDate;

    // @PrePersist
    // private void onCreate() {
    // reservationDate = LocalDateTime.now();
    // }

    @Column
    private long version;

    public ReservationTO(long id, LocalDateTime reservationDate, ReservationStatus status) {
        this.id = id;
        this.reservationDate = reservationDate;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public long getVersion() {
        return version;
    }

}
