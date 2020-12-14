package ru.nsu.vki.intershop.manager;

import org.slf4j.Logger;
import ru.nsu.vki.intershop.database.IDBService;

public class ParentManager {
    protected IDBService dbService;
    protected Logger log;

    public ParentManager(IDBService dbService, Logger log) {
        this.dbService = dbService;
        this.log = log;
    }
}
