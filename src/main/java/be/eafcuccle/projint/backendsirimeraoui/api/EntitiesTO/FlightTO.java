package be.eafcuccle.projint.backendsirimeraoui.api.EntitiesTO;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class FlightTO {
    
    @Id
    private Long id;
    @Column
    private int code;
    @Column
    private int nbrOfPassengers;
     // Time and date
     @Column
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
     private LocalDate departDate;
     @Column
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm[:ss]")
     private LocalTime departTime;
     @Column
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm[:ss]")
     private LocalTime arrivalTime;

     
    @Column
    private long version;

    public FlightTO(Long id, int code, int nbrOfPassengers, LocalDate departDate, LocalTime departTime,
            LocalTime arrivalTime,long version) {
        this.id = id;
        this.code = code;
        this.nbrOfPassengers = nbrOfPassengers;
        this.departDate = departDate;
        this.departTime = departTime;
        this.arrivalTime = arrivalTime;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getNbrOfPassengers() {
        return nbrOfPassengers;
    }

    public void setNbrOfPassengers(int nbrOfPassengers) {
        this.nbrOfPassengers = nbrOfPassengers;
    }

    public LocalDate getDepartDate() {
        return departDate;
    }

    public void setDepartDate(LocalDate departDate) {
        this.departDate = departDate;
    }

    public LocalTime getDepartTime() {
        return departTime;
    }

    public void setDepartTime(LocalTime departTime) {
        this.departTime = departTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public long getVersion() {
        return version;
    } 

    
}
