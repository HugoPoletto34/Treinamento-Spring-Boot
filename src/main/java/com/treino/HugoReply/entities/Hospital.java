package com.treino.HugoReply.entities;

import com.treino.HugoReply.dto.Request.HospitalRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_hospital")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Hospital implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nome;

    @Embedded
    private Endereco endereco;

    @ManyToMany
    @JoinTable (name = "tb_hospital_profissional",
            joinColumns = @JoinColumn (name = "hospital_codigo"),
            inverseJoinColumns = @JoinColumn (name = "profissional_matricula")
    )
    private List<Profissional> profissionais;

    public Hospital(HospitalRequestDTO build) {
    }
}
