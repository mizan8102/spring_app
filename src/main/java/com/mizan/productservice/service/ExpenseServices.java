package com.mizan.productservice.service;

import com.mizan.productservice.model.Expense;
import org.springframework.stereotype.Service;
import com.mizan.productservice.repository.ExpenseRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ExpenseServices {

    private final ExpenseRepository expenseRepository;

    public ExpenseServices(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void addExpense(Expense expense){
        expenseRepository.insert(expense);
    }
    public void updateExpense(Expense expense){
        Expense savedExpense = expenseRepository.findById(expense.getId())
                .orElseThrow(()-> new RuntimeException(
                        String.format("Cannot Find Expense by ID %s",expense.getId())));

        savedExpense.setExpenseName(expense.getExpenseName());
        savedExpense.setExpenseCategory(expense.getExpenseCategory());
        savedExpense.setExpenseAmount(expense.getExpenseAmount());
        expenseRepository.save(expense);
    }
    public List<Expense> getAllExpense(){
        return expenseRepository.findAll();
    }
    public Expense getExpenseByName(String name) {
        try {
            return expenseRepository.findByName(name).orElseThrow(() -> new NoSuchElementException(
                    String.format("Cannot Find Expense by Name %s", name)
            ));

        } catch (NoSuchElementException e) {
            return new Expense();
        }
    }

        public void deleteExpense(String id){
            expenseRepository.deleteById(id);
    }
}
