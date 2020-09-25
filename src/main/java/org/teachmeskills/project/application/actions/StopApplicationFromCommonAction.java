package org.teachmeskills.project.application.actions;

import org.springframework.stereotype.Component;
import org.teachmeskills.project.application.exceptions.StopApplicationException;

@Component
public class StopApplicationFromCommonAction implements CommonAction {
    @Override
    public String getName() {
        return "Выход";
    }

    @Override
    public void action() throws StopApplicationException {
        throw new StopApplicationException();
    }

}
