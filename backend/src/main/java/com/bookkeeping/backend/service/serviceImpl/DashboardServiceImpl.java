package com.bookkeeping.backend.service.serviceImpl;

import com.bookkeeping.backend.dao.CostDao;
import com.bookkeeping.backend.dao.IncomeDao;
import com.bookkeeping.backend.dao.ReconciliationExpenseDao;
import com.bookkeeping.backend.dao.ReconciliationIncomeDao;
import com.bookkeeping.backend.entity.*;
import com.bookkeeping.backend.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.bookkeeping.backend.utility.Constants.MONTH_NAMES;
import static com.bookkeeping.backend.utility.Constants.NUMBER_OF_MONTH;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    IncomeDao incomeDao;
    @Autowired
    CostDao costDao;
    @Autowired
    ReconciliationIncomeDao reconciliationIncomeDao;
    @Autowired
    ReconciliationExpenseDao reconciliationExpenseDao;

    @Override
    public YearGross getYearGross(Integer year) {
        Income income = getYearIncome(year);
        Cost cost = getYearCost(year);

        if (income == null || cost == null) {
            return null;
        }

        return new YearGross(income, cost, getCumulativeIncome(income), getCumulativeCost(cost), getResult(income, cost));
    }

    @Override
    public ReconciliationYearGross getReconciliationYearGross(Integer year) {
        Income income = getYearIncome(year);
        Cost cost = getYearCost(year);

        if(income == null || cost == null) {
            return null;
        }

        List<ReconciliationIncome> reconciliationIncome = getReconciliationYearIncome(year);
        List<ReconciliationExpense> reconciliationExpense = getReconciliationYearExpense(year);

        Map<String, Integer> reconciliationResult = getReconciliationResult(reconciliationIncome, reconciliationExpense);
        Map<String, Integer> finalResult = getFinalResult(reconciliationResult, getResult(income, cost));
        Map<String, Integer> cumulativeFinalResult = getCumulativeFinalResult(finalResult);

        return new ReconciliationYearGross(reconciliationIncome, reconciliationExpense,
                reconciliationResult, finalResult, cumulativeFinalResult);
    }

    @Override
    public List<Integer> getYears() {
        List<Integer> yearListFromIncome = incomeDao.findYears();
        List<Integer> yearListFromCost = costDao.findYears();
        List<Integer> years = new ArrayList<>();

        for (Integer incomeYear : yearListFromIncome) {
            if (yearListFromCost.contains(incomeYear)) {
                years.add(incomeYear);
            }
        }

        return years;
    }

    private Map<String, Integer> getCumulativeFinalResult(Map<String, Integer> finalResult) {
        Map<String, Integer> cumulativeFinalResult = new LinkedHashMap<>();

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
        Map<String, Integer> finalResult = new LinkedHashMap<>();

        for (int month = 0; month < NUMBER_OF_MONTH; month++) {
            String monthName = MONTH_NAMES[month];
            Integer monthReconciliationResult = reconciliationResult.get(monthName);
            Integer monthFinalResult = monthReconciliationResult + result.get(monthName);

            finalResult.put(monthName, monthFinalResult);
        }

        return finalResult;
    }


    private Map<String, Integer> getReconciliationResult(List<ReconciliationIncome> reconciliationIncome,
                                                         List<ReconciliationExpense> reconciliationExpense) {
        Map<String, Integer> reconciliationResult = new LinkedHashMap<>();
        Map<String, Integer> totalReconciliationIncome = getTotalReconciliationIncome(reconciliationIncome);
        Map<String, Integer> totalReconciliationExpense = getTotalReconciliationExpense(reconciliationExpense);

        for (int month = 0; month < NUMBER_OF_MONTH; month++) {
            String monthName = MONTH_NAMES[month];
            Integer monthReconciliationIncome = totalReconciliationIncome.get(monthName);
            Integer monthReconciliationCost = totalReconciliationExpense.get(monthName);
            Integer totalReconciliationValue = monthReconciliationIncome - monthReconciliationCost;

            reconciliationResult.put(monthName, totalReconciliationValue);
        }

        return reconciliationResult;
    }

    private Map<String, Integer> getTotalReconciliationIncome(List<ReconciliationIncome> reconciliationIncome) {
        Map<String, Integer> totalReconciliationIncome = new LinkedHashMap<>();

        for (int month = 0; month < NUMBER_OF_MONTH; month++) {
            String monthName = MONTH_NAMES[month];
            int yearTotalReconciliationIncome = 0;

            for (int index = 0; index < reconciliationIncome.size(); index++) {
                Integer monthReconciliationIncome = reconciliationIncome.get(index).getMonths().getMonthValue(monthName);
                if (monthReconciliationIncome != null) {
                    yearTotalReconciliationIncome += monthReconciliationIncome;
                }
            }
            totalReconciliationIncome.put(monthName, yearTotalReconciliationIncome);
        }

        return totalReconciliationIncome;
    }

    private Map<String, Integer> getTotalReconciliationExpense(List<ReconciliationExpense> reconciliationExpense) {
        Map<String, Integer> totalReconciliationExpense = new LinkedHashMap<>();

        for (int month = 0; month < NUMBER_OF_MONTH; month++) {
            String monthName = MONTH_NAMES[month];
            int yearTotalReconciliationExpense = 0;

            for (int index = 0; index < reconciliationExpense.size(); index++) {
                ReconciliationExpense reconciliationExpenseByType = reconciliationExpense.get(index);
                Integer monthReconciliationExpenseByType = reconciliationExpenseByType.getMonths().getMonthValue(monthName);

                if (monthReconciliationExpenseByType != null) {
                    yearTotalReconciliationExpense += monthReconciliationExpenseByType;
                }
            }
            totalReconciliationExpense.put(monthName, yearTotalReconciliationExpense);
        }

        return totalReconciliationExpense;
    }

    private List<ReconciliationIncome> getReconciliationYearIncome(Integer year) {
        return reconciliationIncomeDao.findAllIncomeByYear(year);
    }

    private List<ReconciliationExpense> getReconciliationYearExpense(Integer year) {
        return reconciliationExpenseDao.findAllExpenseByYear(year);
    }

    public Income getYearIncome(Integer year) {
        Optional<Income> income = incomeDao.findById(year);

        if (income.isPresent()) {
            return income.get();
        } else {
            return null;
        }
    }

    public Cost getYearCost(Integer year) {
        Optional<Cost> cost = costDao.findById(year);

        if (cost.isPresent()) {
            return cost.get();
        } else {
            return null;
        }
    }

    private Map<String, Integer> getResult(Income income, Cost cost) {
        Map<String, Integer> result = new LinkedHashMap<>();

        for (int month = 0; month < NUMBER_OF_MONTH; month++) {
            String monthName = MONTH_NAMES[month];
            Integer monthIncome = income.getMonths().getMonthValue(monthName);
            Integer monthCost = cost.getMonths().getMonthValue(monthName);
            Integer monthResult = monthIncome - monthCost;

            result.put(monthName, monthResult);
        }

        return result;
    }

    public Map<String, Integer> getCumulativeIncome(Income income) {
        Map<String, Integer> cumulativeIncome = new LinkedHashMap<>();

        for (int month = 0; month < NUMBER_OF_MONTH; month++) {
            String monthName = MONTH_NAMES[month];
            Integer monthIncome = income.getMonths().getMonthValue(monthName);

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
        Map<String, Integer> cumulativeCost = new LinkedHashMap<>();

        for (int month = 0; month < NUMBER_OF_MONTH; month++) {
            String monthName = MONTH_NAMES[month];
            Integer monthCost = cost.getMonths().getMonthValue(monthName);

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
