package com.raven.munin.practice;

import org.hibernate.action.internal.EntityActionVetoException;

import javax.persistence.EntityNotFoundException;


public class IssueService {

    public Issue queryById(Long id){
        throw  new EntityNotFoundException(id.toString());
    }
}
