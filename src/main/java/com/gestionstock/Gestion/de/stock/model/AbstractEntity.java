package com.gestionstock.Gestion.de.stock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    @JsonIgnore
    private Instant creationDate;

    @LastModifiedDate
    @JsonIgnore
    private Instant lastModifieDate;
}
