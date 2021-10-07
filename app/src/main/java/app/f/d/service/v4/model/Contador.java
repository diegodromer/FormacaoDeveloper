package app.f.d.service.v4.model;

public class Contador {

    // POJO

    private int contagem;
    private String dataContagem;
    private String horaContagem;
    private String descricaoContagem;

    public Contador(int conagem){

        this.contagem = conagem;
    }

    public int getContagem() {
        return contagem;
    }

    public void setContagem(int contagem) {
        this.contagem = contagem;
    }

    public String getDataContagem() {
        return dataContagem;
    }

    public void setDataContagem(String dataContagem) {
        this.dataContagem = dataContagem;
    }

    public String getHoraContagem() {
        return horaContagem;
    }

    public void setHoraContagem(String horaContagem) {
        this.horaContagem = horaContagem;
    }

    public String getDescricaoContagem() {
        return descricaoContagem;
    }

    public void setDescricaoContagem(String descricaoContagem) {
        this.descricaoContagem = descricaoContagem;
    }
}
