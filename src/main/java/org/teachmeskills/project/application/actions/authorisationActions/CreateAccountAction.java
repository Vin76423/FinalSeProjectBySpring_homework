package org.teachmeskills.project.application.actions.authorisationActions;

import org.springframework.stereotype.Component;
import org.teachmeskills.project.controllers.AuthorisationController;
import org.teachmeskills.project.application.UsersStatus;
import org.teachmeskills.project.exceptions.DuplicateUserException;

@Component
public class CreateAccountAction implements AuthorisationAction {
    private AuthorisationController authorisationController;

    public CreateAccountAction(AuthorisationController authorisationController) {
        this.authorisationController = authorisationController;
    }

    @Override
    public String getName() {
        return "Создать учетную запись";
    }

    @Override
    public UsersStatus action() throws DuplicateUserException {
        return authorisationController.createNewUser(AuthorisationAction.getUsersLogin(), AuthorisationAction.getUsersPassword()); }
}
