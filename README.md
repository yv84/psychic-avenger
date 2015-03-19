# psychic-avenger
spring layout

# hsqldb
$ java -cp ./lib/hsqldb.jar org.hsqldb.server.Server -database.0 file:mydb -dbname.0 base -port 9001

$ mvn compile -Dspring.profiles.active="profile1,profile2" -Djetty.port=8080 jetty:run
