package constructors;

import java.util.List;

public class CommentsConstructor {

    String autor;

    String fechasEscritura;

    String country;

    String mark;

    String title;

    String negative;

    String positive;

    String fechasEstancia;

    List<String> etiquetas;

    public CommentsConstructor() {
    }

    public String getFechasEscritura() {
        return fechasEscritura;
    }

    public void setFechasEscritura(String fechasEscritura) {
        this.fechasEscritura = fechasEscritura;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNegative() {
        return negative;
    }

    public void setNegative(String negative) {
        this.negative = negative;
    }

    public String getPositive() {
        return positive;
    }

    public void setPositive(String positive) {
        this.positive = positive;
    }

    public String getFechasEstancia() {
        return fechasEstancia;
    }

    public void setFechasEstancia(String fechasEstancia) {
        this.fechasEstancia = fechasEstancia;
    }

    public List<String> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<String> etiquetas) {
        this.etiquetas = etiquetas;
    }
}
