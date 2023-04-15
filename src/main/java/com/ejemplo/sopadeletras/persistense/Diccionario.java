package com.ejemplo.sopadeletras.persistense;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "diccionario")
public class Diccionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diccionario_id_seq")
    @SequenceGenerator(name = "diccionario_id_seq",  sequenceName = "diccionario_id_seq", initialValue = 1, allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "letra")
    private Character letra;

    public String toString() {
        return "Diccionario";
    }
}
