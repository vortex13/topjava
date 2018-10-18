package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.util.Collection;

public interface MealService {
    Meal save(Meal meal, int userId);

    void delete(int id, int userId);

    MealWithExceed get(int id, int userId);

    Collection<MealWithExceed> getAll(int userId);
}