package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.util.Collection;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal create(Meal meal) {
        log.info("create");
        return service.save(meal, authUserId());
    }

    public Meal update(Meal meal) {
        log.info("update");
        return service.save(meal, authUserId());
    }

    public void delete(int id) {
        log.info("delete");
        service.delete(id, authUserId());
    }

    public MealWithExceed get(int id) {
        log.info("get");
        return service.get(id, authUserId());
    }

    public Collection<MealWithExceed> getAll() {
        log.info("getAll");
        return service.getAll(authUserId());
    }
}