# BSPQ19-E1
BSPQ19-E1

Compilation
`mvn clean compile`

Create Datanucleus schema: 
`mysql < src/sql/create-messagesdb.sql`
`mvn datanucleus:schema-create`

To start the registry
`start rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false`

Launch server:
`mvn exec:java -Pserver`

Launch user:
`mvn exec:java -Pclient`
