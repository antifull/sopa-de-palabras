package com.ejemplo.sopadeletras.persistense;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tablero_palabras")
public class TableroPalabras implements Serializable {

    private static final long serialVersionUID = 1L;
//    @Id
//    @NonNull
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tablero_palabras_id_seq")
//    @SequenceGenerator(name = "tablero_palabras_id_seq",  sequenceName = "tablero_palabras_id_seq", initialValue = 1, allocationSize = 1)
//    @Basic(optional = false)
//    @Column(name = "id")
//    private Long id;
    @Id
    @NonNull
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @NonNull
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_tablero")
    private Tablero tablero;

    @NonNull
//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_palabras")
    private Palabras palabras;

    @Basic(optional = false)
    @Column(name = "resuelto")
    private boolean resuelto;

    @Basic(optional = false)
    @Column(name = "sr")
    private Long sr;

    @Basic(optional = false)
    @Column(name = "sc")
    private Long sc;

    @Basic(optional = false)
    @Column(name = "er")
    private Long er;

    @Basic(optional = false)
    @Column(name = "ec")
    private Long ec;

    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;

    public String toString() {
        return "TableroPalabras";
    }
}
