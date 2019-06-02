package com.example.promo.model;

import javax.persistence.*;

@Entity
@Table(name = "bonus")
public class Bonus {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String bonusName;

    @Column(name = "instock")
    private int inStock;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "promoid", nullable = false)
    private Promo promo;

//    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty("post_id")

//    @JoinColumn(name = "promo_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonProperty("promo_id")


//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id")
//    private Promo promo;

    public Bonus() {
    }

    public Bonus(String bonusName, int inStock, Promo promo) {
        this.bonusName = bonusName;
        this.inStock = inStock;
        this.promo = promo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBonusName() {
        return bonusName;
    }

    public void setBonusName(String bonusName) {
        this.bonusName = bonusName;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public Promo getPromo() {
        return promo;
    }

    public void setPromo(Promo promo) {
        this.promo = promo;
    }

    @Override
    public String toString() {
        return "Bonus{" +
                "id=" + id +
                ", bonusName='" + bonusName + '\'' +
                ", inStock=" + inStock +
                ", promo=" + promo +
                '}';
    }
}