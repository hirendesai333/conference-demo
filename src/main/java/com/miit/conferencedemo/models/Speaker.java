package com.miit.conferencedemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "speakers")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long speaker_id;
    private String first_name;
    private String last_name;
    private String title;
    private String company;
    private String speaker_bio;

    /*@Lob //lob class stands for large objects / helps JPA deal with larger data
    @Type(type = "org.hibernate.type.BinaryType") //type class helps hibernate deal with binary data
    private byte[] speaker_photo;*/

    @JsonIgnore
    @ManyToMany(mappedBy = "speakers")
    private List<Session> sessions;
}
