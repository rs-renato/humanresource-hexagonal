package br.com.hrs.api.support.message;

import java.util.List;

public class MensagemRetornoBuilder {

    private MensagemRetornoCategoria categoria;
    private int codigo;
    private String descricao;
    private String[] detalhes;

    private MensagemRetornoBuilder() {
    }

    public static MensagemRetornoBuilder builder(){
        return new MensagemRetornoBuilder();
    }

    public MensagemRetornoBuilder categoria(MensagemRetornoCategoria categoria) {
        this.categoria = categoria;
        return this;
    }

    public MensagemRetornoBuilder codigo(int codigo) {
        this.codigo = codigo;
        return this;
    }

    public MensagemRetornoBuilder descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public MensagemRetornoBuilder detalhes(String ...detalhes) {
        this.detalhes = detalhes;
        return this;
    }

    public MensagemRetornoBuilder detalhes(List<String> detalhes) {
        this.detalhes = detalhes != null ? detalhes.toArray(new String[0]) : null;
        return this;
    }

    public MensagemRetorno buildMensagem(){
        return buildMensagem(this.categoria, this.descricao, this.codigo, this.detalhes);
    }

    public static MensagemRetorno buildMensagem(int codigo, String ...errors){
        return buildMensagem(null, null, codigo, errors);
    }

    public static MensagemRetorno buildMensagem(String descricao, int codigo, String ...errors){
        return buildMensagem(null, descricao, codigo, errors);
    }

    public static MensagemRetorno buildMensagem(MensagemRetornoCategoria categoria, int codigo, String ...errors){
        return buildMensagem(categoria, null, codigo, errors);
    }

    public static MensagemRetorno buildMensagem(MensagemRetornoCategoria categoria, String descricao, int codigo, String ...errors){
        return new MensagemRetorno(categoria, descricao, codigo, errors);
    }
}

