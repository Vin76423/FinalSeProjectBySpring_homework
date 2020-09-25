package org.teachmeskills.project.controllers;

import org.springframework.stereotype.Component;
import org.teachmeskills.project.dao.QueriesResponsesLogDBdao;
import org.teachmeskills.project.services.CreateStoryLogXMLService;
import org.teachmeskills.project.services.XMLService;

@Component
public class ExportQueriesResponsesLogByXMLController {
    QueriesResponsesLogDBdao logDBdao;

    public ExportQueriesResponsesLogByXMLController(QueriesResponsesLogDBdao queriesResponsesLogDBdao) {
        this.logDBdao = queriesResponsesLogDBdao;
    }

    public void createStoryLogXML() {
        XMLService service = new CreateStoryLogXMLService(logDBdao.getAllLogLines());
        new Thread(service).start();
    }
}
