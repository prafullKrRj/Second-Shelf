# Second Shelf

Second Shelf is an application designed to facilitate the resale of used books. This project is built using Java and
Spring Boot, with Gradle as the build tool. The frontend will be developed in Android in the future.

## Project Structure

- **Backend**: Java, Kotlin, Spring Boot
- **Build Tool**: Gradle
- **Frontend**: Android (planned)

## Package Structure

- **com.prafull.secondshelf.repositories**
    - `UserRepository.kt`: Interface for handling CRUD operations for `UserEntity`. Extends `JpaRepository` to provide
      JPA related methods.

- **com.prafull.secondshelf.services**
    - `UserService.kt`: Service class for managing user-related operations. Future methods will include user
      registration, authentication, and profile management.
    - `BookService.kt`: Service class for managing book-related operations. Future methods will include adding new
      books, updating book details, and handling book resale transactions.

## Future Work

- **Frontend Development**: The frontend will be developed using Android. It will include features for user
  registration, book listing, and book purchasing.
- **Additional Services**: More service classes will be added to handle other aspects of the application, such as
  payment processing and notification management.