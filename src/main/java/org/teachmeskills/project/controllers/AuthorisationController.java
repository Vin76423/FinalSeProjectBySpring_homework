package org.teachmeskills.project.controllers;

import org.springframework.stereotype.Component;
import org.teachmeskills.project.dao.UsersDao;
import org.teachmeskills.project.application.UsersStatus;
import org.teachmeskills.project.exceptions.DuplicateUserException;
import org.teachmeskills.project.exceptions.NotUserException;

@Component
public class AuthorisationController {
    UsersDao usersDao;

    public AuthorisationController(UsersDao usersDBdao) {
        this.usersDao = usersDBdao;
    }

    public UsersStatus getUsersStatus(String login, String password) throws NotUserException {
        int statusID = usersDao.getUsersStatusID(login, password);
        if (statusID == 0) throw new NotUserException();
        return UsersStatus.values()[statusID - 1];
    }

    public UsersStatus createNewUser(String login, String password) throws DuplicateUserException {
        usersDao.setUser(login, password);
        return UsersStatus.USER;
    }
}
