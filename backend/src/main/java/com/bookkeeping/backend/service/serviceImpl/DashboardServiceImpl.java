package com.bookkeeping.backend.service.serviceImpl;

import com.bookkeeping.backend.dao.CostDao;
import com.bookkeeping.backend.dao.IncomeDao;
import com.bookkeeping.backend.dao.ReconciliationCostDao;
import com.bookkeeping.backend.dao.ReconciliationIncomeDao;
import com.bookkeeping.backend.entity.*;
import com.bookkeeping.backend.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {

    public static String[] MONTH_NAMES = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
    public static Integer NUMBER_OF_MONTH = 12;

    @Autowired
    private IncomeDao incomeDao;
    @Autowired
    private CostDao costDao;
    @Autowired
    private ReconciliationIncomeDao reconciliationIncomeDao;
    @Autowired
    private ReconciliationCostDao reconciliationCostDao;

    @Override
    public YearGross getYearGross(Integer year) {
        Income income = getYearIncome(year);
        Cost cost = getYearCost(year);

        return new YearGross(income, cost, getCumulativeIncome(income), getCumulativeCost(cost), getResult(income, cost));
    }

    @Override
    public ReconciliationYearGross getReconciliationYearGross(Integer year) {
        List<ReconciliationIncome> reconciliationIncome = getReconciliationYearIncome(year);
        List<ReconciliationCost> reconciliationCost = getReconciliationYearCost(year);
        Income income = getYearIncome(year);
        Cost cost = getYearCost(year);
        Map<String, Integer> reconciliationResult = getReconciliationResult(reconciliationIncome, reconciliationCost);
        Map<String, Integer> finalResult = getFinalResult(reconciliationResult, getResult(income, cost));
        Map<String, Integer> cumulativeFinalResult = getCumulativeFinalResult(finalResult);

        return new ReconciliationYearGross(reconciliationIncome, reconciliationCost,
                reconciliationResult, finalResult, cumulativeFinalResult);
    }

    private Map<String, Integer> getCumulativeFinalResult(Map<String, Integer> finalResult) {
        Map<String, Integer> cumulativeFinalResult = new HashMap<>();

        for (int month = 0; month < NUMBER_OF_MONTH; month++) {
            String monthName = MONTH_NAMES[month];
            Integer monthFinalResult = finalResult.get(monthName);

            if (month == 0) {
                if (monthFinalResult == null) {
                    monthFinalResult = 0;
                }

                cumulativeFinalResult.put(monthName, monthFinalResult);
            } else {
                Integer monthCumulativeFinalResult = monthFinalResult + cumulativeFinalResult.get(MONTH_NAMES[month - 1]);

                cumulativeFinalResult.put(monthName, monthCumulativeFinalResult);
            }
        }

        return cumulativeFinalResult;
    }

    private Map<String, Integer> getFinalResult(Map<String, Integer> reconciliationResult, Map<String, Integer> result) {
        Map<String, Integer> finalResult = new HashMap<>();

        for (int month = 0; month < NUMBER_OF_MONTH; month++) {
            String monthName = MONTH_NAMES[month];
            Integer monthReconciliationResult = reconciliationResult.get(monthName);
            Integer monthFinalResult = monthReconciliationResult + result.get(monthName);

            finalResult.put(monthName, monthFinalResult);
        }

        return finalResult;
    }


    private Map<String, Integer> getReconciliationResult(List<ReconciliationIncome> reconciliationIncome,
                                                         List<ReconciliationCost> reconciliationCost) {
        Map<String, Integer> reconciliationResult = new HashMap<>();
        Map<String, Integer> totalReconciliationIncome = getTotalReconciliationIncome(reconciliationIncome);
        Map<String, Integer> totalReconciliationCost = getTotalReconciliationCost(reconciliationCost);

        for (int month = 0; month < NUMBER_OF_MONTH; month++) {
            String monthName = MONTH_NAMES[month];
            Integer monthReconciliationIncome = totalReconciliationIncome.get(monthName);
            Integer monthReconciliationCost = totalReconciliationCost.get(monthName);
            Integer totalReconciliationValue = monthReconciliationIncome - monthReconciliationCost;

            reconciliationResult.put(monthName, totalReconciliationValue);
        }

        return reconciliationResult;
    }

    private Map<String, Integer> getTotalReconciliationIncome(List<ReconciliationIncome> reconciliationIncome) {
        Map<String, Integer> totalReconciliationIncome = new HashMap<>();

        for (int month = 0; month < NUMBER_OF_MONTH; month++) {
            String monthName = MONTH_NAMES[month];
            int yearTotalReconciliationIncome = 0;

            for (int index = 0; index < reconciliationIncome.size(); index++) {
                Integer monthReconciliationIncome = reconciliationIncome.get(index).getMonthValue(monthName);
                if (monthReconciliationIncome != null) {
                    yearTotalReconciliationIncome += monthReconciliationIncome;
                }
            }
            totalReconciliationIncome.put(monthName, yearTotalReconciliationIncome);
        }

        return totalReconciliationIncome;
    }

    private Map<String, Integer> getTotalReconciliationCost(List<ReconciliationCost> reconciliationCost) {
        Map<String, Integer> totalReconciliationCost = new HashMap<>();

        for (int month = 0; month < NUMBER_OF_MONTH; month++) {
            String monthName = MONTH_NAMES[month];
            int yearTotalReconciliationCost = 0;

            for (int index = 0; index < reconciliationCost.size(); index++) {
                ReconciliationCost reconciliationCostByType = reconciliationCost.get(index);
                Integer monthReconciliationCostByType = reconciliationCostByType.getMonthValue(monthName);

                if (monthReconciliationCostByType != null) {
                    yearTotalReconciliationCost += monthReconciliationCostByType;
                }
            }
            totalReconciliationCost.put(monthName, yearTotalReconciliationCost);
        }

        return totalReconciliationCost;
    }

    private List<ReconciliationIncome> getReconciliationYearIncome(Integer year) {
        return reconciliationIncomeDao.findAllIncomeByYear(year);
    }

    private List<ReconciliationCost> getReconciliationYearCost(Integer year) {
        return reconciliationCostDao.findAllCostByYear(year);
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
