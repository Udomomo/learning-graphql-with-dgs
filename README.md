# Learning-graphql-with-dgs
This is an implementation of the sample photo application in [Learning GraphQL](https://www.oreilly.com/library/view/learning-graphql/9781492030706/). But unlike the book I use:

- [DGS Framework](https://netflix.github.io/dgs/) + Spring Boot for backend
- MongoDB for database

# Launch
- Execute Spring Boot
- GraphQL query playground is available on `http://localhost:8080/graphiql`

## Tips about MongoDB
### How to check if data is correctly inserted?
- Log in with `mongosh -u root -p`
- Choose db by `use graphql-practice;`
- Check collection by `show collections;` , and get data by `db.<collection name>.find;`
- If you connect from Intellij, don't forget to specify `authSource` as `admin`.
