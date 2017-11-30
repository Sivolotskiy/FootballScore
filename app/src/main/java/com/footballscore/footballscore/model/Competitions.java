package com.footballscore.footballscore.model;

import com.footballscore.footballscore.net.responce.Response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Competitions extends Response implements Serializable{

    private String id;

    private String name;

    private String text;

    private String time;
}
