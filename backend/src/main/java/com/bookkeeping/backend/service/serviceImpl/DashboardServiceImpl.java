package com.bookkeeping.backend.service.serviceImpl;

import com.bookkeeping.backend.dao.CostDao;
import com.bookkeeping.backend.dao.IncomeDao;
import com.bookkeeping.backend.entity.Cost;
import com.bookkeeping.backend.entity.Income;
import com.bookkeeping.backend.entity.YearGross;
import com.bookkeeping.backend.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {

    public static String[] MONTH_NAMES = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
    public static Integer NUMBER_OF_MONTH = 12;

    @Autowired
    private IncomeDao incomeDao;

    @Autowired
    private CostDao costDao;

    @Override
    public YearGross getYearGross(Integer year) {
        Income income = getYearIncome(year);
        Cost cost = getYearCost(year);

        return new YearGross(income, cost, getCumulativeIncome(income), getCumulativeCost(cost), getResult(income, cost));
    }

    public Income getYearIncome(Integer year) {
        return incomeDao.findById(year).get();
    }

    public Cost getYearCost(Integer year) {
        return costDao.findById(year).get();
    }

    private Map<String, Integer> getResult(Income income, Cost cost) {
        Map<String, Integer> result = new HashMap<>();

        for (int month = 0; month < NUMBER_OF_MONTH; month++) {
            String monthName = MONTH_NAMES[month];
            Integer monthIncome = income.getMonthValue(monthName);
            Integer monthCost = cost.getMonthValue(monthName);
            Integer monthResult = monthIncome - monthCost;

            result.put(monthName, monthResult);
        }

        return result;
    }

    public Map<String, Integer> getCumulativeIncome(Income income) {
        Map<String, Integer> cumulativeIncome = new HashMap<>();

        for (int month = 0; month < NUMBER_OF_MONTH; month++) {
            String monthName = MONTH_NAMES[month];
            Integer monthIncome = income.getMonthValue(monthName);

            if (month == 0) {
                cumulativeIncome.put(monthName, monthIncome);
            } else {
                Integer previousMonthCumulativeIncome = cumulativeIncome.get(MONTH_NAMES[month - 1]);
                Integer monthCumulativeIncome = monthIncome + previousMonthCumulativeIncome;

                cumulativeIncome.put(monthName, monthCumulativeIncome);
            }
        }

        return cumulativeIncome;
    }

    public Map<String, Integer> getCumulativeCost(Cost cost) {
        Map<String, Integer> cumulativeCost = new HashMap<>();

        for (int month = 0; month < NUMBER_OF_MONTH; month++) {
            String monthName = MONTH_NAMES[month];
            Integer monthCost = cost.getMonthValue(monthName);

            if (month == 0) {
                cumulativeCost.put(monthName, monthCost);
            } else {
                Integer previousMonthCumulativeCost = cumulativeCost.get(MONTH_NAMES[month - 1]);
                Integer monthCumulativeCost = monthCost + previousMonthCumulativeCost;

                cumulativeCost.put(monthName, monthCumulativeCost);
            }
        }

        return cumulativeCost;
    }

}
