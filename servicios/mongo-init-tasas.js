db.auth('tasasUser', 'tasas1234');
db=db.getSiblingDB('tasas-database');
db.createUser(
  {
    user: "tasasUser",
    pwd: "tasas1234",
    roles: [
      {
        role: "readWrite",
        db: "tasas-database"
      }
    ]
  }
);

db.createCollection('tasas');
