package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        for (Meal meal: MealsUtil.MEALS){
            repository.putIfAbsent(DEFAULT_USER_ID, new HashMap<>());
            save(meal, DEFAULT_USER_ID);
        }
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (!repository.containsKey(userId)) return null;
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.get(userId).put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return repository.get(userId).computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public void delete(int id, int userId) {
        if (!repository.containsKey(userId)) return;
        repository.get(userId).remove(id);
    }

    @Override
    public Meal get(int id, int userId) {
        if (!repository.containsKey(userId)) return null;
        return repository.get(userId).get(id);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        List<Meal> list = new ArrayList<>(repository.get(userId).values());
        if (!repository.containsKey(userId)) return list;
        list.sort(Comparator.comparing(Meal::getDate));
        Collections.reverse(list);
        return list;
    }
}

