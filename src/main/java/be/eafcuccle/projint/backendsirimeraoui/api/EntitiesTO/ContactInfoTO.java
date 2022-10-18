package be.eafcuccle.projint.backendsirimeraoui.api.EntitiesTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ContactInfoTO {
    
    @Id
    private Long id;

    @Column
    private Long phoneNumber;

    @Column
    private String emailAddress;
    
    @Column
    private long version;

    public ContactInfoTO(Long id, Long phoneNumber, String emailAddress,long version) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.version = version;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public long getVersion() {
        return version;
    }  
}
