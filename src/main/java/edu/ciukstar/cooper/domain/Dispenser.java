package edu.ciukstar.cooper.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author sergiu
 */
@Entity
@Table(name = "DISPENSERS")
public class Dispenser implements Persistable<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PURCHASE", nullable = false, referencedColumnName = "ID")
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "WAREHOUSE", nullable = false, referencedColumnName = "ID")
    private Warehouse warehouse;

    @Embedded
    private Schedule schedule;

    @Override
    public String toString() {
        return "Dispenser{" + "purchase=" + purchase + ", warehouse=" + warehouse + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.purchase);
        hash = 13 * hash + Objects.hashCode(this.warehouse);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dispenser other = (Dispenser) obj;
        if (!Objects.equals(this.purchase, other.purchase)) {
            return false;
        }
        if (!Objects.equals(this.warehouse, other.warehouse)) {
            return false;
        }
        return true;
    }

    public Long getId() {
        return id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public boolean isNew() {
        return null == getId();
    }

}
