package com.exercicio.rebeldes;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class Rebelde {

    private String nome;
    private Integer idade;
    private RacaEnum raca;



}
