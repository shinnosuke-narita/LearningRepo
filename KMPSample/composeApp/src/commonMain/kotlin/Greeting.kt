import kmpproject.composeapp.generated.resources.Res
import kmpproject.composeapp.generated.resources.app_name
import kmpproject.composeapp.generated.resources.hello
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.getString
import kotlin.random.Random

@OptIn(ExperimentalResourceApi::class)
class Greeting {
    private val platform = getPlatform()

    suspend fun greet(): String {
        val firstWord = if (Random.nextBoolean()) getString(Res.string.hello) else "Hello!"
        return "$firstWord ${platform.name} $num"
    }
}