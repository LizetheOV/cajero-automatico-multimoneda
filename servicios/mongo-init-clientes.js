db.auth('clientesUser', 'clientes1234');
db=db.getSiblingDB('clientes-database');
db.createUser(
  {
    user: "clientesUser",
    pwd: "clientes1234",
    roles: [
      {
        role: "readWrite",
        db: "clientes-database"
      }
    ]
  }
);

db.createCollection('clientes');
