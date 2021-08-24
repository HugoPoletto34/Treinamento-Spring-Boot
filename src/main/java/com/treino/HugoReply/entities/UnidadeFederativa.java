package com.treino.HugoReply.entities;

public enum UnidadeFederativa {
    ACRE("AC"),
    ALAGOAS("AL"),
    AMAPÁ("AP"),
    AMAZONAS("AM"),
    BAHIA("BA"),
    CEARÁ("CE"),
    DISTRITO_FEDERAL("DF"),
    ESPIRITO_SANTO("ES"),
    GOIÁS("GO"),
    MARANHÃO("MA"),
    MATO_GROSSO("MT"),
    MATO_GROSSO_DO_SUL("MS"),
    MINAS_GERAIS("MG"),
    PARÁ("PA"),
    PARAÍBA("PB"),
    PARANÁ("PR"),
    PERNAMBUCO("PE"),
    PIAUÍ("PI"),
    RIO_DE_JANEIRO("RJ"),
    RIO_GRANDE_DO_NORTE("RN"),
    RIO_GRANDE_DO_SUL("RS"),
    RONDÔNIA("RO"),
    RORAIMA("RR"),
    SANTA_CATARINA("SC"),
    SÃO_PAULO("SP"),
    SERGIPE("SE"),
    TOCANTINS("TO");

    private String uf;

    UnidadeFederativa(String uf) {
        this.uf = uf;
    }

    public String getUf(){
        return uf;
    }
}
