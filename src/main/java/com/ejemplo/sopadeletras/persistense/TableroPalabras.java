package com.ejemplo.sopadeletras.persistense;

import javax.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tablero_palabras")
public class TableroPalabras implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tablero_palabras_id_seq")
    @SequenceGenerator(name = "tablero_palabras_id_seq",  sequenceName = "tablero_palabras_id_seq", initialValue = 1, allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_tablero")
    private Tablero tablero;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_palabras")
    private Palabras palabras;

    @Basic(optional = false)
    @Column(name = "resuelto")
    private boolean resuelto;

    public String toString() {
        return "TableroPalabras";
    }
}
