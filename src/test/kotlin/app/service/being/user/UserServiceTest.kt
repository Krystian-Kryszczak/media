package app.service.being.user

import app.model.being.user.User
import com.datastax.oss.driver.api.core.uuid.Uuids
import io.kotest.core.spec.style.StringSpec
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

@MicronautTest
class UserServiceTest(private val userService: UserService) : StringSpec({

    fun createUser(id: UUID) = User(
        id = id,
        name = "John",
        lastname = "Smith",
        email = "${UUID.randomUUID()}@example.com",
        phoneNumber = null,
        dateOfBirthInDays = 0,
        sex = 1
    )

    "test `save and find by id`" { // passed
        val id = Uuids.timeBased()
        val user = createUser(id)
        val result = withContext(Dispatchers.IO) {
            userService.saveReactive(user)
                .andThen(userService.findByIdReactive(id))
                .map { it == user }
                .defaultIfEmpty(false)
                .blockingGet()
        }
        assert(result)
    }

    "test `save and find email`" {
        val user = createUser(Uuids.timeBased())
        val result = withContext(Dispatchers.IO) {
            userService.saveReactive(user)
                .andThen(userService.findByEmailReactive(user.email!!))
                .map { it == user }
                .defaultIfEmpty(false)
                .blockingGet()
        }
        assert(result)
    }

    "test `save and delete by id`" {
        val id = Uuids.timeBased()
        val user = createUser(id)
        val result = withContext(Dispatchers.IO) {
            userService.saveReactive(user)
                .andThen(userService.deleteByIdReactive(id))
                .andThen(Single.just(true))
                .onErrorReturnItem(false)
                .blockingGet()
        }
        assert(result)
    }
})
