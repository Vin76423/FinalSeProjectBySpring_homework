package org.teachmeskills.project.application.actions.authorisationActions;

import org.springframework.stereotype.Component;
import org.teachmeskills.project.controllers.AuthorisationController;
import org.teachmeskills.project.application.UsersStatus;
import org.teachmeskills.project.exceptions.NotUserException;

@Component
public class CheckAccountAction implements AuthorisationAction {
    private AuthorisationController authorisationController;

    public CheckAccountAction(AuthorisationController authorisationController) {
        this.authorisationController = authorisationController;
    }

    @Override
    public String getName() {
        return "Войти в программу под своим учетным именем";
    }

    @Override
    public UsersStatus action() throws NotUserException {
        return authorisationController.getUsersStatus(AuthorisationAction.getUsersLogin(), AuthorisationAction.getUsersPassword()); }
}
