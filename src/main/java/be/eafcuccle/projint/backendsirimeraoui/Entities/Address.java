package be.eafcuccle.projint.backendsirimeraoui.Entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
   
    @Column
    private String town ;
    @Column
    private Long postalCode;
    @Column
    private String region;
    @Column
    private String country;
     
    @Column
    private long version;
    
    public Address() {};
    public Address(String town, Long postalCode, String region, String country) {
        this.town = town;
        this.postalCode = postalCode;
        this.region = region;
        this.country = country;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }
    public Long getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(Long postalCode) {
        this.postalCode = postalCode;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public long getVersion() {
        return version;
    }   
}
