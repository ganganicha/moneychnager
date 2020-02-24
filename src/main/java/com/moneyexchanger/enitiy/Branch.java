package com.moneyexchanger.enitiy;

import javax.persistence.*;

@Entity
@Table(name = "branch")
public class Branch {

    @Id
    @Column(name = "Id")
    int id;

    @Column(name = "branch_id")
    int brancId;

    @Column(name = "branch_name")
    String branchName;

    @Column(name = "available_balance")
    float availableBalance;

    @ManyToOne
    @JoinColumn(name = "currency_Id")
    Currency currency;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrancId() {
        return brancId;
    }

    public void setBrancId(int brancId) {
        this.brancId = brancId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public float getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(float availableBalance) {
        this.availableBalance = availableBalance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", brancId=" + brancId +
                ", branchName='" + branchName + '\'' +
                ", availableBalance='" + availableBalance + '\'' +
                ", currency=" + currency +
                '}';
    }
}
