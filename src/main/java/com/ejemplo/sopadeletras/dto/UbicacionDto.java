package com.ejemplo.sopadeletras.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder
@Data
public class UbicacionDto {

    @NotNull(message = "EL valor no debe ser nulo")
    @Min(message = "El valor no debe ser menor a 0",value = 0)
    @Max(message = "El valor no debe ser mayor a 80",value = 80)
    Long idTablero;

    @NotNull(message = "EL valor no debe ser nulo")
    @Min(message = "El valor no debe ser menor a 0",value = 0)
    @Max(message = "El valor no debe ser mayor a 80",value = 80)
    int sr;

    @NotNull(message = "EL valor no debe ser nulo")
    @Min(message = "El valor no debe ser menor a 0",value = 0)
    @Max(message = "El valor no debe ser mayor a 80",value = 80)
    int sc;

    @NotNull(message = "EL valor no debe ser nulo")
    @Min(message = "El valor no debe ser menor a 0",value = 0)
    @Max(message = "El valor no debe ser mayor a 80",value = 80)
    int er;

    @NotNull(message = "EL valor no debe ser nulo")
    @Min(message = "El valor no debe ser menor a 0",value = 0)
    @Max(message = "El valor no debe ser mayor a 80",value = 80)
    int ec;
}
