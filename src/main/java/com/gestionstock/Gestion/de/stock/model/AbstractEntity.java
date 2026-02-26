package com.gestionstock.Gestion.de.stock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // premet de mettre à jour les champ de date dans la base de donnée.
public class AbstractEntity implements Serializable {


    @Id
    @GeneratedValue
    private  Integer id;

    @CreatedDate
    @Column(name ="creationDate", nullable = false, updatable = false)
    private Instant creationDate;

    @LastModifiedDate
    @Column(name ="lastModifieDate" )
    private Instant lastModifieDate;

//    @PrePersist
//    void prePersist() {
//        creationDate = Instant.now();
//    }
//
//    @PreUpdate
//    void preUpdate() {
//        lastModifieDate = Instant.now();
//    }
}
