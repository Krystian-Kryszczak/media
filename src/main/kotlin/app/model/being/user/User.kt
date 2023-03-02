package app.model.being.user

import app.model.being.Being
import com.datastax.oss.driver.api.mapper.annotations.*
import java.util.UUID

@Entity
@SchemaHint(targetElement = SchemaHint.TargetElement.TABLE)
data class User(
    @PartitionKey
    override val id: UUID? = null,
    override var name: String? = null,
    var lastname: String? = null,
    var email: String? = null,
    var phoneNumber: String? = null,
    val dateOfBirthInDays: Int = 0,
    val sex: Byte = 0
): Being(id, name)
