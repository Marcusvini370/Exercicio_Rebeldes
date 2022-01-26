package com.exercicio.rebeldes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Rebelde {

    private String nome;
    private Integer idade;
    private RacaEnum raca;

}
