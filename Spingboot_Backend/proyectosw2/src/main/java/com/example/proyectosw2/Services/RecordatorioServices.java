package com.example.proyectosw2.Services;


import com.example.proyectosw2.Repository.RecordatorioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RecordatorioServices {

    @Autowired
    private RecordatorioRepository recordatorioRepository;

    public String getRecordatorio(){

        return "";
    }

}
