package io.evilsking.ExpenseService.Services;

import io.evilsking.ExpenseService.Exceptions.ExpenseNotFoundException;
import io.evilsking.ExpenseService.Exceptions.UserNotFoundException;
import io.evilsking.ExpenseService.Helpers.Helpers;
import io.evilsking.ExpenseService.Models.ExpenseModel;
import io.evilsking.ExpenseService.Repositories.ExpenseRepository;
import io.evilsking.ExpenseService.Validators.ExpenseValidator;
import io.evilsking.ExpenseService.dto.ExpenseResponse;
import io.evilsking.ExpenseService.dto.MonthlyExpenseResponse;
import io.evilsking.ExpenseService.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class ExpenseServices {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private WebClient.Builder loadBalancedWebClientBuilder;

    public List<ExpenseResponse> getAllUsersExpenses(Long userId){
        return expenseRepository.findByUserId(userId).stream()
                .map(expense ->
                    ExpenseResponse.builder()
                            .expenseId(expense.getExpenseId())
                            .category(expense.getCategory())
                            .userId(expense.getUserId())
                            .amount(expense.getAmount())
                            .priority(expense.getPriority())
                            .build()
                ).collect(Collectors.toList());
        }

    public String addNewUserExpense(ExpenseModel expenseModel) {

        ExpenseValidator validator = new ExpenseValidator();
        Helpers helpers = new Helpers();


        UserResponse userResponse = fetchUserDetails(expenseModel.getUserId());

        if (!userResponse.getUserName().equals(expenseModel.getUserName()))
        {
            throw new UserNotFoundException("The user with user id: " + expenseModel.getUserId() + " is not valid!");
        }

        if (!validator.validateCategory(expenseModel)){
            return "The category you're trying to add the expense for seems to be invalid. Would you like to create a new category?";
        }

        System.out.println("HERE");

        System.out.println(expenseModel.getExpenseDate());

        if (expenseModel.getExpenseDate() == null){
            return "Please specify the date of the expense!";
        }

        expenseModel.setCreatedOn(helpers.getCurrentDate());

        expenseRepository.save(expenseModel);

        return "Expense saved successfully!";

    }

    public ExpenseModel updateExpense(ExpenseModel expenseModel, Long expenseId) {

        ExpenseValidator validator = new ExpenseValidator();
        Helpers helper = new Helpers();

        UserResponse userResponse = fetchUserDetails(expenseModel.getUserId());

        if (userResponse.getUserName().equals(expenseModel.getUserName()))
        {
            throw new UserNotFoundException("User with user id: " + expenseModel.getUserId() + " not valid!");
        }


        Optional<ExpenseModel> expenseModel1 = expenseRepository.findByExpenseId(expenseId);

        if (expenseModel1.isEmpty()){
            throw new ExpenseNotFoundException("The expense you're trying to update is probably deleted / archived and hence cannot be updated");
        }
        else {
            expenseModel.setExpenseId(expenseId);
            expenseModel.setUpdatedOn(helper.getCurrentDate());
            expenseRepository.save(expenseModel);
            return expenseModel;
        }



    }

    public String deleteExpense(Long expenseId) {
        ExpenseValidator validator = new ExpenseValidator();
        Helpers helper = new Helpers();
        Optional<ExpenseModel> expenseModel = expenseRepository.findByExpenseId(expenseId);

        if (expenseModel.isEmpty())
        {
            throw new ExpenseNotFoundException("The expense you're trying to delete is not available!");
        }

        UserResponse userResponse = fetchUserDetails(expenseModel.get().getUserId());


        if (userResponse.getUserName().equals(expenseModel.get().getUserName()))
        {
            throw new UserNotFoundException("User with user id: " + expenseModel.get().getUserId() + " not valid!");
        }

        expenseModel.get().setDeletedOn(helper.getCurrentDate());
        expenseModel.get().setIsActive(false);
        expenseRepository.save(expenseModel.get());

        return "Expense deleted successfully";

    }


    public UserResponse fetchUserDetails(Long userId){
        UserResponse userResponses = loadBalancedWebClientBuilder.build().get().uri(
                        "http://user-service/api/user/get/{userId}", userId)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();

        return userResponses;
    }


    public MonthlyExpenseResponse getMonthlyExpense(Long userId, Date currentDate) {
        MonthlyExpenseResponse monthlyExpenseResponse = new MonthlyExpenseResponse();
        List<ExpenseModel> allExpenses = expenseRepository.findByUserId(userId);
        List<String> categoryList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.set(Calendar.DATE, 1);
        Date startDate = cal.getTime();
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        Date endDate = cal.getTime();
        monthlyExpenseResponse.setStartDate(startDate);
        monthlyExpenseResponse.setEndDate(endDate);
        monthlyExpenseResponse.setUserId(userId);

        allExpenses = allExpenses.stream()
                .filter(
                        expense -> expense.getExpenseDate().after(startDate) && expense.getExpenseDate().before(endDate)
                ).collect(Collectors.toList());
        if (!allExpenses.isEmpty()){
            monthlyExpenseResponse.setTotalAmount(allExpenses.stream()
                    .map(expense -> expense.getAmount())
                    .reduce(0L, Long::sum));
            categoryList = allExpenses.stream()
                    .map(expense -> expense.getCategory()).collect(Collectors.toList());

        }
        else{
            monthlyExpenseResponse.setTotalAmount(0L);

        }

        monthlyExpenseResponse.setCategoryList(categoryList);

        return monthlyExpenseResponse;
    }
}
