package org.teachmeskills.project.application.actions.authorisationActions;

import org.teachmeskills.project.application.actions.Action;
import org.teachmeskills.project.application.exceptions.StopApplicationException;
import org.teachmeskills.project.application.utils.Input;
import org.teachmeskills.project.application.UsersStatus;
import org.teachmeskills.project.exceptions.DuplicateUserException;
import org.teachmeskills.project.exceptions.NotUserException;

public interface AuthorisationAction extends Action {
    UsersStatus action() throws NotUserException, DuplicateUserException, StopApplicationException;

    static String getUsersLogin() { return Input.getString("Введите логин:"); }
    static String getUsersPassword() { return Input.getString("Введите пароль:"); }
}
