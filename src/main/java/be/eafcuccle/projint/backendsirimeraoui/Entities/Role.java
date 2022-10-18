package be.eafcuccle.projint.backendsirimeraoui.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="ROLES")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private String title;
    @Column
    private String description;
   
    @Version
    private long version;
    // Getters&&Setters
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
     return id;
 }
   public void setTitle(String title) {
       this.title = title;
   }
   public String getTitle() {
    return title;
}
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public long getVersion() {
        return version;
    }
    public Role(){

    }

    public Role(String title, String description) {
        this.title = title;
        this.description = description;
    }
    
   

    
}
