package com.exercicio;

import com.exercicio.rebeldes.RacaEnum;
import com.exercicio.rebeldes.Rebelde;
import com.exercicio.rebeldes.RebeldeView;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class RebeldesApp
{
    public static void main( String[] args )
    {
        RebeldeView rebeldeView = new RebeldeView();
        rebeldeView.menuRebelde();
    }
}
