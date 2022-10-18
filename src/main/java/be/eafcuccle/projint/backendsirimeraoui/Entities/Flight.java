package be.eafcuccle.projint.backendsirimeraoui.Entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    @Column
    private int code;
    @Column
    private int nbrOfPassengers;
    @PrePersist
    private void nbrOfPassengers(){
        nbrOfPassengers = Reservation.getNbConfirmedReservations();
    }
    @Version
    private long version;
    
    @ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name="airline_company_id")
    private AirlineCompany airlineCompany;
    @ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name="depart_airport_id")
    private Airport departAirport;
    @ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name="arrival_airport_id")
    private Airport arrivalAirport;
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
    // constructors
    public Flight() {
    }
    public Flight(int code, LocalDate departDate, LocalTime departTime, LocalTime arrivalTime) {
        this.code = code;
        this.departDate = departDate;
        this.departTime = departTime;
        this.arrivalTime = arrivalTime;
    }


    //Getters setters
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
    public int getnbrOfPassengers() {
        return nbrOfPassengers;
    }
    public void setnbrOfPassengers(int nbrOfPassengers) {
        this.nbrOfPassengers = nbrOfPassengers;
    }
    public AirlineCompany getAirlineCompany() {
        return airlineCompany;
    }
    public void setAirlineCompany(AirlineCompany airlineCompany) {
        this.airlineCompany = airlineCompany;
    }
    public Airport getDepartAirport() {
        return departAirport;
    }
    public void setDepartAirport(Airport departAirport) {
        this.departAirport = departAirport;
    }
    public Airport getArrivalAirport() {
        return arrivalAirport;
    }
    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
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
    public int getNbrOfPassengers() {
        return nbrOfPassengers;
    }
    public void setNbrOfPassengers(int nbrOfPassengers) {
        this.nbrOfPassengers = nbrOfPassengers;
    }
    public long getVersion() {
        return version;
    }

    
}
