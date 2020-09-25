package org.teachmeskills.project.application.actions.authorisationActions;

import org.springframework.stereotype.Component;
import org.teachmeskills.project.application.exceptions.StopApplicationException;
import org.teachmeskills.project.application.UsersStatus;

@Component
public class StopApplicationFromAuthorisationAction implements AuthorisationAction {
    @Override
    public String getName() {
        return "Выход";
    }

    @Override
    public UsersStatus action() throws StopApplicationException {
        throw new StopApplicationException();
    }
}
