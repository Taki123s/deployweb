package model;

import java.time.LocalDateTime;

public class Bonus {
    private int id;
    private int idProducer;
    private int idGenre;
    private int idMovie;
    private String description;
    private LocalDateTime dayBegin;
    private LocalDateTime dayEnd;
    private double percent;
    private double unitPrice;
    private double samePrice;
    private LocalDateTime createAt;
    private int status=1;

    public Bonus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProducer() {
        return idProducer;
    }

    public void setIdProducer(int idProducer) {
        this.idProducer = idProducer;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDayBegin() {
        return dayBegin;
    }

    public void setDayBegin(LocalDateTime dayBegin) {
        this.dayBegin = dayBegin;
    }

    public LocalDateTime getDayEnd() {
        return dayEnd;
    }

    public void setDayEnd(LocalDateTime dayEnd) {
        this.dayEnd = dayEnd;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getSamePrice() {
        return samePrice;
    }

    public void setSamePrice(double samePrice) {
        this.samePrice = samePrice;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Bonus{" +
                "id=" + id +
                ", idProducer=" + idProducer +
                ", idGenre=" + idGenre +
                ", idMovie=" + idMovie +
                ", description='" + description + '\'' +
                ", dayBegin=" + dayBegin +
                ", dayEnd=" + dayEnd +
                ", percent=" + percent +
                ", unitPrice=" + unitPrice +
                ", samePrice=" + samePrice +
                ", createAt=" + createAt +
                ", status=" + status +
                '}';
    }
}
