package com.example;

import jakarta.inject.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class ErrorTrigger {
    @PersistenceContext
    private final EntityManager entityManager;

    public ErrorTrigger(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
