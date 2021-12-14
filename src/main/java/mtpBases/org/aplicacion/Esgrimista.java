package mtpBases.org.aplicacion;

public class Esgrimista {
    String nombre;
    String nacionalidad;
    String manoDominante;
    Integer rankingMundial;

    Esgrimista(String nombre, String nacionalidad, String manoDominante, Integer rankingMundial) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.manoDominante = manoDominante;
        this.rankingMundial = rankingMundial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getManoDominante() {
        return manoDominante;
    }

    public void setManoDominante(String manoDominante) {
        this.manoDominante = manoDominante;
    }

    public Integer getRankingMundial() {
        return rankingMundial;
    }

    public void setRankingMundial(Integer rankingMundial) {
        this.rankingMundial = rankingMundial;
    }
}
