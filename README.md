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

### How to make id automatically generated?
- Specify `ObjectId` for `id` column on Entity. Field named `id` is automatically mapped to MongoDB's `_id` column.
- We can generate value by `ObjectId.get()`.



