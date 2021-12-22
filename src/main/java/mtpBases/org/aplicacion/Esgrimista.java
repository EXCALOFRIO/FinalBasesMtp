package mtpBases.org.aplicacion;

public class Esgrimista {
    String nombre;
    String nacionalidad;
    String manoDominante;
    Integer rankingMundial;
    private Integer clasificacion;

    public Integer getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Integer clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFechaCompeticion() {
        return fechaCompeticion;
    }

    public void setFechaCompeticion(String fechaCompeticion) {
        this.fechaCompeticion = fechaCompeticion;
    }

    public String getLuarCompeticion() {
        return luarCompeticion;
    }

    public void setLuarCompeticion(String luarCompeticion) {
        this.luarCompeticion = luarCompeticion;
    }

    public Integer getNumeroVictorias() {
        return numeroVictorias;
    }

    public void setNumeroVictorias(Integer numeroVictorias) {
        this.numeroVictorias = numeroVictorias;
    }

    private String id;
    private String fechaCompeticion;
    private String luarCompeticion;
    private Integer numeroVictorias;

    Esgrimista(String nombre, String nacionalidad, String manoDominante, Integer rankingMundial) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.manoDominante = manoDominante;
        this.rankingMundial = rankingMundial;
    }

    Esgrimista(Integer clasificacion, String id, String fechaCompeticion, String luarCompeticion,
            Integer numeroVictorias) {
        this.clasificacion = clasificacion;
        this.id = id;
        this.fechaCompeticion = fechaCompeticion;
        this.luarCompeticion = luarCompeticion;
        this.numeroVictorias = numeroVictorias;
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
