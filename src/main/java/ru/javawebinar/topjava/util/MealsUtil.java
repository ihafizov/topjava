package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class MealsUtil {
    public static void main(String[] args) {
        List<Meal> meals = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<MealTo> mealsWithExcess = getFilteredWithExcess(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<MealTo> getFilteredWithExcess(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly excess field
        final Map<LocalDate, Integer> sumOfCaloriesByDate = new HashMap<>();
        meals.forEach(meal -> sumOfCaloriesByDate.merge(meal.getDateTime().toLocalDate(), meal.getCalories(), Integer::sum);

        final List<MealTo> mealsWithExcess = new ArrayList<>();
        meals.forEach(meal -> {
            if (TimeUtil.isBetween(meal.getDateTime(), startTime, endTime)) {
                mealsWithExcess.add(createMealWithExcess(meal, sumOfCaloriesByDate.get(meal.getDateTime().toLocalDate()) > caloriesPerDay)))
            }
        });

        return mealsWithExcess;
    }

    public static MealTo createMealWithExcess(Meal meal, boolean excess) {
        return new MealTo(meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }


}