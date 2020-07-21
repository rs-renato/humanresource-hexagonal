package br.com.hrs.api.support.message;

import java.beans.Transient;
import java.util.Arrays;
import java.util.List;

public class MensagemRetorno {

    private MensagemRetornoCategoria categoria;
    private int codigo;
    private String descricao;
    private List<String> detalhes;

    public MensagemRetorno() {
        // TODO Auto-generated constructor stub
    }

    public MensagemRetorno(int code, String ...detalhes) {
        this(null, null, code, detalhes);
    }

    public MensagemRetorno(String descricao, int code, String ...detalhes) {
        this(null, descricao, code, detalhes);
    }

    public MensagemRetorno(MensagemRetornoCategoria categoria, int code, String ...detalhes) {
        this(categoria, null, code, detalhes);
    }

    public MensagemRetorno(MensagemRetornoCategoria categoria, int code, List<String> detalhes) {
        this(categoria, null, code, detalhes);
    }

    public MensagemRetorno(MensagemRetornoCategoria categoria, String descricao, int codigo, String ...detalhes) {
        this.categoria = categoria;
        this.descricao = descricao;
        this.codigo = codigo;
        this.detalhes = assertNonEmptyArray(detalhes);
    }

    public MensagemRetorno(MensagemRetornoCategoria categoria, String descricao, int codigo, List<String> detalhes) {
        this.categoria = categoria;
        this.descricao = descricao;
        this.codigo = codigo;
        this.detalhes = detalhes;
    }

    public MensagemRetornoCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(MensagemRetornoCategoria categoria) {
        this.categoria = categoria;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<String> getDetalhes() {
        return detalhes;
    }

    @Transient
    public String getDetalhe() {
        return detalhes != null ? detalhes.get(0) : null;
    }

    public void setDetalhes(List<String> detalhes) {
        this.detalhes = detalhes;
    }

    public static MensagemRetorno build(){
        return new MensagemRetorno();
    }

    public MensagemRetorno categotia(MensagemRetornoCategoria categoria){
        this.categoria = categoria;
        return this;
    }

    public MensagemRetorno codigo(int codigo){
        this.codigo = codigo;
        return this;
    }

    public MensagemRetorno descricao(String descricao){
        this.descricao = descricao;
        return this;
    }

    public MensagemRetorno detalhes(List<String> detalhes){
        this.detalhes = detalhes;
        return this;
    }

    public MensagemRetorno detalhes(String... detalhes){
        this.detalhes = assertNonEmptyArray(detalhes);
        return this;
    }

    private List<String> assertNonEmptyArray(String ...detalhes){

        List<String> list = null;

        if (detalhes != null && detalhes.length ==1 && detalhes[0] == null) {
            return list;
        }else{
            list =  detalhes != null ? Arrays.asList(detalhes) : null;
        }

        return list;
    }

    @Override
    public String toString() {
        return "MensagemRetorno [categoria=" + getCategoria() + ", codigo=" + getCodigo()
                + ", descricao=" + getDescricao() + ", detalhes=" + getDetalhes() + ", detalhe="
                + getDetalhe() + "]";
    }
}
