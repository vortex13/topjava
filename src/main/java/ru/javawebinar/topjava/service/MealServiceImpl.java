package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    private MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal save(Meal meal, int userId) throws NotFoundException {
        return repository.save(meal, userId);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        repository.delete(id, userId);
    }

    @Override
    public MealWithExceed get(int id, int userId) throws NotFoundException {
        if (repository.get(id, userId) == null) return null;
        List<MealWithExceed> mealsExc = MealsUtil.getWithExceeded(repository.getAll(userId), MealsUtil.DEFAULT_CALORIES_PER_DAY);

        for (MealWithExceed mealWithExceed: mealsExc)
            if (mealWithExceed.getId() == id) return mealWithExceed;

        return null;
    }

    @Override
    public Collection<MealWithExceed> getAll(int userId) throws NotFoundException {
        return MealsUtil.getWithExceeded(repository.getAll(userId), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }
}