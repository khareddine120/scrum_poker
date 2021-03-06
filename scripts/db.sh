#!/usr/bin/env bash
mongo << EOF
server = new Mongo("localhost:27017");
var databaseList = [
                    {'db' :'scrumPoker', 'user':'scrumPokerApp', 'pwd':'spa'}
                    ];
databaseList.forEach(function(database) {
    db = server.getDB(database.db);
    db.dropUser(database.user);
    db.createUser({user:database.user, pwd:database.pwd, roles:["readWrite", "dbAdmin", "userAdmin"]});
});
exit;
EOF 