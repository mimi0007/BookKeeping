package com.bookkeeping.backend.entity;

import java.util.Map;

public class YearGross {
    public Income income;
    public Cost cost;
    public Map<String, Integer> cumulativeIncome;
    public Map<String, Integer> cumulativeCost;
    public Map<String, Integer> result;

    public YearGross(Income income, Cost cost, Map<String, Integer> cumulativeIncome, Map<String,
            Integer> cumulativeCost, Map<String, Integer> result) {
        this.income = income;
        this.cost = cost;
        this.cumulativeIncome = cumulativeIncome;
        this.cumulativeCost = cumulativeCost;
        this.result = result;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public Map<String, Integer> getCumulativeIncome() {
        return cumulativeIncome;
    }

    public void setCumulativeIncome(Map<String, Integer> cumulativeIncome) {
        this.cumulativeIncome = cumulativeIncome;
    }

    public Map<String, Integer> getCumulativeCost() {
        return cumulativeCost;
    }

    public void setCumulativeCost(Map<String, Integer> cumulativeCost) {
        this.cumulativeCost = cumulativeCost;
    }

    public Map<String, Integer> getResult() {
        return result;
    }

    public void setResult(Map<String, Integer> result) {
        this.result = result;
    }


}
