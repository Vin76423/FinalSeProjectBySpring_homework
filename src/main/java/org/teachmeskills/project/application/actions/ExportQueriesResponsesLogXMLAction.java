package org.teachmeskills.project.application.actions;

import org.springframework.stereotype.Component;
import org.teachmeskills.project.controllers.ExportQueriesResponsesLogByXMLController;

@Component
public class ExportQueriesResponsesLogXMLAction implements CommonAction {
    private ExportQueriesResponsesLogByXMLController controller;

    public ExportQueriesResponsesLogXMLAction(ExportQueriesResponsesLogByXMLController exportQueriesResponsesLogByXMLController) {
        this.controller = exportQueriesResponsesLogByXMLController;
    }

    @Override
    public String getName() {
        return "Импортировать историю-лог запросов пользователей в xml-файл";
    }

    @Override
    public void action() {
        controller.createStoryLogXML();
    }
}
