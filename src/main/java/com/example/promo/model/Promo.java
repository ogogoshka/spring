package com.example.promo.model;

import javax.persistence.*;

@Entity
@Table(name = "promo")
public class Promo {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String namePromo;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bonus")
//    @JoinColumn(name = "id", nullable = false)
//    private List<Bonus> bonuses = new ArrayList<>();


//    @OneToMany(mappedBy = "bonus", fetch = FetchType.EAGER)
//    private List<Bonus> bonuses = new ArrayList<>();
//
//    public List<Bonus> getBonuses() {
//        return bonuses;
//    }
//
//    public void setBonuses(List<Bonus> bonuses) {
//        this.bonuses = bonuses;
//    }


    public Promo() {
    }

    public Promo(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamePromo() {
        return namePromo;
    }

    public void setNamePromo(String namePromo) {
        this.namePromo = namePromo;
    }
//
//    public List<Bonus> getBonuses() {
//        return bonuses;
//    }
//
//    public void setBonuses(List<Bonus> bonuses) {
//        this.bonuses = bonuses;
//    }

//    @Override
//    public String toString() {
//        return "Promo{" +
//                "id=" + id +
//                ", namePromo='" + namePromo + '\'' +
//                ", bonuses=" + bonuses +
//                '}';
//    }




}