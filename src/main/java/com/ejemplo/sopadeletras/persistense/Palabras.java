package com.ejemplo.sopadeletras.persistense;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "palabras")
public class Palabras implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "palabras_id_seq")
    @SequenceGenerator(name = "palabras_id_seq",  sequenceName = "palabras_id_seq", initialValue = 1, allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "palabra")
    private String palabra;

    public String toString() {
        return "Palabras";
    }
}
