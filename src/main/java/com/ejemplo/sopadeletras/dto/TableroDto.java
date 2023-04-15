package com.ejemplo.sopadeletras.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder
@Data
public class TableroDto {

        @NotNull(message = "EL valor no debe ser nulo")
        @Min(message = "El valor no debe ser menor a 15",value = 15)
        @Max(message = "El valor no debe ser mayor a 80",value = 80)
        int alto;

        @NotNull(message = "EL valor no debe ser nulo")
        @Min(message = "El valor no debe ser menor a 15",value = 15)
        @Max(message = "El valor no debe ser mayor a 80",value = 80)
        int ancho;

        @NotNull(message = "EL valor no debe ser nulo")
        boolean izq_a_der;

        @NotNull(message = "EL valor no debe ser nulo")
        boolean der_a_izq;

        @NotNull(message = "EL valor no debe ser nulo")
        boolean arr_a_abj;

        @NotNull(message = "EL valor no debe ser nulo")
        boolean abj_a_arr;

        @NotNull(message = "EL valor no debe ser nulo")
        boolean diagonal;
    }
